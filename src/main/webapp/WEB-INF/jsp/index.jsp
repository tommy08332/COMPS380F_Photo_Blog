<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style>
        ul {
            list-style-type: none;
            width: 100%;
        }
        li {
            display: inline;
            padding-left: 7%;
        }
        .tableFixHead {
            overflow-y: auto; /* make the table scrollable if height is more than 200 px  */
            height: 800px; /* gives an initial height of 200px to the table */
        }
        table {
            border-collapse: collapse; /* make the table borders collapse to each other */
            width: 100%;
            height: 50%;
        }
        td {
            padding: 8px 16px;
            border: 1px solid #ccc;
        }
        td {
            height: 300px;
            background: #eee;
        }
        .link-button {
            background: none;
            border: none;
            color: #1a0dab;
            text-decoration: underline;
            cursor: pointer;
        }

        img {
            width:300px;
            margin-left: auto;
            margin-right: auto;
            display: block;
        }
    </style>
    <title>Welcome to PhotoBlog</title>
</head>
<body>

<c:choose>
    <c:when test="${param.success != null}">

        <h1>Welcome to PhotoBlog! </h1>

        <ul>
            <li>
                <bold>
                    You've been logged out, please press the <a href="<c:url value="/"/>">Home page</a> back to home page.
                </bold>
            </li>

        </ul>



    </c:when>
    <c:otherwise>

        <div class="container">
            <div class="row">
                <h1>Welcome to PhotoBlog! </h1>
                    <%--        nav bar--%>

                    <%--  if the user logged in, show the username here      --%>
                <c:if test="${!empty uname}">
                    Hi, <c:out value="${uname}" />

                </c:if>


                <ul>

                    <security:authorize access="hasAnyRole('USER', 'ADMIN')">

                        <li>
                            <bold>
                                <a href="<c:url value="/user/profile/${uname}"/>">Profile</a>
                            </bold>
                        </li>

                    </security:authorize>

                    <c:if test="${empty uname}">
                        <li>
                            <bold>
                                <a href="<c:url value="/login"/>">Login</a>
                            </bold>
                        </li>
                        <li>
                            <bold>
                                <a href="<c:url value="/registration/create"/>">Registration</a>
                            </bold>
                        </li>

                    </c:if>

                    <security:authorize access="hasRole('ADMIN')">

                        <li>
                            <bold>
                                <a href='<c:url value="/admin/panel/user"/>'>Admin panel</a>
                            </bold>
                        </li>

                    </security:authorize>

                    <security:authorize access="hasAnyRole('USER', 'ADMIN')">

                        <li>
                            <bold>
                                <a href='<c:url value="/photo/upload"/>'>Upload Photo Page</a>
                            </bold>
                        </li>

                    </security:authorize>

                    <security:authorize access="hasAnyRole('USER', 'ADMIN')">

                        <li>
                            <bold>
                                <c:url var="logoutUrl" value="/logout" />
                                <form action="${logoutUrl}" method="POST" style="padding-left: 90%">
                                    <input style="color: #0000EE;background-color:white;border: none;text-decoration: none;" type="submit" class="link-button" value="Log out">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </bold>
                        </li>

                    </security:authorize>

                </ul>


                <c:choose>

                    <c:when test="${empty photos}">

                        Please Upload photo

                    </c:when>


                    <c:otherwise>

                        <div class="tableFixHead">
                            <table>

                                <tbody>

                                <c:forEach var="i" begin="0" end="${photos.size()-1}">

                                <c:if test="${i % 3 == 0}">
                                <tr>
                                    </c:if>

                                    <td>
                                        <a href="<c:url value="/photo/show/${photos.get(i).photoId}"/>">

                                            <img alt="img"
                                                 src="data:image/${photos.get(i).photoFileType};base64,${fileContent.get(i)}"/>
                                        </a>

                                    </td>

                                    </c:forEach>

                                </tbody>

                            </table>

                        </div>

                    </c:otherwise>

                </c:choose>

            </div>

        </div>
    </c:otherwise>

</c:choose>

</body>
</html>
