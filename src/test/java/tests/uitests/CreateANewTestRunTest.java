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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.NewProjectPage;
import pages.SuccessfulMessagePage;
import pages.TestRunPage;
import testdata.GetTestRunModel;
import utilities.Retry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class CreateANewTestRunTest extends BaseTest {
    @BeforeMethod
    public void createProject() {
        NewProjectPage newProjectPage = new NewProjectPage(driver);
        newProjectPage.createProject();
    }

    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking for the message that test run has been successfully created")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://testrail.io")
    public void createANewTestRunTest() throws SQLException, IOException {
        TestRunPage testRunPage = new TestRunPage(driver);
        testRunPage.openMainPage();
        testRunPage.openTestRunTab();
        testRunPage.addTestRun();
        testRunPage.fillTestRun(GetTestRunModel.getTestRunWithFields());
        LOGGER.debug("Debugging test run creating" + testRunPage.getClass().getName());
        testRunPage.saveTestRun();
        SuccessfulMessagePage successfulMessagePage = new SuccessfulMessagePage(driver);
        LOGGER.debug("Debugging message" + successfulMessagePage.getClass().getName());
        String actualMessage = successfulMessagePage.messageGetText();
        String expectedAlertMessage = "Successfully added the new test run.";
        TakesScreenshot screenShot = ((TakesScreenshot) driver);
        byte[] sourceFile = screenShot.getScreenshotAs(OutputType.BYTES);
        Files.write(Paths.get("src/test/resources/CreateANewTestRunTest.png"), sourceFile);
        Assert.assertEquals(actualMessage, expectedAlertMessage, "Messages are not equal");
    }
}
