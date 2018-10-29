<%@ page import="com.entities.EntityAccountsManager" %><%--
  Created by IntelliJ IDEA.
  User: Goran
  Date: 24.10.2018 г.
  Time: 19:58 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<html>
<head>
    <title>Registration</title>

    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>

<%
    String missingEmail = "";
    String missingUsername = "";
    String missingPassword = "";
    String missingRetypePassword = "";
    String unSuccessfulReg = "   ";
%>

<%
    if (request.getAttribute("missingEmail") != null) {
        missingEmail = (String) request.getAttribute("missingEmail");
    }
    if (request.getAttribute("missingUsername") != null) {
        missingUsername = (String) request.getAttribute("missingUsername");
    }
    if (request.getAttribute("missingPassword") != null) {
        missingPassword = (String) request.getAttribute("missingPassword");
    }
    if (request.getAttribute("missingRetypePassword") != null) {
        missingRetypePassword = (String) request.getAttribute("missingRetypePassword");
    }
    if (request.getAttribute("unSuccessfulReg") != null) {
        unSuccessfulReg = (String) request.getAttribute("unSuccessfulReg");
    }
%>

<div align="right">
    <p class="nosuccess">&#160<%= unSuccessfulReg%>
    </p>
</div>

<form method="post" name="Login" action="register">
    <p class="form-group">
    <p class="first" align="right">
        <label><%= missingEmail%>
        </label> &ensp; &#160<input type="text" name="email" placeholder="E-mail" size="40"/>
    </p>
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
    <p class="fourth" align="right">
        <label><%= missingRetypePassword%>
        </label> &#160<input type="password" name="retypePass" placeholder="Retype password" size="40"/>
    </p>
    <br>
    <input type="submit" value="Register">
    </p>
</form>

<form method="post" name="Login" action="take_book">
    <input type="submit" value="Get book">
</form>

</body>
</html>