package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import staticdata.WebUrl;

import java.sql.SQLException;

public class LoginPage extends BasePage {
    private By EMAIL_INPUT = By.id("name");
    private By PASSWORD_INPUT = By.id("password");
    private By LOGIN_BUTTON = By.id("button_primary");
    WebUrl webUrl = new WebUrl();

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @Step("Open login page")
    public void openLoginPage() throws SQLException {
        driver.get(webUrl.getTRLoginUrl());
    }

    @Step("Data entry and login")
    public void inputAndLogin(String email, String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
