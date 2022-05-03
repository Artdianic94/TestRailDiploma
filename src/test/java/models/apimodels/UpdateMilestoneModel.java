package models.apimodels;

import lombok.Data;

@Data
public class UpdateMilestoneModel {
    private boolean is_completed;
    private boolean is_started;
}
