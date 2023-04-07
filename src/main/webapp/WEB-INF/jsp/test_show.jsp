<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:ForEach items="${photoUser}" var="user">

    <label>${user.username}</label>
    <label>${user.password}</label>
    <label>${user.userRoles}</label>

</c:ForEach>

</body>
</html>
