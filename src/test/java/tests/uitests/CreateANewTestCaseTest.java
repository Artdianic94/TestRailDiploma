package tests.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NewProjectPage;
import pages.SuccessfulMessagePage;
import pages.TestCasesPage;
import testdata.GetTestCaseModel;
import tests.apitests.restassuredtests.BaseRestAssuredTest;
import utilities.Retry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class CreateANewTestCaseTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking for the message that a test case has been successfully created")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://testrail.io")
    public void createANewTestCaseTest() throws SQLException, IOException {
        NewProjectPage newProjectPage = new NewProjectPage(driver);
        newProjectPage.createProject();
        TestCasesPage testCasesPage = new TestCasesPage(driver);
        testCasesPage.openMainPage();
        testCasesPage.openTestCasesTab();
        testCasesPage.addTestCase();
        testCasesPage.saveTestCase(GetTestCaseModel.getTestCaseWithFields());
        LOGGER.debug("Debugging test case creating" + testCasesPage.getClass().getName());
        testCasesPage.saveTestCase();
        SuccessfulMessagePage successfulMessagePage = new SuccessfulMessagePage(driver);
        LOGGER.debug("Debugging alert message" + successfulMessagePage.getClass().getName());
        String actualAlertMessage = successfulMessagePage.alertMessageGetText();
        String expectedAlertMessage = "Successfully added the new test case. Add another";
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Messages are not equal");
    }
}
