<%@ page import="com.entities.EntityAccountsManager" %>
  Created by IntelliJ IDEA.
  User: Jays
  Date: 10/26/2018
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="css/successful_reg_or_log.css">
</head>
<body>

<%
    EntityAccountsManager entityAccountsManager = new EntityAccountsManager();
%>

<%String isLogged = "No";%>

<%
    if (request.getAttribute("isLogged") != null) {
        if (request.getAttribute("isLogged") != "No") {
            isLogged = String.valueOf(request.getAttribute("isLogged"));
%> <h2 align="right">Logged in as <%=isLogged%>
</h2> <%

} else {
%>
<div class="reg_or_log" align="right">
    <a href="register.jsp">REGISTER</a>
    <a href="login.jsp">LOGIN</a>
</div>
<%
    }
} else {
%>
<div class="reg_or_log" align="right">
    <a href="register.jsp">REGISTER</a>
    <a href="login.jsp">LOGIN</a>
</div>
<%
    }
%>

<p>Welcome to MARVEL's videostore!</p>
<div class="buttons">
    
    <form method="post" name="Login" action="register.jsp">
        <input type="submit" value="Create account">
    </form>
    <form method="post" name="Login" action="take_book">
        <input type="submit" value="Check when certain tape will be back">
    </form>
    <form method="post" name="Login" action="take_book">
        <input type="submit" value="Return videotape">
    </form>
    <form method="post" name="Login" action="take_book">
        <input type="submit" value="Delete account">
    </form>
</div>

</body>
</html>
