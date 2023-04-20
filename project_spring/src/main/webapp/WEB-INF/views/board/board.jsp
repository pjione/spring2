<%--
  Created by IntelliJ IDEA.
  User: pjwon
  Date: 2023-04-01
  Time: 오전 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginId" value="${pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId==null ? '/login' : '/logout'}"/>
<c:set var="loginOut" value="${loginId==null ? 'Login' : 'ID='+=loginId}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="/css/menu.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
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
<script>
    if('${msg}'=="write error"){
        alert("등록에 실패하였습니다. 다시 시도해 주세요.")
    }
</script>
<div style="text-align:center">
    <h3>게시물 ${mode eq "new" ? "등록" : "보기"}</h3>
    <form action = "" id="form">
        <input type = "text" name = "title" value="${board.title}" ${mode eq "new" ? '' : "readonly"}>
        <textarea name = "content" id = "" cols="30" row = "10" ${mode eq "new" ? '' : "readonly"}>${board.content}</textarea>
        <input type = "hidden" name = "bno" value="${board.bno}" readonly}>
        <button type="button" id="writeBtn" class="btn">등록</button>
        <c:if test="${mode != 'new'}">
            <button type="button" id="modifyBtn" class="btn">수정</button>
            <button type="button" id="removeBtn" class="btn">삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    $(document).ready(function(){
        $('#listBtn').on("click", function(){
            location.href = "<c:url value='/board/list/'/>?page=${page}&pageSize=${pageSize}";
        });
        $('#writeBtn').on("click", function(){
            let form = $('#form');
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();
        });
        $('#removeBtn').on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
            form.attr("method", "post");
            form.submit();
        });

    });
</script>
</body>
</html>