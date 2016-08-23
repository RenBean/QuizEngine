<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.PrintStream" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <title>QUIZ QUESTION</title>
</head>
<body>
<br>
<h1>QUIZ QUESTION</h1>

QUESTION: <c:out value="${quizQuestion.question}" />
<br><br><br>
<%--<output type="text" name="Iterable" value="?">:<c:out value="${quizQuestion.Iterable}" /></output>--%>
<form name="questionForm" method="POST" action="/quiz/questionAnswer">
<c:if test="${quizQuestion.questionType == 'MULTIPLE_CHOICE'}">

    <%--<% Map<Integer, String> randomizeQuestion = new HashMap<>();--%>
        <%--randomizeQuestion.put(0,"Zero");--%>
        <%--randomizeQuestion.put(1,"One");--%>
        <%--randomizeQuestion.put(2,"Two");--%>
        <%--randomizeQuestion.put(3,"Three");--%>

        <%--System.out.println("random question 0="+randomizeQuestion.get(0));--%>
        <%--System.out.println(randomizeQuestion.get((int)(Math.random()*4))); %>--%>

    <%--//Java--%>
    <%--Map<String, String> countryCapitalList = new HashMap<String, String>();--%>
    <%--randomQuestionSource.put("United States", "Washington DC");--%>
    <%--randomQuestionSource.put("India", "Delhi");--%>
    <%--randomQuestionSource.put("Germany", "Berlin");--%>
    <%--randomQuestionSource.put("France", "Paris");--%>
    <%--randomQuestionSource.put("Italy", "Rome");--%>

    <%--request.setAttribute("randomQuestion", randomQuestionSource);--%>

    <%--//JSP--%>
    <%--<c:forEach var="answer" items="${quizQuestion.correctMultipleChoiceAnswer;--%>
    <%--quizQuestion.wrongMultipleChoiceAnswer1;quizQuestion.wrongMultipleChoiceAnswer2;--%>
    <%--quizQuestion.wrongMultipleChoiceAnswer3}">--%>
        <%--QAK: ${answer.key}  - QAV: ${answer.value}--%>
    <%--</c:forEach>--%>


    <input type="radio" name="multiAnswer" value="yes"> :<c:out value="${quizQuestion.correctMultipleChoiceAnswer}" /><br>
    <input type="radio" name="multiAnswer" value="no"> :<c:out value="${quizQuestion.wrongMultipleChoiceAnswer1}" /><br>
    <input type="radio" name="multiAnswer" value="no"> :<c:out value="${quizQuestion.wrongMultipleChoiceAnswer2}" /><br>
    <input type="radio" name="multiAnswer" value="no"> :<c:out value="${quizQuestion.wrongMultipleChoiceAnswer3}" /><br>
</c:if>
<c:if test="${quizQuestion.questionType == 'TRUE_FALSE'}">
    True: <input type="radio" name="trueFalseAnswer" value="true"><br>
    False: <input type="radio" name="trueFalseAnswer" value="false">
</c:if>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<br><br><input type="submit" name="Show Me">
</form>
</body>
</html>