package models.apimodels;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@lombok.Data
@ToString
@EqualsAndHashCode
@Jacksonized
public class AddProjectModel {
    private String name;
    private String announcement;
    private boolean show_announcement;
}
