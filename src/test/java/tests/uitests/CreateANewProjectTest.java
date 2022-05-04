package tests.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NewProjectPage;
import utilities.Retry;

import java.sql.SQLException;

public class CreateANewProjectTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CreateANewProjectTest.class.getName());

    @Description("Checking the creation of a new project")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://testrail.io/index.php?/api/v2/add_project")
    public void createProjectTest() throws SQLException {
        LOGGER.info("createProjectTest started");
        NewProjectPage newProjectPage = new NewProjectPage(driver);
        LOGGER.info("Open main page");
        newProjectPage.openMainPage();
        LOGGER.info("Attempt to create Project " + newProjectPage.getClass().getName());
        String expectedNameOfNewProject = newProjectPage.createProject();
        String actualNameOnMainPage = newProjectPage.getNameOfProjectOnMainPage();
        Assert.assertEquals(expectedNameOfNewProject, actualNameOnMainPage, "Names are not equal");
    }

}
