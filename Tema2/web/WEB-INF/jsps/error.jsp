
<%--
  Created by IntelliJ IDEA.
  User: P3700983
  Date: 10/12/2018
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
    body {font-family: Arial, Helvetica, sans-serif;}
    * {box-sizing: border-box}
    </style>
    <title>Error</title>
</head>
<body>
<div id="_head">%s</div>

<h4>The following error has occurred :${error}</h4>
<br/>
<p>Please return <a href="http://localhost:8080/">here</a></p>
<div id="_footer">%s</div>
</body>
</html>
