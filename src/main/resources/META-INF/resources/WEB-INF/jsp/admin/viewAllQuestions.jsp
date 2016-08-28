<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="http://b.dryicons.com/images/icon_sets/architecture_blueprint_icons_set/png/512x512/question_mark.png" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <title>View All Questions</title>
</head>
<body>
<h1>View All Questions</h1>
<br><br>
<table>
    <thead>
    <tr>
        <th> Id </th>
        <th> Question Category </th>
        <th> Question Type </th>
        <th> Question Difficulty </th>
        <th> Question </th>
        <th> Answer </th>
        <th> Delete </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="quizQuestion" items="${quizQuestions}">
        <tr>
            <td><a href="/admin/viewQuestion?id=${quizQuestion.id}"><c:out value="${quizQuestion.id}" /></a></td>
            <td><c:out value="${quizQuestion.category}" /></td>
            <td><c:out value="${quizQuestion.questionType}" /></td>
            <td><c:out value="${quizQuestion.difficulty}" /></td>
            <td><c:out value="${quizQuestion.question}" /></td>
            <td>
                <c:if test="${quizQuestion.questionType == 'MULTIPLE_CHOICE'}">
                    <c:out value="${quizQuestion.correctMultipleChoiceAnswer}" />
                </c:if>
                <c:if test="${quizQuestion.questionType == 'TRUE_FALSE'}">
                    <c:out value="${quizQuestion.trueOrFalse}" />
                </c:if>
                <c:if test="${quizQuestion.questionType == 'CODE'}">
                    <c:forEach var="codeLine" items="${quizQuestion.codeLines}">
                        <c:if test="${not empty codeLine}">
                            <c:out value="${codeLine}"/><br>
                        </c:if>
                    </c:forEach>
                </c:if>
            </td>
            <td><a href="/admin/deleteQuestion?id=${quizQuestion.id}">DELETE<c:out value="${quizQuestion.id}" /></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
||
<a href="/restData/getAllQuestions">Bulk Download Questions</a>
||
<a href="/admin/uploadQuestions">Bulk Upload Questions</a>
||
<a href="admin/deleteAllQuestions">Bulk Delete All Questions</a>

<br><br>
<a href="/">HOME</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/admin/addQuestion">Add New Question</a>
</body>
</body>
</html>