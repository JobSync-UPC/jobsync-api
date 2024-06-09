package com.jobsync.jobysncapi.organization.api.dto.response;

import lombok.Data;

@Data
public class ApplicationPhaseTaskResponse {

    private Long id;
    private Long applicationPhaseId;
    private Long taskId;
    private boolean completed;
    private boolean pass;
}
