<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <style><%@include file="./css/login.css"%></style>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>
<body>

<div class="wrapper itim-regular">
    <h1>QUIZIFY</h1>
    <div class="form_wrapper">
        <p class="itim-regular">login</p>
        <form action="login" method="post">
            <label id="user-name-label">
                <input type="text" placeholder="username or email" name="username" class="itim-regular">
            </label>
            <div></div>
            <label id="password-label">
                <input type="password" placeholder="password" name="password" class="itim-regular">
            </label>
            <button class="itim-regular">login</button>
        </form>
        <div class="anchor_wrapper">
            <a href="<c:url value="/register"/>">Not a member? Register now</a>
        </div>
    </div>
</div>

</body>
</html>