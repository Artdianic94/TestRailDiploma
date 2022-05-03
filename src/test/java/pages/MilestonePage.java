package pages;

import elements.InputFields;
import elements.SelectOption;
import elements.TextAreaFields;
import io.cucumber.java.bs.I;
import io.qameta.allure.Step;
import models.uimodels.MilestoneModel;
import models.uimodels.TestRunModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import staticdata.WebUrl;

import java.sql.SQLException;

public class MilestonePage extends BasePage {
    private final By MILESTONE_TAB = By.xpath("//a[@class='link' and contains(.,'Milestones')]");
    private final By ADD_MILESTONE_BUTT = By.xpath("//div[@class='button-group']//a[contains(.,'Add Milestone')]");
    private final By SAVE_MILESTONE_BUTT = By.id("accept");
    WebUrl webUrl = new WebUrl();

    public MilestonePage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() throws SQLException {
        driver.get(webUrl.getTRDashboardUrl());
    }

    public void openMilestoneTab() {
        driver.findElement(MILESTONE_TAB).click();
    }

    public void addTMilestone() {
        driver.findElement(ADD_MILESTONE_BUTT).click();
    }

    @Step("Fill out the form and click to add milestone")
    public void fillMilestone(MilestoneModel milestoneModel) {
        new InputFields(driver, "name").inputText(milestoneModel.getName());
        new InputFields(driver, "reference").inputText(milestoneModel.getReferences());
        new SelectOption(driver, "").selectOption(milestoneModel.getParent());
        new TextAreaFields(driver, "description").textAreaText(milestoneModel.getDescription());
        new InputFields(driver, "start_on").inputText(milestoneModel.getStartDate());
        new InputFields(driver, "due_on").inputText(milestoneModel.getEndDate());
    }

    public void saveMilestone() {
        driver.findElement(SAVE_MILESTONE_BUTT).click();
    }
}
