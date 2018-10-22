<%@ page import="model.Login"%>
<%@ taglib uri="/WEB-INF/tlds/recordTag" prefix="record" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/function_tag.tld" prefix="f"%>
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
<record:record username= "${user.getUsername()}"  password="${user.getPassword()}"/>
<hr>
<h2>Users List</h2>

<table border="2px">
    <tr>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${f:allRecords()}" var="record">
        <tr>
            <td>${record.getUsername()}</td>
            <td>${record.getEmail()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</body>
</html>