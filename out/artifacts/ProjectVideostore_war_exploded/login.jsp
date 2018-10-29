<%@ page import="com.entities.EntityAccountsManager" %><%--
  Created by IntelliJ IDEA.
  User: Jays
  Date: 10/25/2018
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<%
    String missingUsername = "";
    String missingPassword = "";
    String unSuccessfulLog = "   ";

%>

<%
    if (request.getAttribute("missingUsername") != null) {
        missingUsername = (String) request.getAttribute("missingUsername");
    }
    if (request.getAttribute("missingPassword") != null) {
        missingPassword = (String) request.getAttribute("missingPassword");
    }
    if (request.getAttribute("unsuccessfulLog") != null) {
        unSuccessfulLog = (String) request.getAttribute("unsuccessfulLog");
    }
%>


<div align="right">
    <p class="nosuccess">&#160<%= unSuccessfulLog%>
    </p>
</div>

<form method="post" name="Login" action="login">
    <p class="form-group">

    <br>
    <p class="second" align="right">
        <label><%= missingUsername%>
        </label>&ensp; &#160<input type="text" name="user" placeholder="Username" size="40"/>

    </p>
    <br>
    <p class="third" align="right">
        <label><%= missingPassword%>
        </label> &nbsp; &#160;<input type="password" name="pass" placeholder="Password" size="40"/>

    </p>
    <br>
    <input type="submit" value="Login">
    </p>
</form>
</body>
</html>
