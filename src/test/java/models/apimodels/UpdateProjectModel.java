package models.apimodels;

import lombok.Data;

@Data
public class UpdateProjectModel {
    private String name;
    private String announcement;
    private boolean show_announcement;
    private int suite_mode;
}
