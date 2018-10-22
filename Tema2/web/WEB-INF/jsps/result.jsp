<%--
  Created by IntelliJ IDEA.
  User: P3700983
  Date: 10/12/2018
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/function_tag.tld" prefix="f"%>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: center

        }

        td, th {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }
        body {font-family: Arial, Helvetica, sans-serif;}
        * {box-sizing: border-box}
    </style>
    <title>User List</title>
</head>
<!---   <body>
<h1>User List</h1>
<hr>
<table>
    <tr>
        <th>User</th>
        <th>Mail</th>
        <th>Password</th>
    </tr>
    ${recordList}
</table>

</body>
</html>
!--->
<body>
<h2>Users List</h2>

<table border="2px">
    <tr>
        <th>${language}</th>
        <th>${word}</th>
        <th>${definition}</th>
    </tr>
    <c:forEach items="${f:allRecords()}" var="record">
        <tr>
            <td>${record.getUsername()}</td>
            <td>${record.getEmail()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>


