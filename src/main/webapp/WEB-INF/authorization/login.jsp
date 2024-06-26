<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
    <style><%@include file="./css/register.css"%></style>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
</head>
<body>

<div class="wrapper itim-regular">
    <h1>QUIZIFY</h1>
    <div class="form_wrapper">
        <p class="itim-regular">register</p>
        <form action="register" method="post">
            <label id="email-label">
                <input type="email" placeholder="email" name="email" class="itim-regular">
            </label>
            <div></div>
            <label id="user-name-label">
                <input type="text" placeholder="username" name="username" class="itim-regular">
            </label>
            <div></div>
            <label id="password-label">
                <input type="password" placeholder="password" name="password" class="itim-regular">
            </label>
            <button class="itim-regular">register</button>
        </form>
        <div class="anchor_wrapper">
            <a href="<c:url value="/login"/>">Member already? Log in</a>
        </div>
    </div>
</div>

</body>
</html>