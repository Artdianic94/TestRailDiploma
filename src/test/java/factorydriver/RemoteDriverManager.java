package factorydriver;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RemoteDriverManager extends DriverManager {
    @Override
    public void setUpDriver() throws MalformedURLException {
        EdgeOptions browserOptions = new EdgeOptions();
        browserOptions.setCapability("platformName", "macOS 10.15");
        browserOptions.setCapability("browserVersion", "latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        browserOptions.setCapability("sauce:options", sauceOptions);
        driver = new RemoteWebDriver(new URL("https://oauth-artyomenko.diana.nic-058e2:34e8539f-2050-4912-a833-27082de52017@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), browserOptions);
    }
}
