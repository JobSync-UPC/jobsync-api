package com.jobsync.jobysncapi.organization.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationPhaseTaskRequest {

    @NotNull
    private Long applicationPhaseId;

    @NotNull
    private Long taskId;

    private boolean completed;
    private boolean pass;
}
