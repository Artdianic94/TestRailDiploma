package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureService {
    @Attachment
    public byte[] takeScreenShot(WebDriver driver) {
        TakesScreenshot screenShot = ((TakesScreenshot) driver);
        return screenShot.getScreenshotAs(OutputType.BYTES);
    }
}
