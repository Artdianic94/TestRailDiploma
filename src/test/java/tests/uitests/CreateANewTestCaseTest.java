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
import pages.TestCasesPage;
import testdata.GetTestCaseModel;
import utilities.Retry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class CreateANewTestCaseTest extends BaseTest {
    @BeforeMethod
    public void createProject() {
        NewProjectPage newProjectPage = new NewProjectPage(driver);
        newProjectPage.createProject();
    }

    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking for the message that a test case has been successfully created")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://testrail.io")
    public void createANewTestCaseTest() throws SQLException, IOException {
        TestCasesPage testCasesPage = new TestCasesPage(driver);
        testCasesPage.openMainPage();
        testCasesPage.openTestCasesTab();
        testCasesPage.addTestCase();
        testCasesPage.fillTestCase(GetTestCaseModel.getTestCaseWithFields());
        LOGGER.debug("Debugging test case creating" + testCasesPage.getClass().getName());
        testCasesPage.saveTestCase();
        SuccessfulMessagePage successfulMessagePage = new SuccessfulMessagePage(driver);
        LOGGER.debug("Debugging message" + successfulMessagePage.getClass().getName());
        String actualAlertMessage = successfulMessagePage.messageGetText();
        String expectedAlertMessage = "Successfully added the new test case. Add another";
        TakesScreenshot screenShot = ((TakesScreenshot) driver);
        byte[] sourceFile = screenShot.getScreenshotAs(OutputType.BYTES);
        Files.write(Paths.get("src/test/resources/createANewTestCaseTest.png"), sourceFile);
        Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Messages are not equal");
    }
}
