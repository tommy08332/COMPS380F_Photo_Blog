<!DOCTYPE html>
<html>
<style>
    ul {
        list-style-type: none;
        width: 100%;
    }
    li {
        display: inline;
        padding-left: 7%;
    }
    table, th, td {
        border: 1px solid black;
    }
    th {
        width: 10%;
    }
    td {
        font-weight: bold;
        padding: 0;
        text-align: center;
    }
    #adduser {
        background-color: white;
        border: none;
    }
</style>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <title>Admin Panel - User Management</title>
</head>
<body>
<security:authorize access="hasRole('ADMIN')">
    <div class="container">
        <div class="row">
            <div class="col-lg">


                <h1>
                    <bold>Admin Panel - User management</bold>
                </h1>

                <ul style="padding-bottom: 15%;padding-top: 1.5%;">

                    <li>
                        <bold>
                            <a href='<c:url value="/"/>'>Home Page</a>
                        </bold>
                    </li>

                    <li>
                        <bold>
                            <a style="text-decoration: none;" href="<c:url value="/admin/panel/user"/>">User
                                Management</a>
                        </bold>
                    </li>

                    <li>
                        <bold>
                            <a style="text-decoration: none;" href="<c:url value="/admin/panel/history"/>">Upload
                                History</a>
                        </bold>
                    </li>
                    <li>
                        <bold>
                            <button style="background-color: white;border:none;"><a style=" text-decoration: none;"
                                href="<c:url value="/admin/panel/registration/create"/>">Create new user</a></button>
                        </bold>
                    </li>
                    <li>
                        <bold>
                            <button style="background-color: white;border:none;"><a style=" text-decoration: none;"
                                href="<c:url value="/photo/upload"/>">Upload photo</a></button>
                        </bold>
                    </li>
                    <li>

                        <c:url var="logoutUrl" value="/logout"/>
                        <form action="${logoutUrl}" method="POST" style="padding-left: 90%">
                            <input style="color: #0000EE;background-color:white;border: none;text-decoration: none;"
                                   type="submit" class="link-button" value="Log out">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>

                    </li>

                </ul>


                    <%-- list all user--%>
                <table>
                    <caption>User Management</caption>
                    <thead>
                    <tr>
                        <th style="text-align: center;">Username</th>
                        <th style="text-align: center;">Password</th>
                        <th style="text-align: center;">Email</th>
                        <th style="text-align: center;">Phone Number</th>
                        <th style="text-align: center;">User Description</th>
                        <th style="text-align: center;">User Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty users}">

                        <c:forEach items="${users}" var="user">
                            <tr>

                                <td>
                                    <bold>
                                        <a href="<c:url value="/user/profile/${user.username}"/>">
                                        <c:out value="${user.username}"/>
                                        </a>
                                    </bold>
                                </td>
                                <td>
                                    <bold><c:out value="${fn:substringAfter(user.password, '{noop}')}"/></bold>
                                </td>
                                <td>
                                    <bold><c:out value="${user.email}"/></bold>
                                </td>
                                <td>
                                    <bold><c:out value="${user.phoneNum}"/></bold>
                                </td>
                                <td>
                                    <bold><c:out value="${user.userDescription}"/></bold>
                                </td>
                                <td>

                                    <bold><c:forEach items="${user.userRoles}" var="role" varStatus="roleStatus">
                                        <c:if test="${!roleStatus.first}">, </c:if>
                                        <c:out value="${fn:substringAfter(role.userRole, 'ROLE_')}"/>
                                    </c:forEach></bold>
                                </td>
                                <td style="background-color: #649113;width: 10%;text-decoration: none;"><a
                                        style="margin-left: 25%;color:black"
                                        href="<c:url value="/admin/panel/edit/user/${user.username}"/> ">Edit</a></td>
                                <td style="background-color: red;width: 10%;text-decoration: none;"><a
                                        style="margin-left: 25%;color: aliceblue"
                                        href="<c:url value="/admin/panel/delete/user/${user.username}"/> ">Delete</a>
                                </td>

                            </tr>

                        </c:forEach>

                    </c:if>


                    </tbody>

                </table>

                <table>


                </table>


            </div>
        </div>
    </div>

</security:authorize>
</body>
</html>
