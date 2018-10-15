<%@ page import="model.Login"%>
<jsp:useBean id="login" scope="session"  type="model.Login"/>
<jsp:setProperty name="login" property="*"/>
<html>
<head>
    <title>Thankyou for your request</title>
</head>
<body>
<h1>Thankyou <%=login.getUsername()%> for log in</h1>
<p>Your password is <%=login.getPassword()%></p>
</body>
</html>