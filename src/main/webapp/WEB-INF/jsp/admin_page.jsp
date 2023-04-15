<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<style>
    table, th, td {
        border:1px solid black;
    }
    table, th, td, img{
        /*max-width: 400px;*/
        /*max-height: 400px;*/
    }
</style>
<head>
    <title>Admin Panel</title>
</head>
<body>

<security:authorize access="hasRole('ADMIN')">


    <h1>Admin Page</h1>

    <a href="<c:url value="/registration/create"/>" >Create new user</a>

    <%-- list all user--%>
    <table>
        <caption>User Management</caption>
        <thead>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>User Description</th>
            <th>User Role</th>
        </tr>
        </thead>
        <tbody>
                    <c:if test="${!empty users}">

                        <c:forEach items="${users}" var="user">
                            <tr>

                                <td>${user.username}</td>
                                <td>${fn:substringAfter(user.password, "{noop}")}</td>
                                <td>${user.email}</td>
                                <td>${user.phoneNum}</td>
                                <td>${user.userDescription}</td>
                                <td><c:forEach items="${user.userRoles}" var="role" varStatus="roleStatus">
                                    <c:if test="${!roleStatus.first}">
                                        ,
                                    </c:if>

                                    ${fn:substringAfter(role.userRole, "ROLE_")}
                                </c:forEach></td>
                                <td><button>Edit</button></td>


                            </tr>

                        </c:forEach>

                    </c:if>



        </tbody>

    </table>



    <%-- list all photo and edit--%>

    <table>



    </table>

</security:authorize>






</body>
</html>
