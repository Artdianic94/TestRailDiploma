package tests.apitests.restassuredtests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import staticdata.MyData;
import staticdata.WebUrl;
import utilities.Retry;


import java.sql.SQLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.instanceOf;

public class DataTypeOfAllProjectsTest extends BaseRestAssuredTest {
    MyData myData = new MyData();
    WebUrl webUrl = new WebUrl();

    @Description("Checking the correctness of field data types")
    @Test(retryAnalyzer = Retry.class)
    @Severity(SeverityLevel.NORMAL)
    @Link("https://testrail.io/index.php?/api/v2/get_projects")
    public void dataTypeTest() throws SQLException {
        String encoding = Base64.getEncoder().encodeToString((myData.getMyData().get("email") + ":" + myData.getMyData().get("password")).getBytes());
        given()
                .header("Authorization", "Basic " + encoding)
                .when()
                .get(webUrl.getTRGetProjectsUrl())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .all()
                .body("offset", instanceOf(Integer.class))
                .body("limit", instanceOf(Integer.class))
                .and()
                .log()
                .all();

    }
}
