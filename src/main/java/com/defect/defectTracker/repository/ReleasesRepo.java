package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Project;
import com.defect.defectTracker.entity.Releases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReleasesRepo extends JpaRepository<Releases, String> {
    boolean existsByReleaseId(String releaseId);

    @Query("SELECT r FROM Releases r WHERE " +
            "(:releaseId IS NULL OR r.releaseId = :releaseId) AND " +
            "(:releaseName IS NULL OR r.releaseName LIKE %:releaseName%) AND " +
            "(:releaseType IS NULL OR r.releaseType = :releaseType) AND " +
            "(:releaseDate IS NULL OR r.releaseDate = :releaseDate) AND " +
            "(:projectId IS NULL OR r.project.id = :projectId)")
    List<Releases> search(
            @Param("releaseId") String releaseId,
            @Param("releaseName") String releaseName,
            @Param("releaseType") String releaseType,
            @Param("releaseDate") Date releaseDate,
            @Param("projectId") Long projectId);

    Optional<Releases> findByReleaseId(String releaseId);
    List<Releases> findByProject(Project project);



}
