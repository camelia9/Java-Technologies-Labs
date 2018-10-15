<%--
  Created by IntelliJ IDEA.
  User: P3700983
  Date: 10/12/2018
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
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
    <title>Title</title>
</head>
<body>
<h1>User List</h1>
<hr>
<table>
    <tr>
        <th>Category</th>
        <th>Key</th>
        <th>Name</th>
    </tr>
    ${recordList}
</table>

</body>
</html>
