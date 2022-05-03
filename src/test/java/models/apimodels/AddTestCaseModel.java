package models.apimodels;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@lombok.Data
@ToString
@EqualsAndHashCode
@Jacksonized
public class AddTestCaseModel {
    private String title;
}
