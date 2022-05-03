package pages;

import elements.InputFields;
import elements.SelectOption;
import elements.TextAreaFields;
import io.qameta.allure.Step;
import models.uimodels.TestRunModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import staticdata.WebUrl;

import java.sql.SQLException;

public class TestRunPage extends BasePage {
    private final By TEST_RUN_TAB = By.xpath("//a[@class='link' and contains(.,'Test Runs')]");
    private final By ADD_TEST_RUN_BUTT = By.xpath("//div[@class='button-group']//a[contains(.,'Add Test Run')]");
    private final By SAVE_TEST_RUN_BUTT = By.id("accept");
    WebUrl webUrl = new WebUrl();

    public TestRunPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() throws SQLException {
        driver.get(webUrl.getTRDashboardUrl());
    }

    public void openTestRunTab() {
        driver.findElement(TEST_RUN_TAB).click();
    }

    public void addTestRun() {
        driver.findElement(ADD_TEST_RUN_BUTT).click();
    }

    @Step("Fill out the form and click to save a new test run")
    public void fillTestRun(TestRunModel testRunModel) {
        new InputFields(driver, "name").inputText(testRunModel.getName());
        new InputFields(driver, "refs").inputText(testRunModel.getReferences());
        new SelectOption(driver, "").selectOption(testRunModel.getMilestone());
        new SelectOption(driver, "Me").selectOption(testRunModel.getAssignTo());
        new TextAreaFields(driver, "description").textAreaText(testRunModel.getDescription());
    }

    public void saveTestRun() {
        driver.findElement(SAVE_TEST_RUN_BUTT).click();
    }
}
