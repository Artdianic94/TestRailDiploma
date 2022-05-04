package tests.apitests.restassuredtests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import models.apimodels.UpdateProjectModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import staticdata.MyData;
import staticdata.WebUrl;
import utilities.GenerateFakeMessage;
import utilities.Retry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class UpdateAProjectTest extends BaseRestAssuredTest {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();

    @Description("Checking the correctness of updates an existing project")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Link("https://testrail.io/index.php?/api/v2/update_project/{project_id}")
    public void updateProjectTest() throws SQLException, IOException {
        UpdateProjectModel updateProjectModel = new UpdateProjectModel();
        updateProjectModel.setName(GenerateFakeMessage.getProjectName());
        updateProjectModel.setAnnouncement(GenerateFakeMessage.getDescription());
        updateProjectModel.setShow_announcement(false);
        updateProjectModel.setSuite_mode(GenerateFakeMessage.getAnyNumber());
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(updateProjectModel)
                .and()
                .pathParam("project_id", projectsId.getProjectsId().get(0))
                .when()
                .post(webUrl.getTRUpdateProjectUrl())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .log()
                .all();
    }
}
