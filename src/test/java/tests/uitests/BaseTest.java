package tests.uitests;


import factorydriver.DriverFactory;
import factorydriver.DriverManager;
import factorydriver.DriverType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import staticdata.MyData;
import tests.apitests.restassuredtests.BaseRestAssuredTest;
import utilities.TestListeners;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

@Listeners(TestListeners.class)
public class BaseTest {
    WebDriver driver;
    DriverManager driverManager;
    private static final Logger LOGGER = LogManager.getLogger(CreateANewProjectTest.class.getName());
    MyData myData = new MyData();

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("firefox") String browser) throws SQLException, MalformedURLException {

        DriverFactory factory = new DriverFactory();
        DriverType driverType = switch (browser) {
            case "chrome" -> DriverType.CHROME;
            case "firefox" -> DriverType.FIREFOX;
            case "remote" -> DriverType.REMOTE;
            default -> null;
        };
        driverManager = factory.getManager(driverType);
        driverManager.setUpDriver();
        driver = driverManager.getDriver();
        driverManager.maximize();
        driverManager.setTimeout();
        System.getProperty("configuration");
        LoginPage loginPage = new LoginPage(driver);
        LOGGER.info("Attempt to open " + loginPage.getClass().getName() + " page");
        loginPage.openLoginPage();
        LOGGER.info("Attempt to login with email: " + myData.getMyData().get("email") + "and password: " + myData.getMyData().get("password"));
        loginPage.inputAndLogin((String) myData.getMyData().get("email"), (String) myData.getMyData().get("password"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterTest(alwaysRun = true)
    public void quiteBrowser() {
        driverManager.quitDriver();
    }

    @AfterSuite
    public void deleteCreatedProject() throws SQLException, IOException {
        BaseRestAssuredTest baseRestAssuredTest = new BaseRestAssuredTest();
        baseRestAssuredTest.deleteProject();
    }
}