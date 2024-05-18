package com.jobsync.jobysncapi.organization.api.dto.request;


import com.jobsync.jobysncapi.organization.domain.model.entity.Application;
import com.jobsync.jobysncapi.organization.domain.model.entity.ApplicationPhaseTask;
import com.jobsync.jobysncapi.organization.domain.model.entity.RecruitmentPhase;
import lombok.Data;

import java.util.List;

@Data
public class ApplicationPhaseRequest {


    private Application application;
    private RecruitmentPhase recruitmentPhase;
    private List<ApplicationPhaseTask> applicationPhaseTasks;

}
