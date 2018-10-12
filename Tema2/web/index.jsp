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
  <form method="POST" action="/record">
    Category:
      <br/>
    <input type="text" name="category" size="20" value=""/> <br/>
      <br/>
    Key:
      <br/>
    <input type="text" name="key" size="20" value=""/> <br/>
      <br/>
    Name:
      <br/>
    <input type="text" name="name" size="20" value=""/> <br/>
      <br/>

      Are you a robot?:
      <br/>
      <img src="http://localhost:8080/captcha">
      <br/>
      <input type="text" name="capcha">
      <br/>
      <br/>


    <input type="submit" name="submit" value="record">
  </form>

  </body>
</html>
