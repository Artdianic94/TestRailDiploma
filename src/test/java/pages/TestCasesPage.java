package pages;

import elements.TestCasesInputFields;
import elements.TestCasesSelectOption;
import elements.TestCasesTextAreaFields;
import io.qameta.allure.Step;
import models.uimodels.TestCasesModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import staticdata.WebUrl;

import java.sql.SQLException;

public class TestCasesPage extends BasePage {
    private final By TEST_CASES_TAB = By.xpath("//a[@class='link' and contains(.,'Test Cases')]");
    private final By TEST_CASES_ADDTEST_CASE_BUTT = By.xpath("//a[@id='addSectionInline']//following-sibling::a[contains(.,'Add Test Case')]");
    private final By TEST_CASES_SAVETESTCASE_BUTT = By.id("accept");
    WebUrl webUrl = new WebUrl();

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() throws SQLException {
        driver.get(webUrl.getTRDashboardUrl());
    }

    public void openTestCasesTab() {
        driver.findElement(TEST_CASES_TAB).click();
    }

    public void addTestCase() {
        driver.findElement(TEST_CASES_ADDTEST_CASE_BUTT).click();
    }


    @Step("Fill out the form and click to save a new test case")
    public void saveTestCase(TestCasesModel testCasesModel) {
        new TestCasesInputFields(driver, "title").inputText(testCasesModel.getTitle());
        new TestCasesSelectOption(driver, "Test Cases").selectOption(testCasesModel.getSection());
        new TestCasesSelectOption(driver, "Test Case (Text)").selectOption(testCasesModel.getTemplate());
        new TestCasesSelectOption(driver, "Automated").selectOption(testCasesModel.getType());
        new TestCasesSelectOption(driver, "Critical").selectOption(testCasesModel.getPriority());
        new TestCasesInputFields(driver, "estimate").inputText(testCasesModel.getEstimate());
        new TestCasesInputFields(driver, "refs").inputText(testCasesModel.getReferences());
        new TestCasesSelectOption(driver, "Ranorex").selectOption(testCasesModel.getAutomationType());
        new TestCasesTextAreaFields(driver, "preconds").textAreaText(testCasesModel.getPreconditions());
        new TestCasesTextAreaFields(driver, "steps").textAreaText(testCasesModel.getSteps());
        new TestCasesTextAreaFields(driver, "expected").textAreaText(testCasesModel.getExpectedResult());
    }

    public void saveTestCase() {
        driver.findElement(TEST_CASES_SAVETESTCASE_BUTT).click();
    }
}
