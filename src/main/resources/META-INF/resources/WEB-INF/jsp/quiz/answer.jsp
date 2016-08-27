<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="http://b.dryicons.com/images/icon_sets/architecture_blueprint_icons_set/png/512x512/question_mark.png" type="image/x-icon">
    <title>QUIZ ANSWER</title>
    <link type="text/css" rel="stylesheet" href="/css/style.css">

</head>
<body>
<br>
<h1>QUIZ ANSWER</h1>
<c:if test="${not empty correct}">
    <div style="color:#00ff33;font-weight: bold;">
        <c:out value="${correct}" />
    </div>
</c:if>
<c:if test="${not empty incorrect}">
    <div style="color:red;font-weight: bold;">
        <c:out value="${incorrect}" />
    </div>
</c:if>
<br>
<c:if test="${not empty tracker}">
    You have <c:out value="${tracker.correct}" /> correct <br><c:out value="${tracker.incorrect}" /> incorrect.
</c:if>
<br>
QUESTION: <c:out value="${quizQuestion.question}" /><br>
ANSWER:
<c:if test="${quizQuestion.questionType == 'MULTIPLE_CHOICE'}">
    <c:out value="${quizQuestion.correctMultipleChoiceAnswer}" />
</c:if>
<c:if test="${quizQuestion.questionType == 'TRUE_FALSE'}">
    <c:out value="${quizQuestion.trueOrFalse}" />
</c:if>
<c:if test="${quizQuestion.questionType == 'CODE'}">
<br>
    <c:choose>
            <c:when test="${quizQuestion.category == 'SQL'}">SQL Category:
            <pre><code class="sql">
            </c:when>
            <c:when test="${quizQuestion.category == 'HTML'}">HTML Category:
                <pre><code class="html">
            </c:when>
            <c:when test="${quizQuestion.category == 'CSS'}">CSS Category:
                <pre><code class="css">
            </c:when>
            <c:otherwise>
                <pre><code class="java">Java Category:
            </c:otherwise>
        </c:choose>
                <div style="color:#00ff33;font-weight: bold;">
        <c:forEach var="codeLine" items="${quizQuestion.codeLines}">
            <c:if test="${not empty codeLine && fn:length(codeLine)>0}">
                <c:out value="${codeLine}"/>
            </c:if>
        </c:forEach>
                </div>
        </code></pre>
    </c:if>
<br>

<form action="/quiz/nextQuestion" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <br><input type="submit" value="Next Question" /><br>  <a href="/">Home</a><br>
                    <a href="quizResults">Exit and go to results</a>
</form>
</body>
</html>
