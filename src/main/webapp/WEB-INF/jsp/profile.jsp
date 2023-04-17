<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.0/bootstrap.min.js"></script>

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
        th,
        td {
            padding: 8px 16px;
            border: 1px solid #ccc;
        }
        th {
            height: 300px;
            background: #eee;
        }
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 300px;
            margin: auto;
            text-align: center;
            font-family: arial;
        }
        button {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: Green;
            text-align: center;
            cursor: pointer;
            width: 100%;
            font-size: 18px;
        }
        ul {
            list-style-type: none;
            width: 100%;
        }
        li {
            display: inline;
            padding-left: 7%;
        }
        button:hover, a:hover {
            opacity: 0.7;
        }
    </style>
    <title>Profile</title>
</head>
<body>
<div style="padding-top: 3%;padding-left: 10%;">
    <ul>

        <li>
            <bold>
                <a href='<c:url value="/photo/upload"/>'>Upload Photo Page</a>
            </bold>
        </li>

        <li>
            <bold>
                <a href='<c:url value="/"/>'>Home Page</a>
            </bold>
        </li>

        <security:authorize access="hasAnyRole('USER', 'ADMIN')">

            <li>
                <bold>
                    <c:url var="logoutUrl" value="/logout" />
                    <form action="${logoutUrl}" method="POST" style="padding-left: 90%">
                        <input style="background-color: red;border: none;text-decoration: none;text-align: center;font-weight: bold;color:yellow;" type="submit" class="link-button" value="Log out">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </bold>
            </li>

        </security:authorize>

    </ul>
</div>
<div class="container container-table">
    <div class="row">
        <div class="text-center col-md-5 col-md-offset-3" style="padding-top:5%;">

            <div class="card">
                <h1><c:out value="${blogUsers.username}"/> </h1>
                <p>Phone : <c:out value="${blogUsers.phoneNum}"/></p>
                <p>Email : <c:out value="${blogUsers.email}"/></p>
                <p>Description : <c:out value="${blogUsers.userDescription}"/></p>
                <p>
                    <a href="<c:url value="/user/profile/edit/${blogUsers.username}"/>">Edit Description</a>
                </p>
            </div>

        </div>
    </div>
</div>


<div class="container">
    <div class="tableFixHead">
        <table>
            <tbody>
            <tr>
                <th>

                    <c:choose>

                    <c:when test="${empty photos}">

                    no photo uploaded
                    </c:when>
                    <c:otherwise>

                    <c:forEach var="i" begin="0" end="${photos.size()-1}">
                    <div style="display: inline;padding-right: 10%;">

                        <img alt="img" src="data:image/${photos.get(i).photoFileType};base64,${photoImg.get(i)}"style="width: 300px;" />
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Title:</h3></br></br></br>
                        </label>
                        <c:out value="${photoDetails.get(i).photoTitle}"/>
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Description :</h3></br></br></br>
                        </label>
                        <c:out value="${photoDetails.get(i).photoDescription}" />
                    </div>
                    <div style="display: inline">
                        <label><h3>Upload-On :</h3></br></br></br>
                            <fmt:formatDate value="${photos.get(i).photoUploadedDatetime}" pattern="yyyy-MM-dd HH:mm:ss" /> </label>
                    </div>
                    </c:forEach>
                    </c:otherwise>
                    </c:choose>

            </tbody>
        </table>
    </div>
</div>

<h2>
    Comments List
</h2>
<div class="container">
    <div class="tableFixHead">
        <table>
            <tbody>
            <c:choose>
                <c:when test="${commentsList.size() eq 0}">
                    <tr>
                        <th>
                            no comment
                        </th>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="i" begin="0" end="${commentsList.size()-1}">
                        <tr>
                            <th>
                                <div style="display: inline;padding-right: 10%;">
                                    <label><h3>Title:</h3></br></br></br>
                                    </label>
                                    <a href="<c:url value="/photo/show/${commentsList.get(i).photo.photoId}"/>" >
                                        <c:out value="${commentsList.get(i).photo.photoDetails.get(0).photoTitle}" escapeXml="false">Unknown</c:out>
                                    </a>
                                </div>
                                <div style="display: inline;padding-right: 10%;">
                                    <label><h3>Comment :</h3></br></br></br>
                                    </label>
                                    <c:out value="${commentsList.get(i).commentText}" escapeXml="false">Null Comment</c:out>
                                </div>
                                <div style="display: inline">
                                    <label><h3>Commented-On :</h3></br></br></br>
                                        <fmt:formatDate value="${commentsList.get(i).commentDatetime}" pattern="yyyy-MM-dd HH:mm:ss" /> </label>
                                </div>
                            </th>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
