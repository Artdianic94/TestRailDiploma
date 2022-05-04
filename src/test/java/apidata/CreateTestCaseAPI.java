package apidata;

import io.restassured.http.ContentType;
import models.apimodels.AddSectionModel;
import models.apimodels.AddTestCaseModel;
import models.apimodels.AddTestSuiteModel;
import org.apache.http.HttpStatus;
import staticdata.MyData;
import staticdata.WebUrl;
import tests.apitests.httpclienttests.ProjectsId;
import utilities.GenerateFakeMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class CreateTestCaseAPI {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
    ProjectsId projectsId = new ProjectsId();

    public CreateTestCaseAPI() throws SQLException {
    }

    public int getSuiteId() throws SQLException, IOException {
        AddTestSuiteModel addTestSuiteModel = new AddTestSuiteModel();
        addTestSuiteModel.setName(GenerateFakeMessage.getProjectName());
        addTestSuiteModel.setDescription(GenerateFakeMessage.getDescription());
        int suite_id = given()
                .header("Authorization", "Basic " + encoding)
                .contentType(ContentType.JSON)
                .and()
                .pathParam("project_id", projectsId.getProjectsId().get(0))
                .log()
                .all()
                .when()
                .body(addTestSuiteModel)
                .when()
                .post(webUrl.getTRAddSuiteUrl())
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("id");
        return suite_id;
    }

    public int getSectionId() throws SQLException, IOException {
        AddSectionModel addSectionModel = new AddSectionModel();
        addSectionModel.setDescription(GenerateFakeMessage.getDescription());
        addSectionModel.setName(GenerateFakeMessage.getProjectName());
        addSectionModel.setSuite_id(getSuiteId());
        int section_id =
                given()
                        .header("Authorization", "Basic " + encoding)
                        .contentType(ContentType.JSON)
                        .and()
                        .pathParam("project_id", projectsId.getProjectsId().get(0))
                        .log()
                        .all()
                        .when()
                        .body(addSectionModel)
                        .when()
                        .post(webUrl.getTRCreateSectionUrl())
                        .then()
                        .log()
                        .all()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().path("id");
        return section_id;
    }

    public int getCaseId() throws SQLException, IOException {
        AddTestCaseModel addTestCaseModel = new AddTestCaseModel();
        addTestCaseModel.setTitle(GenerateFakeMessage.getTitle());
        int case_id = given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(addTestCaseModel)
                .and()
                .pathParam("section_id", getSectionId())
                .when()
                .post(webUrl.getTRCreateTestCaseUrl())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .all()
                .extract().path("id");
        return case_id;
    }
}

