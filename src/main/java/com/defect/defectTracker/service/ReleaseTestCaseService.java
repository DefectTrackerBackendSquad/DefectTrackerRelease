package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.dto.TestCaseResponseDTO;

import java.util.List;

public interface ReleaseTestCaseService {
    TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId);
    void deleteTestCaseByReleaseTestCaseId(String releaseTestCaseId);
    List<ReleaseTestCaseDto> getTestCasesByReleaseId(String releaseId);
}
