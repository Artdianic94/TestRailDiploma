package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectOption {
    WebDriver driver;
    String label;
    String selectOptionListXpath = "//div[@id='%s_id_chzn']//a";

    public SelectOption(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }
    public void selectOption(String option) {
        selectOptionListXpath = String.format(selectOptionListXpath, option);
        driver.findElement(By.xpath(selectOptionListXpath)).click();
    }
}
