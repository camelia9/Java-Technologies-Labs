<%@ page import="model.Login"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<jsp:useBean id="user" type="model.Record" scope="request"/>
<h3>Welcome <%=user.getUsername()%></h3>
<p>Your email is <%=user.getEmail()%></p>
</body>
</html>