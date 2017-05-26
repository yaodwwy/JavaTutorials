package com.adam.sys.sysfunc.entity;

import java.util.Date;

import com.adam.common.entity.BaseEntity;

public class Job extends BaseEntity{
    
	private static final long serialVersionUID = -4204514518494583060L;

	private Integer jobId;

    private String jobName;

    private String jobGroup;

    private String cronExpression;

    private String jobStatus;

    private String jobStatusname;
    
    private String jobClass;

    private Date prevfireTime;

    public String getJobStatusname() {
		return jobStatusname;
	}

	public void setJobStatusname(String jobStatusname) {
		this.jobStatusname = jobStatusname;
	}

	public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass == null ? null : jobClass.trim();
    }

    public Date getPrevfireTime() {
        return prevfireTime;
    }

    public void setPrevfireTime(Date prevfireTime) {
        this.prevfireTime = prevfireTime;
    }

}