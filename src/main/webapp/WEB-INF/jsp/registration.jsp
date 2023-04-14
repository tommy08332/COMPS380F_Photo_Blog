<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register User</title>
</head>
<body>

<h1>Register Page</h1>

<form:form method="POST" modelAttribute="createPhotoUser">

    <form:label path="username">Username: </form:label>
    <form:input type="text" path="username" />
    <br/>

    <form:label path="password">Password: </form:label>
    <form:input type="text" path="password" />
    <br/>

    <form:label path="phoneNum">Phone number: </form:label>
    <form:input type="text" path="phoneNum" />
    <br/>

    <form:label path="email">Email: </form:label>
    <form:input type="text" path="email" />
    <br/>

    <security:authorize access="hasRole('ADMIN')">

        <form:label path="userRole">User Roles: </form:label>

        <form:checkbox path="userRole" value="ROLE_USER" />Normal User
        <form:checkbox path="userRole" value="ROLE_ADMIN" />Admin User

    </security:authorize>


    <br/>
    <input type="submit" value="Register"/>


</form:form>

</body>
</html>
