<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Sign In</title>
</head>
<body>
<form action="/signin" method="post">
    <div class="container">
        <h1>Sign In</h1>
        <hr>
        <label><b>Username</b></label>
        <br/>
        <input type="text" placeholder="Enter Username" name="username" required>
        <br/>
        <label><b>Password</b></label>
        <br/>
        <input type="password" placeholder="Enter Password" name="password" required>
        <br/>

        <button type="submit" class="signupbtn">Login</button>
    </div>
</form>
</body>
</html>
