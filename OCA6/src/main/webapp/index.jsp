<%-- 
    Document   : index
    Created on : Nov 12, 2018, 8:49:20 PM
    Author     : Camelia
--%>

<html>
<head>
    <title>Test Database</title>
</head>
<body>
    <h2>Test Database Connection</h2>

    <div>
        <h3>Connection pool operations</h3>
        <form action="/benchmark" method="get">
            <input type="hidden" name="connType" value="poolInsert"/>
            <input type="submit" value="Insert"/>
        </form>

        <form action="/benchmark" method="get">
            <input type="hidden" name="connType" value="poolSelect"/>
            <input type="submit" value="Select"/>
        </form>

      
    </div>
</body>
</html>