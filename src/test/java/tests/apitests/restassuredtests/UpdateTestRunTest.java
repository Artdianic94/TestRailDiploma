package tests.apitests.restassuredtests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import models.apimodels.UpdateTestRunModel;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import staticdata.MyData;
import staticdata.WebUrl;
import apidata.CreateTestRunAPI;
import tests.uitests.CreateANewTestCaseTest;
import utilities.Retry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class UpdateTestRunTest extends BaseRestAssuredTest {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking the correctness of updates an existing test run")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Link("https://testrail.io/index.php?/api/v2/update_run/{run_id}")
    public void updateTestRunTest() throws SQLException, IOException {
        CreateTestRunAPI createTestRunAPI = new CreateTestRunAPI();
        UpdateTestRunModel updateTestRunModel = new UpdateTestRunModel();
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(updateTestRunModel)
                .and()
                .pathParam("run_id", createTestRunAPI.getRunId())
                .when()
                .post(webUrl.getTRUpdateTestRunUrl())
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
}
