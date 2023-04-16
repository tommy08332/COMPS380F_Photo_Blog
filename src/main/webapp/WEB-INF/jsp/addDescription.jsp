<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Page</title>
</head>
<body>

    <h1>Add Page</h1>

    <form:form method="POST" modelAttribute="adescription">

        <form:label path="userDescription">Description: </form:label>
        <form:input type="text" path="userDescription" value="${blogUsers.userDescription}"/>
        <br/>


        <br/>
        <input type="submit" value="Add Description"/>


    </form:form>

</body>
</html>
