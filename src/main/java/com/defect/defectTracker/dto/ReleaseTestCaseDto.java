package com.defect.defectTracker.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class ReleaseTestCaseDto {
    private String releaseTestCaseId;
    private String testCaseId;
    private String testCaseDescription;
    private String testSteps;
    private Date testDate;
    private Time testTime;
    private String testCaseStatus;
    private String testedBy;
    private String releaseId;
    private String severityName;
    private String typeName;
    private String moduleName;
    private String subModuleName;
    private String projectName;
    public String getReleaseTestCaseId() {
        return releaseTestCaseId;
    }

}