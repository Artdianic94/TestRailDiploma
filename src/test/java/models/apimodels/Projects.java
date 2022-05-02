package models.apimodels;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@lombok.Data
@Builder
@EqualsAndHashCode
@Jacksonized
@ToString
public class Projects {
    private int id;
    private String name;
}
