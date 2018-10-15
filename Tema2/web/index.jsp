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
    <title>Sign Up</title>
  </head>
  <body>
  <form method="POST" action="/record">
    User:
      <br/>
    <input type="text" name="username" size="20" value=""/> <br/>
      <br/>
    Email:
      <br/>
    <input type="text" name="email" size="20" value=""/> <br/>
      <br/>
    Password:
      <br/>
    <input type="password" name="password" size="20" value=""/> <br/>
      <br/>

      I am not a robot:
      <br/>
      <img src="http://localhost:8080/captcha">
      <br/>
      <input type="text" name="code">
      <br/>
      <br/>
    <input type="submit" name="submit" value="record">
  </form>
  <p>Already have an account? Go <a href="http://localhost:8080/signin.jsp">here</a></p>

  </body>
</html>
