package testdata;

import models.uimodels.MilestoneModel;
import utilities.GenerateFakeMessage;

public class GetMilestoneModel {
    public static MilestoneModel getMilestoneWithFields() {
        return MilestoneModel.builder()
                .name(GenerateFakeMessage.getTitle())
                .references(GenerateFakeMessage.getReferences())
                .parent("parent")
                .description(GenerateFakeMessage.getDescription())
                .startDate("")
                .endDate("")
                .build();
    }
}
