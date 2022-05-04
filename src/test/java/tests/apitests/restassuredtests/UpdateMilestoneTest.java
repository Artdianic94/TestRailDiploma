package tests.apitests.restassuredtests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import models.apimodels.UpdateMilestoneModel;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import staticdata.MyData;
import staticdata.WebUrl;
import apidata.CreateMilestoneAPI;
import tests.uitests.CreateANewTestCaseTest;
import utilities.Retry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class UpdateMilestoneTest extends BaseRestAssuredTest {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking the correctness of updates an existing milestone")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Link("https://testrail.io/index.php?/api/v2/update_milestone/{milestone_id}")
    public void updateMilestoneTest() throws SQLException, IOException {
        CreateMilestoneAPI createMilestoneAPI = new CreateMilestoneAPI();
        UpdateMilestoneModel updateMilestoneModel = new UpdateMilestoneModel();
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(updateMilestoneModel)
                .and()
                .pathParam("milestone_id", createMilestoneAPI.getMilestoneId())
                .when()
                .post(webUrl.getTRUpdateMilestoneUrl())
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
}
