<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <c:choose>

            <c:when test="${fn:length(photoImg)} == 0">

                Picture not found

            </c:when>
            <c:otherwise>

                <div>
                    <label><h3>${photoDetails.photoTitle}</h3></label>
                </div>

                <img alt="img" src="data:image/${photos.photoFileType};base64,${photoImg}" width="500" height="500"/>

                <div>
                    <label>Uploaded by: ${photos.uploadUsername}</label><br>
                </div>
                <div>
                    <label>File name: ${photos.photoFilename}</label><br>
                </div>

                <div>
                    <label>Description: ${photoDetails.photoDescription}</label><br>
                </div>
                <div>
                    <label>Uploaded On: <fmt:formatDate value="${photos.photoUploadedDatetime}" pattern="yyyy-MM-dd hh:mm:ss" /> </label>
                </div>

            </c:otherwise>

            
        </c:choose>

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
