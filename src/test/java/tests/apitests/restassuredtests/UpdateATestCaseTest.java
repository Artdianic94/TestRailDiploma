package tests.apitests.restassuredtests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import models.apimodels.UpdateTestCaseModel;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import staticdata.MyData;
import staticdata.WebUrl;
import testdata.CreateTestCaseAPI;
import tests.uitests.CreateANewTestCaseTest;
import utilities.GenerateFakeMessage;
import utilities.Retry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class UpdateATestCaseTest extends BaseRestAssuredTest {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();
    private static final Logger LOGGER = LogManager.getLogger(CreateANewTestCaseTest.class.getName());

    @Description("Checking the correctness of updates an existing test case")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Link("https://testrail.io/index.php?/api/v2/update_case/{case_id}")
    public void updateTestCaseTest() throws SQLException, IOException {
        CreateTestCaseAPI createTestCaseAPI = new CreateTestCaseAPI();
        UpdateTestCaseModel updateTestCaseModel = new UpdateTestCaseModel();
        updateTestCaseModel.setCase_id(createTestCaseAPI.getCaseId());
        updateTestCaseModel.setSection_id(createTestCaseAPI.getSectionId());
        updateTestCaseModel.setTitle(GenerateFakeMessage.getTitle());
        updateTestCaseModel.setRefs(GenerateFakeMessage.getReferences());
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(updateTestCaseModel)
                .and()
                .pathParam("case_id", createTestCaseAPI.getCaseId())
                .when()
                .post(webUrl.getTRUpdateTestCaseUrl())
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
}
