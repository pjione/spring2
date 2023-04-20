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
    <title>hi</title>
    <link rel="stylesheet" href="/css/menu.css">
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
    <script>
        if('${msg}'=="delete ok"){
            alert("삭제 되었습니다.")
        }
        if('${msg}'=="delete error"){
            alert("삭제에 실패하였습니다.")
        }
        if('${msg}'=="write ok"){
            alert("등록 되었습니다.")
        }
    </script>
</div><div style="text-align:center">
    <button type="button" id="writeBtn" onclick = "location.href='<c:url value="/board/write"/>'">글쓰기</button>
    <table border="1" align="center">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>이름</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.bno}</td>
            <td><a href="<c:url value='/board/read?bno=${board.bno}&page=${ph.page}&pageSize=${ph.pageSize}'/>">${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.reg_date}</td>
            <td>${board.view_cnt}</td>
        </tr>
        </c:forEach>
    </table>
    <div>
        <c:if test="${ph.showPrev}">
            <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
        </c:if>
    </div>
</div>

</body>
</html>