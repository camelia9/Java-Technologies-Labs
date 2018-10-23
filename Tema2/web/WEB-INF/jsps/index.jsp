<%--
  Created by IntelliJ IDEA.
  User: P3700983
  Date: 10/12/2018
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tlds/helloTag" prefix="say" %>
<html>
<style>
  body {font-family: Arial, Helvetica, sans-serif;
    align-items: center;
    }
  * {box-sizing: border-box}

  /* Full-width input fields */
  input[type=text], input[type=password] {
    width: 40%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;

  }

  input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
  }

  hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
  }

  /* Set a style for all buttons */
  button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 30%;
    opacity: 0.9;
  }

  button:hover {
    opacity:1;
  }

  /* Float cancel and signup buttons and add an equal width */
  .cancelbtn, .signupbtn {
    float: left;
    width: 40%;
  }

  /* Add padding to container elements */
  .container {
    padding: 20px;
    vertical-align: middle;
  }

  /* Clear floats */
  .clearfix::after {
    content: "";
    clear: both;
    display: table;
  }

  /* Change styles for cancel button and signup button on extra small screens */
  @media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
      width: 40%;
    }
  }
</style>
  <head>
    <title>Sign Up</title>
  </head>
  <body>
  <div id="_head">%s</div>
  <form method="POST" action="/record">
      <div class="container">
          <h1>Sign Up</h1>
          <hr>
          <p>Please fill in this form to create an account.</p>
          <label><b>Username</b></label>
      <br/>
    <input type="text" name="username" size="20" value=""/> <br/>
      <br/>
          <label><b>Email</b></label>
      <br/>
    <input type="text" name="email" size="20" value=""/> <br/>
      <br/>
          <label><b>Password</b></label>
      <br/>
    <input type="password" name="password" size="20" value=""/> <br/>
      <br/>
          <label><b>I am not a robot</b></label>
      <br/>
      <img src="http://localhost:8080/captcha">
      <br/>
      <input type="text" name="code">
      <br/>
      <br/>
          <button type="submit" class="signupbtn" value="record">Login</button>
      </div>
  </form>
  <br/>
  <p>Already have an account? Go <a href="/signin">here</a></p>
  <div id="_footer">%s</div>
  </body>
</html>
