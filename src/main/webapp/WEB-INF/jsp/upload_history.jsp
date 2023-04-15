<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel - Upload History</title>
</head>
<body>

<security:authorize access="hasRole('ADMIN')">

    <h1>Admin Panel - Upload History</h1>

    <ul>

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
                <c:url var="logoutUrl" value="/logout" />
                <form action="${logoutUrl}" method="POST" >
                    <input type="submit" class="link-button" value="Log out">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </bold>
        </li>

    </ul>

    <table border="1">
        <caption>History</caption>
        <thead>

            <tr>
                <th>Photo</th>
                <th>Title</th>
                <th>Description</th>
                <th>Filename</th>
                <th>File Type</th>
                <th>Upload Date</th>
                <th>Uploaded By</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" begin="0" end="${photos.size()-1}">
                <tr>
                    <td>
                        <a href="<c:url value="/photo/show/${photos.get(i).photoId}" /> ">

                            <img alt="img"
                                 src="data:image/${photos.get(i).photoFileType};base64,${fileContent.get(i)}"
                                 style="width:100px"/>
                        </a>
                    </td>
                    <td><c:out value="${photos.get(i).photoDetails.get(0).photoTitle}" /></td>
                    <td><c:out value="${photos.get(i).photoDetails.get(0).photoDescription}" /></td>
                    <td><c:out value="${photos.get(i).photoFilename}" /></td>
                    <td><c:out value="${photos.get(i).photoFileType}" /></td>
                    <td><fmt:formatDate value="${photos.get(i).photoUploadedDatetime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
                    <td><c:out value="${photos.get(i).uploadUsername}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</security:authorize>

</body>
</html>
