package elements;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesInputFields {
    WebDriver driver;
    String fieldName;
    String inputXpath = "//div[@id='form-controls']//input[@id='%s']";
    private static final Logger LOGGER = LogManager.getLogger(TestCasesInputFields.class.getName());

    public TestCasesInputFields(WebDriver driver, String fieldName) {
        this.driver = driver;
        this.fieldName = fieldName;
    }

    public void inputText(String text) {
        inputXpath = String.format(inputXpath, fieldName);
        LOGGER.trace(String.format("Fill in %s text into %s field located by %s xpath", text, fieldName, inputXpath));
        driver.findElement(By.xpath(inputXpath)).sendKeys(text);
    }
}
