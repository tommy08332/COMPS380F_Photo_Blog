<!DOCTYPE html>
<html>
<head>
    <title>Register User</title>
</head>
<body>

<h1>Register Page</h1>

<form:form method="POST" modelAttribute="createPhotoUser">
    <label>Username</label>
    <input type="text" name="username" id="username"/>
    <br/>
    <label>Password</label>
    <input type="password" name="password" id="password"/>
    <br/>
    <label>User Role for testings</label><br>

    ROLE_ADMIN
    <input type="checkbox" name="userRole" id="userRole" value="ROLE_ADMIN"/>
    <br/>
    ROLE_USER
    <input type="checkbox" name="userRole" id="userRole" value="ROLE_USER"/>
    <br/>
    <input type="submit" value="Register"/>


</form:form>

</body>
</html>
