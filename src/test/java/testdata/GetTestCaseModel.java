package testdata;

import models.uimodels.TestCasesModel;
import utilities.GenerateFakeMessage;

public class GetTestCaseModel {
    public static TestCasesModel getTestCaseWithFields() {
        return TestCasesModel.builder()
                .title(GenerateFakeMessage.getTitle())
                .section("section_id")
                .template("template_id")
                .type("type_id")
                .priority("priority_id")
                .estimate(GenerateFakeMessage.getEstimate())
                .references(GenerateFakeMessage.getReferences())
                .automationType("custom_automation_type")
                .preconditions(GenerateFakeMessage.getPreconditions())
                .steps(GenerateFakeMessage.getSteps())
                .expectedResult(GenerateFakeMessage.getExpectedResult())
                .build();
    }
}
