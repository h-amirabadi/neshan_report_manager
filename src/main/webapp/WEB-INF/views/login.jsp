<%--
  Created by IntelliJ IDEA.
  User: hamir
  Date: 7/27/2022
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@include file="inc/html-header.jsp" %>
</head>
<body>
<%
    if(session.getAttribute("acc") != null)
        session.removeAttribute("acc");
%>
<div class="" style="background-color: darkblue;width: 100%;height: 100%" >
    <div class="row justify-content-center">
        <div class="col-12 col-md-5 col-xl-4 my-5">
            <!-- Heading -->
            <h1 class="display-5 text-center mb-3">
               Neshan Report Manager
            </h1>

            <!-- Subheading -->
            <p class="text-muted text-center mb-5">

            </p>

            <form id="frmLogin" onsubmit="return doLogin();">
                <div class="form-group">
                    <label>
                        Enter your e-mail
                    </label>
                    <div class="input-group input-group-merge">
                        <input type="text" id="email" name="email" required <%--autocomplete="off"--%>
                               class="form-control <%--form-control-appended--%> pass-form-ctrl"
                               placeholder="example@mail.com">
                    </div>
                </div>

                <div class="form-group">
                    <label>
                        Enter your password
                    </label>
                    <div class="input-group input-group-merge">
                        <input type="password" id="password" name="password" required
                               class="form-control <%--form-control-appended--%> pass-form-ctrl"
                               placeholder="Password">
                        <%-- <div class="input-group-append">
                           <span class="input-group-text">
                             <i class="fe fe-eye"></i>
                           </span>
                         </div>--%>
                    </div>
                </div>

                <button type="submit" class="btn btn-lg btn-block btn-primary mt-3 mb-3">
                    Sign in
                </button>

            </form>

        </div>
    </div> <!-- / .row -->
</div> <!-- / .container -->

<%@include file="inc/html-footer.jsp" %>
</body>
</html>
