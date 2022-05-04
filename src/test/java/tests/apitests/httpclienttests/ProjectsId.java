package tests.apitests.httpclienttests;

import com.google.gson.Gson;
import models.apimodels.GetAllProjectsModel;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import staticdata.MyData;
import staticdata.WebUrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class ProjectsId {
    private static final Logger LOGGER = LogManager.getLogger(LoginHTTPClientTest.class.getName());
    MyData myData = new MyData();
    HttpClient httpClient;
    List<Integer> projectId;


    public List<Integer> getProjectsId() throws IOException, SQLException {
        WebUrl webUrl = new WebUrl();
        httpClient = HttpClientBuilder.create().build();
        HttpPost loginRequest = new HttpPost(webUrl.getTRLoginUrl());
        List<NameValuePair> credentials = new ArrayList<>();
        credentials.add(new BasicNameValuePair("email", (String) myData.getMyData().get("email")));
        credentials.add(new BasicNameValuePair("password", (String) myData.getMyData().get("password")));
        loginRequest.setEntity(new UrlEncodedFormEntity(credentials));
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        HttpGet projectsRequest = new HttpGet(webUrl.getTRGetProjectsUrl());
        projectsRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
        HttpResponse response = httpClient.execute(projectsRequest);
        Gson gson = new Gson();
        GetAllProjectsModel actualProjects = gson.fromJson(EntityUtils.toString(response.getEntity()), GetAllProjectsModel.class);
        projectId = actualProjects.getMapOfProjects(actualProjects.getProjects());
        LOGGER.log(Level.INFO, "The project Id is" + projectId);
        return projectId;
    }
}

