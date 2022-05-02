package models.apimodels;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@EqualsAndHashCode
@ToString
@Jacksonized
public class Links {
    private String next;
    private String prev;
}
