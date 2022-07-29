<%@ page import="com.neshan.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: hamir
  Date: 7/26/2022
  Time: 9:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="inc/html-header.jsp" %>
</head>
<body>
<%--<button onclick="x()" style="width: 100px; height: 50px">alert</button>--%>
<%--<div id="divMapLoading" >&lt;%&ndash;<img src="/media/image/loading/l3.gif" style="width: 100%;height: 100%"/>&ndash;%&gt;</div>--%>
<%
    if (session.getAttribute("acc") != null) {
        Account account = (Account) session.getAttribute("acc");
%>
<input type="hidden" id="hdAccountId" name="hdAccountId" value="<%=account.getId()%>">
<div id="mapMainDiv" class="map-div"></div>
<div id="divTools" class="form-row" style="display: none">


        <%
} else {
%>
    <div class="" style="background-color: #4e555b;width: 100%;height: 100%">
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
</body>
<%@include file="inc/html-footer.jsp" %>
<script>
    $(document).ready(function () {
        // mapInitWorkspace('mapMainDiv');
        initMap('mapMainDiv');
    })
</script>
</html>
