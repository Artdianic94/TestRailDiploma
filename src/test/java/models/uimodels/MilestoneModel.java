package models.uimodels;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class MilestoneModel {
    private String name;
    private String references;
    private String parent;
    private String description;
    private String startDate;
    private String endDate;
}
