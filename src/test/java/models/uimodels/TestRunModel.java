package models.uimodels;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class TestRunModel {
    private String name;
    private String references;
    private String milestone;
    private String assignTo;
    private String description;
}
