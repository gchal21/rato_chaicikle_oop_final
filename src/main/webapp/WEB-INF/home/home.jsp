<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="./css/home.css"%></style>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
    <title>home</title>
</head>
<body>
<div class="wrapper">
    <section class="content_wrapper">
        <div class="title_wrapper">
            <p class="title itim-regular">QUIZIFY</p>
        </div>


    </section>
    <section class="sidebar">
        <div class="sidebar_header">
            <p class="itim-regular"><%=request.getAttribute("userName")%></p>
        </div>

        <jsp:include page="./messages.jsp" />

    </section>
</div>
</body>
</html>
