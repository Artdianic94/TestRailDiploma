package tests.apitests.httpclienttests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import staticdata.MyData;
import staticdata.WebUrl;
import utilities.Retry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginHTTPClientTest extends BaseHTTPClientTest {
    private static final Logger LOGGER = LogManager.getLogger(LoginHTTPClientTest.class.getName());
    MyData myData = new MyData();

    @Description("Checking for the login")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://testrail.io/index.php?/auth/login")
    public void loginTest() throws IOException, SQLException {
        WebUrl webUrl = new WebUrl();
        LOGGER.log(Level.INFO, "'Login' test started");
        HttpPost loginRequest = new HttpPost(webUrl.getTRLoginUrl());
        List<NameValuePair> credentials = new ArrayList<>();
        credentials.add(new BasicNameValuePair("email", (String) myData.getMyData().get("email")));
        credentials.add(new BasicNameValuePair("password", (String) myData.getMyData().get("password")));
        loginRequest.setEntity(new UrlEncodedFormEntity(credentials));
        HttpResponse response = httpClient.execute(loginRequest);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Error in authorization");
    }
}
