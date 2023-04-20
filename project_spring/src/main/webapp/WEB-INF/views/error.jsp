<%@ page contentType="text/html;charset=utf-8" isErrorPage = "true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>error</title>
</head>
<body>
서버 장애발생입니다 죄송합니다
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
    <c:forEach items="${pageContext.exception.stackTrace}" var="i">
        <li>${i.toString()}</li>
    </c:forEach>
</ol>
</body>
</html>