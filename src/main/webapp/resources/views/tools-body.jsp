<%@ page import="com.neshan.controller.ReportController" %>
<%@ page import="com.neshan.model.Report" %>
<%@ page import="com.neshan.DTO.ReportDTO" %>
<%@ page import="com.neshan.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: hamir
  Date: 7/29/2022
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ReportController reportController = new ReportController();
    Account account = (Account) session.getAttribute("acc");
    ReportDTO report = reportController.getReportDetailsById(Long.parseLong(request.getParameter("rid")), account.getId());
%>
<div class="container">

    <div class="">
        <input type="hidden" id="hdReportId" name="hdReportId" value="<%=report.getId()%>">
        <div class="row mt-1">
            <div class="col-12">
                <label>Report Type: <%=Report.TYPE.valueOf(report.getType())%>
                </label>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">
                <label>Submitted by: <%=report.getAccountName()%>
                </label>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">
                <label>Creation time: <%=report.getCreatedAt()%>
                </label>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col-12 ">
                <button class="w-100 btn btn-lg <%=report.isLikedByCurrentUser() ? "btn-primary" : "btn-outline-primary"%>" onclick="likeReport();">
                    <i class="fa fa-thumbs-up"></i>
                    <label>Like (<%=report.getLikes()%>)
                    </label>
                </button>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">
                <button class="w-100 btn btn-lg <%=report.isDislikedByCurrentUser() ? "btn-danger" : "btn-outline-danger" %>" onclick="dislikeReport();">
                    <i class="fa fa-thumbs-down"></i>
                    <label>Dislike (<%=report.getDislikes()%>)
                    </label>
                </button>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">
                <button class="w-100 btn btn-lg btn-secondary" onclick="closeReportPane()">
                    <i class="fa fa-close"></i>
                    <label>Close
                    </label>
                </button>
            </div>
        </div>
    </div>

</div>
