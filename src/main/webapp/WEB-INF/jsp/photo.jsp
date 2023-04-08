<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<html>
<head>

    <style>
        .tableFixHead {
            overflow-y: auto;
            height: 700px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            height: 50%;
        }

        th,
        td {
            padding: 8px 16px;
            border: 1px solid #ccc;
        }

        th {
            background: #eee;
        }
    </style>

    <title>Photo</title>
</head>
<body>


<button>login</button>
<div class="row">
    <div class="col-sm-6" style="padding-left:15%;">
        <%--    photos.photoData is a bytes array that stored the photo file data    --%>
        <%--${photos.photoData}--%>
        <img alt="img" src="data:${photo.photoTitle}/jpeg;base64,${photoImg}"/>


        <%--show photo--%>
        <%--        <c:choose>--%>
        <%--            <c:when test="${fn:length(photos) == 0}">--%>

        <%--                Picture not found--%>

        <%--            </c:when>--%>
        <%--            <c:otherwise>--%>
        <%--                <img src="${test_photo_data}" width="500" height="500"><br>--%>
        <div>
            <label>Uploaded by: ${photos.username}</label><br>
        </div>
        <div>
            <label>File name: ${photos.photoFilename}</label><br>
        </div>
        <%--            </c:otherwise>--%>
        <%--        </c:choose>--%>
        <div>
            <label><h3>${photos.photoTitle}</h3></label><br>
        </div>
        <img src="https://cdn.ebaumsworld.com/mediaFiles/picture/1151541/84693449.png" width="500" height="500"><br>
        <div>
            <label>Description: ${photos.photoDescription}</label><br>
        </div>
        <div>
            <label>Uploaded On: ${photos.photoUploadedDatetime}</label><br>
        </div>
    </div>


    <div class="col-sm-6" style="padding-right:15%;">

        <div class="tableFixHead">
            <table>
                <thead>
                <tr>
                    <th>
                        <h3>
                            <bold>COMMENTS</bold>
                        </h3>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>

                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>

                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>

                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>

                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        <div>
                            <div style="padding-bottom: 5%">
                                date : 7/4/2023
                                <button>Edit</button>
                                <button>Delete</button>
                            </div>
                        </div>
                        <div>
                            <label>This is comment !!!!!</label>
                        </div>
                    </th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>


</div>
</body>
</html>
