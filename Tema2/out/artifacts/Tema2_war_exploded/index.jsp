<%--
  Created by IntelliJ IDEA.
  User: P3700983
  Date: 10/12/2018
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Input</title>
  </head>
  <body>
  <form method="POST" action="RecordServlet">
    Category:
    <input type="text" name="category" size="20" value=""/> <br/>
    <br/>
    Key:
    <input type="text" name="key" size="20" value=""/> <br/>
    <br/>
    Name:
    <input type="text" name="name" size="20" value=""/> <br/>

    <input type="submit" name="submit" value="login">
  </form>
  </body>
</html>
