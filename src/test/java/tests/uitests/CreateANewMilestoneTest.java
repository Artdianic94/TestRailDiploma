package tests.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MilestonePage;
import pages.NewProjectPage;
import pages.SuccessfulMessagePage;
import testdata.GetMilestoneModel;
import utilities.Retry;

import java.sql.SQLException;

public class CreateANewMilestoneTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking for the message that test a milestone has been successfully created")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://testrail.io")
    public void createANewMilestoneTest() throws SQLException {
        NewProjectPage newProjectPage = new NewProjectPage(driver);
        newProjectPage.createProject();
        MilestonePage milestonePage = new MilestonePage(driver);
        milestonePage.openMainPage();
        milestonePage.openMilestoneTab();
        milestonePage.addTMilestone();
        milestonePage.fillMilestone(GetMilestoneModel.getMilestoneWithFields());
        LOGGER.debug("Debugging milestone creating" + milestonePage.getClass().getName());
        milestonePage.saveMilestone();
        SuccessfulMessagePage successfulMessagePage = new SuccessfulMessagePage(driver);
        LOGGER.debug("Debugging message" + successfulMessagePage.getClass().getName());
        String actualMessage = successfulMessagePage.messageGetText();
        String expectedAlertMessage = "Successfully added the new milestone.";
        Assert.assertEquals(actualMessage, expectedAlertMessage, "Messages are not equal");
    }
}
