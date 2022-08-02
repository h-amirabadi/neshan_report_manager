<%@ page import="com.neshan.service.AccountService" %>
<%@ page import="com.neshan.model.Account" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hamir
  Date: 8/1/2022
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Neshan RM - Accounts</title>
    <%@include file="inc/html-header.jsp" %>
</head>
<body>
<%
    if (session.getAttribute("acc") != null) {
        Account account = (Account) session.getAttribute("acc");
%>
<div class="bg-color" style="height: 100%; overflow-y: auto">
    <h4 class=" ">
        <a href="/"><%=account.getFirstName() + " " + account.getLastName()%></a>
    </h4>
    <div id="divAccountForm" class="ml-3" style="height: 400px; ">
        <%--        <input type="hidden" id="hd" name="hd" value="<%=request.getParameter("y")%>">--%>

        <h1 class="display-5 text-center ">
            Account management
        </h1>
        <p class="text-muted text-center mb-3">
            <a href="home">Home</a>
        </p>

        <form id="frmAccount" onsubmit="return doAddUpdateAccount();">
            <div class="row">
                <div class="col-6">
                    <label>
                        First name
                    </label>
                    <input type="text" id="tbFirstName" name="firstName" required <%--autocomplete="off"--%>
                           class="form-control "
                           placeholder="John">
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label>
                        Last name
                    </label>
                    <input type="text" id="tbLastName" name="lastName" required <%--autocomplete="off"--%>
                           class="form-control "
                           placeholder="Sterling">
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label>
                        E-Mail
                    </label>
                    <input type="text" id="tbEmail" name="eMail" required autocomplete="off"
                           class="form-control "
                           placeholder="example@mail.com">
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                    <label>
                        Password
                    </label>
                    <input type="password" id="tbPassword" name="password" required
                           class="form-control "
                           placeholder="Password">
                </div>
            </div>

            <div class="row">
                <div class="col-3">
                    <button type="submit" class="btn btn-lg btn-block btn-primary mt-3 mb-3">
                        Save
                    </button>
                </div>
                <div class="col-3">
                    <button type="button" class="btn btn-lg btn-block btn-dark mt-3 mb-3" onclick="clearForm();">
                        Cancel
                    </button>
                </div>
            </div>

        </form>
    </div>

    <div id="divAccountList" class="m-2 mt-3" style="background-color: #999999">
        <div class="" id="divAccTable" >
            <table id="tblAccounts"  class="table <%--table-sm--%> table-nowrap">
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>E-Mail</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody id="tblAccountBody" >

                </tbody>
            </table>
        </div>

    </div>

</div>

<%
} else {
%>
<div class="bg-color" style="width: 100%;height: 100%">
    <div class="row justify-content-center">
        <div class="col-12 col-md-5 col-xl-4 my-5">
            <!-- Heading -->
            <h2 class="display-5 text-center mb-3">
                You have to sign in first...
            </h2>
            <p class="text-muted text-center mb-5">
                <a href="/">Go to login page</a>
            </p>
        </div>
    </div>
</div>
<%}%>
<%@include file="inc/html-footer.jsp" %>
</body>
<script>
    $(document).ready(function () {
        refreshAccounts();
    })
</script>
</html>
