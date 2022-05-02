package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesTextAreaFields {
    WebDriver driver;
    String label;
    String textareaXpath = "//div[@id='custom_%s_display']";

    public TestCasesTextAreaFields(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void textAreaText(String text) {
        textareaXpath = String.format(textareaXpath, label);
        driver.findElement(By.xpath(textareaXpath)).sendKeys(text);
    }
}
