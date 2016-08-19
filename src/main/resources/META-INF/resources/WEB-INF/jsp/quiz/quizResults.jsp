<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%--TODO--%>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <title>QUIZ RESULTS</title>
</head>
<body>
<h1>QUIZ RESULTS</h1>

<c:if test="${not empty tracker.name}">
    <div style="color:darkblue;font-weight: bold;">
        name: <c:out value="${tracker.name}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker.email}">
    <div style="color:darkblue;font-weight: bold;">
        email: <c:out value="${tracker.email}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker.numberOfQuestions}">
    <div style="color:green;font-weight: bold;">
        number of questions asked: <c:out value="${tracker.numberOfQuestions}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker.correct}">
    <div style="color:green;font-weight: bold;">
        correct: <c:out value="${tracker.correct}" />
    </div>
</c:if>
<c:if test="${not empty tracker.incorrect}">
    <div style="color:red;font-weight: bold;">
        incorrect: <c:out value="${tracker.incorrect}" />
    </div>
</c:if>

<br><br>

<br><br>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<a href="/">Quiz Me Again</a> | <a href="/admin/">admin</a>
</body>
</html>