package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.ReleaseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReleaseTestCaseRepo extends JpaRepository<ReleaseTestCase, Long> {
    Optional<ReleaseTestCase> findByReleaseTestCaseId(String releaseTestCaseId);
    void deleteByReleaseTestCaseId(String releaseTestCaseId);
    boolean existsByReleaseTestCaseId(String releaseTestCaseId);
    List<ReleaseTestCase> findByReleasesReleaseId(String releaseId);
}
