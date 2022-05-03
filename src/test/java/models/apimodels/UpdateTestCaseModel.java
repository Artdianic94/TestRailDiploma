package models.apimodels;

import lombok.Data;

@Data
public class UpdateTestCaseModel {
    private int case_id;
    private int section_id;
    private String title;
    private String refs;
}
