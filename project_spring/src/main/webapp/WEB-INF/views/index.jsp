<%--
  Created by IntelliJ IDEA.
  User: pjwon
  Date: 2023-04-01
  Time: 오전 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId==null ? '/login' : '/logout'}"/>
<c:set var="loginOut" value="${loginId==null ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="/css/menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <c:if test="${loginId==null}">
            <li><a href="<c:url value='/join'/>">Sign in</a></li>
        </c:if>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>