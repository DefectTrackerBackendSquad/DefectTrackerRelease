package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/releaseTestCase")
@CrossOrigin
public class ReleaseTestCaseController {

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;


    @GetMapping("/view/{releaseId}")
    public ResponseEntity<List<ReleaseTestCaseDto>> getTestCasesByReleaseId(@PathVariable String releaseId) {
        List<ReleaseTestCaseDto> testCases = releaseTestCaseService.getTestCasesByReleaseId(releaseId);
        System.out.println("Received releaseId = " + releaseId);
        return ResponseEntity.ok(testCases);
    }

    @GetMapping("/{releaseTestCaseId}")
    public StandardResponse getTestCase(@PathVariable String releaseTestCaseId) {
        try {
            TestCaseResponseDTO dto = releaseTestCaseService.getTestCaseByReleaseTestCaseId(releaseTestCaseId);
            return new StandardResponse("success", "Test case retrieved successfully", dto, 200);
        } catch (ResourceNotFoundException e) {
            return new StandardResponse("failure", e.getMessage(), null, 4000);
        } catch (Exception e) {
            return new StandardResponse("failure", "Unexpected error occurred", null, 5000);
        }
    }






}
