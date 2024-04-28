package com.jobsync.jobysncapi.task.api.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class InterviewResponse {

    private Long id;
    private String title;
    private Date start_date;
    private Date end_date;
    private String linkurl;
}
