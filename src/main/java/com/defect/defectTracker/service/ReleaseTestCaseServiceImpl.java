package com.defect.defectTracker.service;
import com.defect.defectTracker.repository.ReleasesRepo;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.ReleaseTestCaseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {

    @Autowired
    private ReleaseTestCaseRepo releaseTestCaseRepo;
    @Autowired
    private  ReleasesRepo releaseRepo;

    @Override
    public TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId) {
        ReleaseTestCase rtc = releaseTestCaseRepo.findByReleaseTestCaseId(releaseTestCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found"));

        TestCaseResponseDTO dto = new TestCaseResponseDTO();
        dto.setReleasedTestcaseId(rtc.getReleaseTestCaseId());
        dto.setTestcaseId(rtc.getTestCase().getTestCaseId());
        dto.setTestcase(rtc.getTestCase().getDescription());
        dto.setDescription(rtc.getTestCase().getDescription());
        dto.setReleaseId(rtc.getReleases().getReleaseId());
        dto.setTestDate(rtc.getTestDate().toString());
        dto.setTestTime(rtc.getTestTime().toString().substring(0, 5));
        dto.setSeverityId(rtc.getTestCase().getSeverity().getId().toString());
        dto.setSeverity(rtc.getTestCase().getSeverity().getSeverityName());
        dto.setSteps(rtc.getTestCase().getSteps());
        dto.setTypeId(rtc.getTestCase().getType().getId().toString());
        dto.setType(rtc.getTestCase().getType().getTypeName());
        dto.setTestcaseStatus("Passed".equalsIgnoreCase(rtc.getTestCaseStatus()) ? 1 : 0);

        return dto;
    }

    @Override
    public void deleteTestCaseByReleaseTestCaseId(String releaseTestCaseId) {
        boolean exists = releaseTestCaseRepo.existsByReleaseTestCaseId(releaseTestCaseId);
        if (!exists) {
            throw new ResourceNotFoundException("ReleaseTestCase with ID " + releaseTestCaseId + " not found");
        }
        releaseTestCaseRepo.deleteByReleaseTestCaseId(releaseTestCaseId);
    }
    @Override
    public List<ReleaseTestCaseDto> getTestCasesByReleaseId(String releaseId) {
        if (!releaseRepo.existsByReleaseId(releaseId)) {
            throw new ResourceNotFoundException("Release ID " + releaseId + " not found");
        }

        List<ReleaseTestCase> releaseTestCases = releaseTestCaseRepo.findByReleasesReleaseId(releaseId);

        return releaseTestCases.stream().map(rtc -> {
            ReleaseTestCaseDto dto = new ReleaseTestCaseDto();

            dto.setTestCaseId(rtc.getTestCase().getTestCaseId());
            dto.setTestCaseDescription(rtc.getTestCase().getDescription());
            dto.setTestSteps(rtc.getTestCase().getSteps());
            dto.setSeverityName(rtc.getTestCase().getSeverity().getSeverityName());
            dto.setTypeName(rtc.getTestCase().getType().getTypeName());

            dto.setSubModuleName(rtc.getTestCase().getSubModule().getSubModuleName());
            dto.setProjectName(rtc.getTestCase().getProject().getProjectName());

            dto.setTestDate(rtc.getTestDate());
            dto.setTestTime(rtc.getTestTime());
            dto.setTestCaseStatus(rtc.getTestCaseStatus());

            dto.setReleaseId(rtc.getReleases().getReleaseId());
            return dto;
        }).collect(Collectors.toList());
    }
}
