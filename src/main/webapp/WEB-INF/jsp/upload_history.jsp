<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        table, th, td {
            border: 1px solid black;
        }
        th{
            width:10%;
        }
        td{
            font-weight: bold;
            padding: 0;
            text-align: center;
        }

    </style>

    <title>Admin Panel - Upload History</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg">
            <security:authorize access="hasRole('ADMIN')">

                <h1><bold>Admin Panel - Upload History</bold></h1>

                <ul style="padding-bottom: 15%;padding-top: 1.5%;">

                    <li>
                        <bold>
                            <a href="<c:url value="/admin/panel/user"/>">User Management</a>
                        </bold>
                    </li>

                    <li>
                        <bold>
                            <a href="<c:url value="/admin/panel/history"/>">Upload History</a>
                        </bold>
                    </li>

                    <li>
                        <bold>
                            <c:url var="logoutUrl" value="/logout"/>
                            <form action="${logoutUrl}" method="POST" style="padding-left: 90%">
                                <input style="background-color: red;border: none;text-align: center;font-weight: bold;color:yellow;" type="submit" class="link-button" value="Log out">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </bold>
                    </li>

                </ul>

                <table border="1">
                    <caption>History</caption>
                    <thead>

                    <tr>
                        <th style="text-align: center;">Photo</th>
                        <th style="text-align: center;">Title</th>
                        <th style="text-align: center;">Description</th>
                        <th style="text-align: center;">Filename</th>
                        <th style="text-align: center;">File Type</th>
                        <th style="text-align: center;">Upload Date</th>
                        <th style="text-align: center;">Uploaded By</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${!empty photos}">

                        <c:forEach var="i" begin="0" end="${photos.size()-1}">
                            <tr>
                                <td>
                                    <a href="<c:url value="/photo/show/${photos.get(i).photoId}" /> ">

                                        <img alt="img"
                                             src="data:image/${photos.get(i).photoFileType};base64,${fileContent.get(i)}"
                                             style="width:100px"/>
                                    </a>
                                </td>
                                <td><c:out value="${photos.get(i).photoDetails.get(0).photoTitle}"/></td>
                                <td><c:out value="${photos.get(i).photoDetails.get(0).photoDescription}"/></td>
                                <td><c:out value="${photos.get(i).photoFilename}"/></td>
                                <td><c:out value="${photos.get(i).photoFileType}"/></td>
                                <td><fmt:formatDate value="${photos.get(i).photoUploadedDatetime}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><c:out value="${photos.get(i).uploadUsername}"/></td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    </tbody>
                </table>
            </security:authorize>
        </div>
    </div>
</div>
</body>
</html>
