package apidata;

import io.restassured.http.ContentType;
import models.apimodels.AddTestRunModel;
import org.apache.http.HttpStatus;
import staticdata.MyData;
import staticdata.WebUrl;
import tests.apitests.httpclienttests.ProjectsId;
import utilities.GenerateFakeMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class CreateTestRunAPI {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
    ProjectsId projectsId = new ProjectsId();

    public CreateTestRunAPI() throws SQLException {
    }

    public int getRunId() throws SQLException, IOException {
        CreateTestCaseAPI createTestCaseAPI = new CreateTestCaseAPI();
        AddTestRunModel addTestRunModel = new AddTestRunModel();
        addTestRunModel.setName(GenerateFakeMessage.getTitle());
        addTestRunModel.setSuite_id(createTestCaseAPI.getSuiteId());
        int run_id = given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(addTestRunModel)
                .and()
                .pathParam("project_id", projectsId.getProjectsId().get(0))
                .when()
                .post(webUrl.getTRCreateTestRunUrl())
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("id");
        return run_id;
    }
}
