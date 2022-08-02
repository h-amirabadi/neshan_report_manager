<%@ page import="com.neshan.controller.ReportController" %>
<%@ page import="com.neshan.model.Report" %>
<%@ page import="com.neshan.DTO.ReportDTO" %>
<%@ page import="com.neshan.model.Account" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: hamir
  Date: 7/29/2022
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }

        JSONArray jsonArray = new JSONArray(new String(json.getBytes(), "UTF-8"));
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);

%>
<tr>
    <td>
        <span class="item-first-name"><%=obj.getString("firstName")%></span>
    </td>
    <td>
        <span class="item-last-name text-reset"><%=obj.getString("lastName")%></span>
    </td>
    <td>
        <span class="item-email"><%=obj.getString("eMail")%></span>
    </td>
    <td>
        <a class="" href="javascript:deleteAccount('<%=obj.getLong("id")%>')" >
            <i class="fa fa-window-close"></i>
        </a>
        <%--<!-- Dropdown -->
        <div class="dropdown">
            <a class="dropdown-ellipses dropdown-toggle" href="#" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fe fe-more-vertical"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right">
                <a href="javascript:&lt;%&ndash;('<%=obj.getString("id")%>')&ndash;%&gt;" class="dropdown-item">
                    <%=$.getText("edit", lt)%>
                </a>
                <a href="javascript:removeAccountFromLane('<%=request.getParameter("cid")%>','<%=obj.getInt("index")%>')"
                   class="dropdown-item">
                    <%=$.getText("delete", lt)%>
                </a>
            </div>
        </div>--%>
    </td>
</tr>
<%
            }
        }
    } catch (Exception ex) {
        response.setStatus(420);
    }
%>