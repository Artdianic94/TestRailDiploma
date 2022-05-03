package testdata;

import models.uimodels.TestRunModel;
import utilities.GenerateFakeMessage;

public class GetTestRunModel {
    public static TestRunModel getTestRunWithFields() {
        return TestRunModel.builder()
                .name(GenerateFakeMessage.getTitle())
                .references(GenerateFakeMessage.getReferences())
                .milestone("milestone")
                .assignTo("assignedto")
                .description(GenerateFakeMessage.getDescription())
                .build();
    }
}
