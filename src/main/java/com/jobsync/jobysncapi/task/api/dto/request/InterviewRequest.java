package com.jobsync.jobysncapi.task.api.dto.request;

import lombok.Data;
import java.util.Date;
@Data
public class InterviewRequest {

    private String title;
    private Date start_date;
    private Date end_date;
    private String linkurl;

}
