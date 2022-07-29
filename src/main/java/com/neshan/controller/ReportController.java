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
        System.out.println("=====going to report manager microservice from neshan microservice start");
        Report report = restTemplate.getForObject("http://localhost:8081/api/v1/report/find/" + id + "",
                Report.class);
        System.out.println("=====report manager microservice end.");

        return report;
    }
    @GetMapping("/get-details/{id}")
    public ReportDTO getReportDetailsById(@PathVariable("id") Long id) {
        System.out.println("=====going to report manager microservice from neshan microservice start");
        ReportDTO reportDTO = restTemplate.getForObject("http://localhost:8081/api/v1/report/get-details/" + id + "",
                ReportDTO.class);
        System.out.println("=====report manager microservice end.");

        return reportDTO;
    }

    @GetMapping("/active-reports")
    public ResponseEntity<List<Report>> getActiveReport() {
        System.out.println("=====going to report manager microservice from neshan microservice start");
        ReportList reportList = restTemplate.getForObject("http://localhost:8081/api/v1/report/active-reports",
                ReportList.class);
        System.out.println("=====report manager microservice end.");

        return new ResponseEntity<>(reportList.getReports(), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<ReportLike> likeReport(@RequestBody ReportLike reportLike) {
        System.out.println("=====going to report manager microservice from neshan microservice start");

        ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:8081/api/v1/report/like",
                reportLike, ResponseEntity.class);

        System.out.println("=====report manager microservice end.");

        return responseEntity;
    }

    @PostMapping("/dislike")
    public ResponseEntity<ReportLike> dislikeReport(@RequestBody ReportLike reportLike) {
        System.out.println("=====going to report manager microservice from neshan microservice start");

        ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:8081/api/v1/report/dislike",
                reportLike, ResponseEntity.class);

        System.out.println("=====report manager microservice end.");

        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        System.out.println("=====going to report manager microservice from neshan microservice start");

        ResponseEntity<Report> rep = restTemplate.postForEntity("http://localhost:8081/api/v1/report/add",
                report, Report.class);

        System.out.println("=====report manager microservice end.");

        return rep;
    }
}
