package com.neshan.reportmanager.service;

import com.neshan.reportmanager.DTO.ReportDTO;
import com.neshan.reportmanager.exception.ReportLikeNotFoundException;
import com.neshan.reportmanager.exception.ReportNotFoundException;
import com.neshan.reportmanager.model.Report;
import com.neshan.reportmanager.model.ReportLike;
import com.neshan.reportmanager.repo.ReportLikeRepo;
import com.neshan.reportmanager.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportLikeService {

    private final ReportLikeRepo reportLikeRepo;
    private final ReportRepo reportRepo;


    @Autowired
    public ReportLikeService(ReportLikeRepo reportLikeRepo, ReportRepo reportRepo) {
        this.reportLikeRepo = reportLikeRepo;
        this.reportRepo = reportRepo;
    }

    public ReportLike likeReport(ReportLike reportLike) {
        ReportLike repLike = null;
        boolean saveLike = false;
        long duration = ((20 * 60) * 1000);
        Report report = reportRepo.findReportById(reportLike.getReport().getId()).get();

        try {
            repLike = this.findReportLikeByAccountIdAndReportId(reportLike.getAccount().getId(), reportLike.getReport().getId());
            if (!repLike.isLikeStatus()) { // disliked before
                saveLike = true;
                duration = duration * 2;
                repLike.setLikeStatus(reportLike.isLikeStatus());
            } else
                reportLikeRepo.delete(repLike);

        } catch (ReportLikeNotFoundException e) {
            saveLike = true;
            repLike = reportLike;
        }


        if (saveLike) {
            reportLikeRepo.save(repLike);
            report.getActiveUntil().setTime(report.getActiveUntil().getTime() + duration);

        } else
            report.getActiveUntil().setTime(report.getActiveUntil().getTime() - duration);

        reportRepo.save(report);

        return repLike;
    }

    public ReportLike dislikeReport(ReportLike reportLike) {
        ReportLike repLike = null;
        boolean saveLike = false;
        long duration = ((20 * 60) * 1000);
        Report report = reportRepo.findReportById(reportLike.getReport().getId()).get();

        try {
            repLike = this.findReportLikeByAccountIdAndReportId(reportLike.getAccount().getId(), reportLike.getReport().getId());
            if (repLike.isLikeStatus()) {// liked before
                saveLike = true;
                duration = duration * 2;
                repLike.setLikeStatus(reportLike.isLikeStatus());
            } else
                reportLikeRepo.delete(repLike);

        } catch (ReportLikeNotFoundException e) {
            saveLike = true;
            repLike = reportLike;
        }


        if (saveLike) {
            reportLikeRepo.save(repLike);
            report.getActiveUntil().setTime(report.getActiveUntil().getTime() - duration);

        } else
            report.getActiveUntil().setTime(report.getActiveUntil().getTime() + duration);

        reportRepo.save(report);

        return repLike;
    }


    public ReportDTO findReportDetailsById(Long id) {

        ReportDTO reportDTO = new ReportDTO();

        Optional<Report> optReport = reportRepo.findReportById(id);
        if (optReport.isPresent()) {
            Report report = optReport.get();
            reportDTO.setId(report.getId());
            reportDTO.setType(report.getType());
            reportDTO.setX(report.getX());
            reportDTO.setY(report.getY());
            reportDTO.setAccountName(report.getAccount().getFirstName() + " " + report.getAccount().getLastName());
            reportDTO.setCreatedAt(report.getCreatedAt());
            reportDTO.setActiveUntil(report.getActiveUntil());


            Optional<ReportLike> optRepLike = reportLikeRepo.findReportLikeByAccountIdAndReportId(report.getAccount().getId(), report.getId());
            if (optRepLike.isPresent()) {
                ReportLike reportLike = optRepLike.get();
                reportDTO.setLikedByCurrentUser(reportLike.isLikeStatus());
                reportDTO.setDislikedByCurrentUser(!reportLike.isLikeStatus());
            }

            reportDTO.setLikes(reportLikeRepo.getReportLikesCountByReportId(report.getId()));
            reportDTO.setDislikes(reportLikeRepo.getReportDislikesCountByReportId(report.getId()));

        }

        return reportDTO;
    }

    public List<ReportLike> findAllReports() {
        return reportLikeRepo.findAll();
    }


    public ReportLike findReportLikeById(Long id) {
        return reportLikeRepo.findReportLikeById(id)
                .orElseThrow(() -> new ReportLikeNotFoundException("Report like by id " + id + " was not found"));
    }

    public ReportLike findReportLikeByAccountIdAndReportId(Long accountId, Long reportId) {
        return reportLikeRepo.findReportLikeByAccountIdAndReportId(accountId, reportId)
                .orElseThrow(() -> new ReportLikeNotFoundException("Report like was not found"));
    }

    public void deleteReportLike(Long id) {
        reportLikeRepo.deleteReportLikeById(id);
    }
}
