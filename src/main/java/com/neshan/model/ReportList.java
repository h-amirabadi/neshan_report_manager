package com.neshan.model;

import java.util.ArrayList;
import java.util.List;

public class ReportList {
    private List<Report> reports;

    public ReportList() {
        this.reports = new ArrayList<>();
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
