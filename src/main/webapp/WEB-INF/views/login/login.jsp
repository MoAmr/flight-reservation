<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>User Login</title>
</head>
<body>
<form action="login" method="post">
    <pre>
        <h2>Login</h2>
    User Name: <input type="text" name="email"/>
    Password: <input type="password" name="password"/>
    <input type="submit" value="login"/>
        ${msg}
    </pre>
</form>
</body>
</html>