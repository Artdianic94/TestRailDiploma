package tests.apitests.httpclienttests;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import staticdata.MyData;
import staticdata.WebUrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseHTTPClientTest {
    HttpClient httpClient;
    MyData myData = new MyData();


    @BeforeClass
    public void createClient() throws IOException, SQLException {
        WebUrl webUrl = new WebUrl();
        httpClient = HttpClientBuilder.create().build();
        HttpPost loginRequest = new HttpPost(webUrl.getTRLoginUrl());
        List<NameValuePair> credentials = new ArrayList<>();
        credentials.add(new BasicNameValuePair("email", (String) myData.getMyData().get("email")));
        credentials.add(new BasicNameValuePair("password", (String) myData.getMyData().get("password")));
        loginRequest.setEntity(new UrlEncodedFormEntity(credentials));
    }
}
