package com.neshan.reportmanager.controller;


import com.neshan.reportmanager.DTO.ReportDTO;
import com.neshan.reportmanager.model.Report;
import com.neshan.reportmanager.model.ReportLike;
import com.neshan.reportmanager.model.ReportList;
import com.neshan.reportmanager.repo.ReportLikeRepo;
import com.neshan.reportmanager.service.ReportLikeService;
import com.neshan.reportmanager.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;
    private final ReportLikeService reportLikeService;

    @Autowired
    public ReportController(ReportService reportService, ReportLikeService reportLikeService) {
        this.reportService = reportService;
        this.reportLikeService = reportLikeService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllAccounts() {
        List<Report> reports = reportService.findAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") Long id) {
        Report report = reportService.findReportById(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/get-details/{id}")
    public ResponseEntity<ReportDTO> getReportDetailsById(@PathVariable("id") Long id) {
        ReportDTO report = reportLikeService.findReportDetailsById(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        long duration = ((60 * 60) * 1000);

        report.setCreatedAt(new Timestamp(new Date().getTime()));
        report.setActiveUntil(new Timestamp(report.getCreatedAt().getTime() + duration));

        Report rep = reportService.addReport(report);
        return new ResponseEntity<>(rep, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Report> updateReport(@RequestBody Report report) {
        Report rep = reportService.updateReport(report);
        return new ResponseEntity<>(rep, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Report> deleteReport(@PathVariable("id") Long id) {
        reportService.deleteReport(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/active-reports")
    public ResponseEntity<ReportList> getActiveReports() {
        List<Report> reports = reportService.findActiveReports();
        ReportList reportList = new ReportList();
        reportList.setReports(reports);
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity likeReport(@RequestBody ReportLike reportLike) {
        reportLikeService.likeReport(reportLike);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/dislike")
    public ResponseEntity dislikeReport(@RequestBody ReportLike reportLike) {
        reportLikeService.dislikeReport(reportLike);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
