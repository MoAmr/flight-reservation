<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>Register User</title>
</head>
<body>
<form action="registerUser" method="post">
    <pre>
        <h2>User Registration</h2>
    First Name: <input type="text" name="firstName"/>
    Last Name: <input type="text" name="lastName"/>
    User Name: <input type="text" name="email"/>
    Password: <input type="password" name="password"/>
    Confirm Password: <input type="password" name="confirmPassword"/>
    <input type="submit" value="register"/>
    </pre>
</form>
</body>
</html>