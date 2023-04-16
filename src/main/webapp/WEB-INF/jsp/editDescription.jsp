<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Page</title>
</head>
<body>

<h1>Edit Page</h1>

<form:form method="POST" modelAttribute="edescription">

    <form:label path="userDescription">Description: </form:label>
    <form:input type="text" path="userDescription" value="${blogUsers.userDescription}"/>
    <br/>

    <br/>
    <input type="submit" value="Edit Description"/>


</form:form>

</body>
</html>
