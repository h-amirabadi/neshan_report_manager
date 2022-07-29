package com.neshan.reportmanager.service;

import com.neshan.reportmanager.DTO.ReportDTO;
import com.neshan.reportmanager.exception.ReportNotFoundException;
import com.neshan.reportmanager.model.Report;
import com.neshan.reportmanager.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepo reportRepo;

    @Autowired
    public ReportService(ReportRepo reportRepo) {
        this.reportRepo = reportRepo;
    }

    public Report addReport(Report report) {
        return reportRepo.save(report);
    }


    public List<Report> findAllReports() {
        return reportRepo.findAll();
    }

    public List<Report> findActiveReports() {
        return reportRepo.findActiveReports(new Timestamp(new Date().getTime()));
    }

    public Report updateReport(Report report) {
        return reportRepo.save(report);
    }


    public Report findReportById(Long id) {
        return reportRepo.findReportById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report by id " + id + " was not found"));
    }


    public void deleteReport(Long id) {
        reportRepo.deleteReportById(id);
    }
}
