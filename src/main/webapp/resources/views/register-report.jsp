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
    Account account = (Account) session.getAttribute("acc");
%>
<div class="container">

    <div class="">
        <input type="hidden" id="hdAccountId" name="hdAccountId" value="<%=account.getId()%>">
        <input type="hidden" id="hdX" name="hdX" value="<%=request.getParameter("x")%>">
        <input type="hidden" id="hdY" name="hdY" value="<%=request.getParameter("y")%>">
        <div class="row mt-4">
            <div class="col-12">
                <label class="m-auto" for="cbType">Report Type
                </label>
                <div class="input-group mt-1">
                    <select class="form-control " onchange=""
                            id="cbType" name="cbType">
                        <%for (Report.TYPE type : Report.TYPE.values()) {%>
                        <option value="<%=type.getID()%>"><%=type%></option>
                        <%}%>
                    </select>
                </div>
            </div>
        </div>

        <div class="row mt-5">
            <div class="col-12 ">
                <button class="w-100 btn btn-lg btn-primary" onclick="registerReport();">
                    <i class="fa fa-check"></i>
                    <label>Register Report
                    </label>
                </button>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-12">
                <button class="w-100 btn btn-lg btn-secondary" onclick="closeReportPane()">
                    <i class="fa fa-close"></i>
                    <label>Cancel
                    </label>
                </button>
            </div>
        </div>
    </div>

</div>
