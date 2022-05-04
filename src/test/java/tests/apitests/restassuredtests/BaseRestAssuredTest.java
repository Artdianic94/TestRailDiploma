package tests.apitests.restassuredtests;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.apimodels.AddProjectModel;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import staticdata.MyData;
import staticdata.WebUrl;
import tests.apitests.httpclienttests.LoginHTTPClientTest;
import tests.apitests.httpclienttests.ProjectsId;
import utilities.GenerateFakeMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseRestAssuredTest {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    ProjectsId projectsId = new ProjectsId();
    List<Integer> allProjectsId;

    public BaseRestAssuredTest() {
    }

    private static final Logger LOGGER = LogManager.getLogger(LoginHTTPClientTest.class.getName());


    @BeforeTest
    public void addNewProject() throws SQLException {
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        AddProjectModel addProjectModel = new AddProjectModel();
        addProjectModel.setName(GenerateFakeMessage.getProjectName());
        addProjectModel.setAnnouncement(GenerateFakeMessage.getProjectName());
        addProjectModel.setShow_announcement(true);
        given()
                .when()
                .formParam("email", myData.getMyData().get("email"))
                .formParam("password", myData.getMyData().get("password"))
                .post(webUrl.getTRLoginUrl())
                .then()
                .log()
                .all()
                .statusCode(200);
        given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(addProjectModel)
                .when()
                .post(webUrl.getTRAddProjectUrl())
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @AfterTest
    public void deleteProject() throws SQLException, IOException {
        allProjectsId = projectsId.getProjectsId();
        LOGGER.log(Level.INFO, allProjectsId);
        RequestSpecification requestLoginSpec = given();
        requestLoginSpec.when()
                .formParam("email", myData.getMyData().get("email"))
                .formParam("password", myData.getMyData().get("password"))
                .post(webUrl.getTRLoginUrl())
                .then()
                .log()
                .all()
                .statusCode(200);
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        for (Integer integer : allProjectsId) {
            given()
                    .header("Authorization", "Basic " + encoding)
                    .when()
                    .contentType(ContentType.JSON)
                    .log()
                    .all()
                    .and()
                    .pathParam("project_id", integer)
                    .when()
                    .post(webUrl.getTRDeleteProjectUrl())
                    .then()
                    .log()
                    .all()
                    .statusCode(200);
        }
    }
}
