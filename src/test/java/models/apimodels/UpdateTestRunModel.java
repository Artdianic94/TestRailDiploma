package models.apimodels;

import lombok.Data;

@Data
public class UpdateTestRunModel {
    private String description;
    private boolean include_all;
}
