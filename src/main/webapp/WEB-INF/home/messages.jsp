<%@ page import="entities.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Time" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style><%@include file="./css/messages.css"%></style>

<div class="messages_container">
    <%List<Message> messages = (List<Message>)request.getAttribute("messages");%>
    <p class="itim-regular messages_header">Messages</p>

    <%if(messages.isEmpty()){%>
    <p class="itim-regular no_message">No New Messages</p>
    <%}else{%>
    <div class="messagebox">
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            for(Message m : messages) {
                String formattedDate = sdf.format(m.getSendDate());
        %>
        <div class="border_box message_wrapper">
            <div>
                <div>
                    <span class="itim-regular"><%=m.getSenderUsername()%></span>
                    <span class="itim-regular"><%=formattedDate%></span>
                </div>
                <p class="itim-regular"><%=m.getContent()%></p>
            </div>
        </div>
        <%}%>
    </div>

    <% }%>


</div>