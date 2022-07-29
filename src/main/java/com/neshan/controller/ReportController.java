package com.neshan.controller;


import com.neshan.DTO.ReportDTO;
import com.neshan.model.Report;
import com.neshan.model.ReportLike;
import com.neshan.model.ReportList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/find/{id}")
    public Report getReportById(@PathVariable("id") Long id) {
        Report report = restTemplate.getForObject("http://localhost:8081/api/v1/report/find/" + id + "",
                Report.class);

        return report;
    }
    @GetMapping("/get-details/{id}")
    public ReportDTO getReportDetailsById(@PathVariable("id") Long id) {
        ReportDTO reportDTO = restTemplate.getForObject("http://localhost:8081/api/v1/report/get-details/" + id + "",
                ReportDTO.class);

        return reportDTO;
    }

    @GetMapping("/active-reports")
    public ResponseEntity<List<Report>> getActiveReport() {
        ReportList reportList = restTemplate.getForObject("http://localhost:8081/api/v1/report/active-reports",
                ReportList.class);

        return new ResponseEntity<>(reportList.getReports(), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<ReportLike> likeReport(@RequestBody ReportLike reportLike) {
        ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:8081/api/v1/report/like",
                reportLike, ResponseEntity.class);

        return responseEntity;
    }

    @PostMapping("/dislike")
    public ResponseEntity<ReportLike> dislikeReport(@RequestBody ReportLike reportLike) {
        ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:8081/api/v1/report/dislike",
                reportLike, ResponseEntity.class);

        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        ResponseEntity<Report> rep = restTemplate.postForEntity("http://localhost:8081/api/v1/report/add",
                report, Report.class);

        return rep;
    }
}
