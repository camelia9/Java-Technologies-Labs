<%@ page import="model.Login"%>
<html>
<head>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        * {box-sizing: border-box}
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }
    </style>
    <title>Welcome</title>
</head>
<body>
<jsp:useBean id="user" type="model.Record" scope="request"/>
<h1>Welcome</h1>
<hr>
<h3>Welcome <%=user.getUsername()%></h3>
<h4>Your email is <%=user.getEmail()%></h4>
</body>
</html>