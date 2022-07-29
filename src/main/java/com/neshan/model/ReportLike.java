package com.neshan.model;


import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "report_like")
public class ReportLike {
    @Id
    @SequenceGenerator(
            name = "report_like_sequence",
            sequenceName = "report_like_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "report_like_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;

    private boolean likeStatus;


    @ManyToOne
    @JoinColumn(
            name = "account_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_report_like_account_id"
            )
    )
    private Account account;

    @ManyToOne
    @JoinColumn(
            name = "report_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_report_like_report_id"
            )
    )
    private Report report;


    public ReportLike() {
    }

    public ReportLike(boolean likeStatus, Account account, Report report) {
        this.likeStatus = likeStatus;
        this.account = account;
        this.report = report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}