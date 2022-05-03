package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulMessagePage extends BasePage {
    private By MESSAGE_XPATH = By.xpath("//div[@class='message message-success' and contains(.,'added')]");

    public SuccessfulMessagePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get text from the message")
    public String messageGetText() {
        return driver.findElement(MESSAGE_XPATH).getText();
    }
}
