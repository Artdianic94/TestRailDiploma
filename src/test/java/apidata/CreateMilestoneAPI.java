package apidata;

import io.restassured.http.ContentType;
import models.apimodels.AddMilestoneModel;
import org.apache.http.HttpStatus;
import staticdata.MyData;
import staticdata.WebUrl;
import tests.apitests.httpclienttests.ProjectsId;
import utilities.GenerateFakeMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class CreateMilestoneAPI {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
    ProjectsId projectsId = new ProjectsId();

    public CreateMilestoneAPI() throws SQLException {
    }

    public int getMilestoneId() throws SQLException, IOException {
        AddMilestoneModel addMilestoneModel = new AddMilestoneModel();
        addMilestoneModel.setName(GenerateFakeMessage.getProjectName());
        addMilestoneModel.setDescription(GenerateFakeMessage.getDescription());
        return given()
                .header("Authorization", "Basic " + encoding)
                .contentType(ContentType.JSON)
                .and()
                .pathParam("project_id", projectsId.getProjectsId().get(0))
                .log()
                .all()
                .when()
                .body(addMilestoneModel)
                .when()
                .post(webUrl.getTRCreateMilestoneUrl())
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("id");
    }

}
