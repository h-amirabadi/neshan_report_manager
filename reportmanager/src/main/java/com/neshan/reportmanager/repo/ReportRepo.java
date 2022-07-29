package com.neshan.reportmanager.repo;

import com.neshan.reportmanager.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ReportRepo extends JpaRepository<Report, Long> {
    void deleteReportById(Long id);

    Optional<Report> findReportById(Long id);

    @Query("SELECT r FROM Report r WHERE r.activeUntil > ?1 ")
    List<Report> findActiveReports(Timestamp now);
}
