<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="shortcut icon" href="http://b.dryicons.com/images/icon_sets/architecture_blueprint_icons_set/png/512x512/question_mark.png" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <%--<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">--%>
    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <title>QUIZ ENGINE</title>
</head>
<body>
<br>
    <h1>QUIZ ENGINE</h1>
<p>
    <form name="quizStartForm" method="POST" action="/quiz/startQuiz">
        Name: <input type="text" name="name" /><br>
        &nbsp;Email: <input type="text" name="email" /><br><br>
        <i class="fa fa-university" aria-hidden="true"></i> Category <select name="category">
            <c:forEach var="category" items="${categories}">
                <option value="<c:out value="${category}"/>"><c:out value="${category}"/></option>
            </c:forEach>
        </select><br><br>
        &nbsp;&nbsp;<i class="fa fa-gavel" aria-hidden="true"></i> Quiz Type <select name="quizType">
            <c:forEach var="quizType" items="${quizTypes}">
                <option value="<c:out value="${quizType}"/>"><c:out value="${quizType}"/></option>
            </c:forEach>
        </select><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-question-circle-o" aria-hidden="true"></i> Type <select name="questionType">
            <c:forEach var="questionType" items="${questionTypes}">
                <option value="<c:out value="${questionType}"/>"><c:out value="${questionType}"/></option>
            </c:forEach>
        </select><br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-wheelchair-alt" aria-hidden="true"></i> Difficulty <select name="difficulty">
            <c:forEach var="difficulty" items="${difficulties}">
                <option value="<c:out value="${difficulty}"/>"><c:out value="${difficulty}"/></option>
            </c:forEach>
        </select><br><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="Let the Quiz Begin">
    </form>
</p>
    <br><br><br><br>
    <br><br><br><br>
    <a href="/admin/">admin</a>
</body>
</html>