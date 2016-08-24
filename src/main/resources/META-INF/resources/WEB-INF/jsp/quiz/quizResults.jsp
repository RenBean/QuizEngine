<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="http://b.dryicons.com/images/icon_sets/architecture_blueprint_icons_set/png/512x512/question_mark.png" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <title>QUIZ RESULTS</title>
</head>
<body>
<br>
<h1>QUIZ RESULTS</h1>

<c:if test="${not empty tracker.name}">
    <div style="color:dodgerblue;font-weight: bold;">
        name: <c:out value="${tracker.name}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker.email}">
    <div style="color:dodgerblue;font-weight: bold;">
        email: <c:out value="${tracker.email}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker.totalQuestions}">
    <div style="color:#00ff33;font-weight: bold;">
        number of questions asked: <c:out value="${tracker.totalQuestions}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker.correct}">
    <div style="color:#00ff33;font-weight: bold;">
        correct: <c:out value="${tracker.correct}" />
    </div>
</c:if>
<br>
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