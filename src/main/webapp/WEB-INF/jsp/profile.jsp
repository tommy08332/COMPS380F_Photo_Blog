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
                <a href='<c:url value="/"/>'>Home Page</a>
            </bold>
        </li>
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
</div>
<div class="container container-table">
    <div class="row">
        <div class="text-center col-md-5 col-md-offset-3" style="padding-top:5%;padding-bottom: 7%">

            <div class="card">
                <h1><c:out value="${blogUsers.username}"/></h1>
                <p>Phone : <c:out value="${blogUsers.phoneNum}"/></p>
                <p>Email : <c:out value="${blogUsers.email}"/></p>
                <p>Description : <c:out value="${blogUsers.userDescription}"/></p>
                <p>
                    <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                        <security:authorize access="principal.username == '${blogUsers.username}'">

                            <button>
                                <a style="color: whitesmoke" href="<c:url value="/user/profile/edit/${blogUsers.username}"/>">Edit
                                    Description</a>
                            </button>

                        </security:authorize>

                    </security:authorize>

                </p>
            </div>

        </div>
    </div>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6">
            <h2>
                Photos
            </h2>
            <div class="tableFixHead">
                <table>
                    <tbody>
                    <tr>
                        <th>
                            <c:choose>
                            <c:when test="${empty photos}">
                            <div style="padding-left: 45%">
                                no photo uploaded
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
                                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                </svg>
                            </div>
                            </c:when>
                            <c:otherwise>
                            <c:forEach var="i" begin="0" end="${photos.size()-1}">
                            <div style="display: inline;padding-right: 5%;">
                                <img alt="img" src="data:image/<c:out value="${photos.get(i).photoFileType}" />;base64,<c:out value="${photoImg.get(i)}" />"
                                     style="width: 300px;"/>
                            </div>
                            <div style="display: inline;padding-right: 8%;">
                                <label><h3>Title:</h3></br>
                                    <c:out value="${photoDetails.get(i).photoTitle}"/>
                                </label>
                            </div>
                            <div style="display: inline;padding-right: 5%;">
                                <label><h3>Description :</h3></br>
                                    <c:out value="${photoDetails.get(i).photoDescription}"/>
                                </label>
                            </div>
                            <div style="display: inline">
                                <label><h3>Upload-On :</h3></br>
                                    <fmt:formatDate value="${photos.get(i).photoUploadedDatetime}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/> </label>
                            </div>
                            </c:forEach>
                            </c:otherwise>
                            </c:choose>

                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-sm-6">
            <h2>
                Comments List
            </h2>
            <div class="tableFixHead">
                <table>
                    <tbody>
                    <c:choose>
                        <c:when test="${commentsList.size() eq 0}">
                            <tr>
                                <th>
                                    <div style="padding-left: 45%">
                                        no comment
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor"
                                             class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
                                            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                        </svg>
                                    </div>
                                </th>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="0" end="${commentsList.size()-1}">
                                <tr>
                                    <th style="padding-left: 10%">
                                        <div style="display: inline;padding-right: 10%;">
                                            <label style="padding-bottom: 8%;"><h3>Title:</h3></br>

                                                <a href="<c:url value="/photo/show/${commentsList.get(i).photo.photoId}"/>">
                                                    <c:out value="${commentsList.get(i).photo.photoDetails.get(0).photoTitle}"
                                                           escapeXml="false">Unknown</c:out>
                                                </a>
                                            </label>
                                        </div>
                                        <div style="display: inline;padding-right: 10%;">
                                            <label><h3>Comment :</h3></br>

                                                <c:out value="${commentsList.get(i).commentText}">Null Comment</c:out>
                                            </label></div>
                                        <div style="display: inline">
                                            <label><h3>Commented-On :</h3></br>
                                                <fmt:formatDate value="${commentsList.get(i).commentDatetime}"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/> </label>
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
    </div>
</div>


</body>
</html>
