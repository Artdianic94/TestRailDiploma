package models.uimodels;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class TestCasesModel {
    private String title;
    private String section;
    private String template;
    private String type;
    private String priority;
    private String estimate;
    private String references;
    private String automationType;
    private String preconditions;
    private String steps;
    private String expectedResult;
}
