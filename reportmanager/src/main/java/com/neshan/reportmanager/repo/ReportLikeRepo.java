package com.neshan.reportmanager.repo;

import com.neshan.reportmanager.model.Report;
import com.neshan.reportmanager.model.ReportLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ReportLikeRepo extends JpaRepository<ReportLike, Long> {

    void deleteReportLikeById(Long id);

    Optional<ReportLike> findReportLikeById(Long id);

    @Query("SELECT r FROM ReportLike r WHERE r.account.id = ?1 and r.report.id = ?2 ")
    Optional<ReportLike> findReportLikeByAccountIdAndReportId(Long accountId, Long reportId);

    @Query("SELECT count(r.likeStatus) FROM ReportLike r WHERE r.likeStatus = true and r.report.id = ?1 ")
    Integer getReportLikesCountByReportId(Long reportId);

    @Query("SELECT count(r.likeStatus) FROM ReportLike r WHERE r.likeStatus = false and r.report.id = ?1 ")
    Integer getReportDislikesCountByReportId(Long reportId);

}
