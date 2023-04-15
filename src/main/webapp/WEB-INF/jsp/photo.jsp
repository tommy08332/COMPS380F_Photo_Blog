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

                img {
                    max-width: 500px;
                    max-height: 500px;
                }
            </style>

            <title>Photo</title>
        </head>

        <body>


            <button>login</button>
            <div class="row">
                <div class="col-sm-6" style="padding-left:15%;">
                    <%-- photos.photoData is a bytes array that stored the photo file data --%>

                        <c:choose>

                            <c:when test="${fn:length(photoImg)} == 0">

                                Picture not found

                            </c:when>
                            <c:otherwise>

                                <div>
                                    <label>
                                        <h3>
                                            <c:out value="${photoDetails.photoTitle}" />
                                        </h3>
                                    </label>
                                </div>

                                <!--                 <img alt="img" src="data:image/${photos.photoFileType};base64,${photoImg}" width="500" height="500"/> -->
                                <img alt="img" src="data:image/${photos.photoFileType};base64,${photoImg}" />

                                <div>
                                    <label>Uploaded by:
                                        <c:out value="${photos.uploadUsername}" />
                                    </label><br>
                                </div>
                                <div>
                                    <label>File name:
                                        <c:out value="${photos.photoFilename}" />
                                    </label><br>
                                </div>

                                <div>
                                    <label>Description:
                                        <c:out value="${photoDetails.photoDescription}" />
                                    </label><br>
                                </div>
                                <div>

                                    <label>Uploaded On:
                                        <fmt:formatDate value="${photos.photoUploadedDatetime}"
                                            pattern="yyyy-MM-dd hh:mm:ss" />
                                    </label>
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
                                <c:choose>
                                    <c:when test="${empty comments}">
                                        <tr>
                                            <div>
                                                There doesn't have comments.
                                            </div>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="i" begin="0" end="${comments.size()-1}">
                                            <tr>
                                                <div style="padding-bottom: 5%">
                                                    date :
                                                    <fmt:formatDate value="${comments.get(i).commentDatetime}"
                                                        pattern="yyyy-MM-dd hh:mm:ss" />
                                                </div>
                                                <div>
                                                    <label id="label${comments.get(i).commentId}">
                                                        <c:out value="${comments.get(i).commentText}" escapeXml="false">
                                                            Null
                                                            Comment</c:out>
                                                    </label>
                                                    <security:authorize access="hasAnyRole('ADMIN')">
                                                        <form:form method="post" modelAttribute="comment"
                                                            id="editForm${comments.get(i).commentId}"
                                                            style="display:none;">
                                                            <textarea rows="4" cols="50" name="commentText"
                                                                form="editForm${comments.get(i).commentId}">
                                                                <c:out value="${comments.get(i).commentText}" escapeXml="false">
                                                                    Null Comment
                                                                </c:out>
                                                            </textarea>
                                                            <!-- <input type="text" value="${comments.get(i).commentText}"> -->
                                                            <input type="hidden" name="commentId" value="${comments.get(i).commentId}">
                                                            <input type="hidden" name="order" value="UPDATE">
                                                            <input type="submit" value="Submit">
                                                        </form:form>
                                                        <button onclick="
                                                            document,getElementsByTagName('label').style.display = 'block';
                                                            document.getElementsByTagName('form').style.display = 'none';
                                                            document.getElementById('deleteform').style.display = 'block';
                                                            document.getElementsByTagName('button').style.display = 'block';
                                                            document,getElementById('label${comments.get(i).commentId}').style.display = 'none';
                                                            document.getElementById('editForm${comments.get(i).commentId}').style.display = 'block';
                                                            this.style.display = 'none';
                                                            ">Edit</button>
                                                        <form:form method="post" style="display:block;" modelAttribute="comment" id="deleteForm">
                                                            <input type="hidden" value="${comments.get(i).commentId}" name="commentId">
                                                            <input type="hidden" value="DELETE" name="order">
                                                            <input type="submit" value="Delete">
                                                        </form:form>
                                                    </security:authorize>
                                                    By ${comments.username}
                                                </div>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
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
                                <security:authorize access="hasAnyRole('USER', 'ADMIN')">

                                    <tr>
                                        <!-- <bold> -->
                                        <!-- <c:url var="logoutUrl" value="/logout" /> -->
                                        <!-- <form action="${logoutUrl}" method="POST">
                                                <input type="submit" class="link-button" value="Log out">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                            </form> -->
                                        <form:form method="POST" modelAttribute="comment" id="commentForm">
                                            <form:label path="commentText">Comment Content:</form:label>
                                            <form:textarea type="text" path="commentText" />
                                            <input type="hidden" value="INSERT" name="order">
                                                <br>
                                                <input type="submit" value="Comment">
                                        </form:form>
                                        <button onclick="
                                            document,getElementsByTagName('label').style.display = 'block';
                                            document.getElementsByTagName('form').style.display = 'none';
                                            document.getElementsByTagName('button').style.display = 'block';
                                            document.getElementById('commentForm').style.display = 'block';
                                            this.style.display = 'none';
                                        " style="display: none;">Comment</button>
                                        <!-- </bold> -->
                                    </tr>

                                </security:authorize>
                                <c:if test="${empty uname}">
                                    <tr>
                                        Login account to comment<br>
                                        <a href="<c:url value=" /login" />">
                                        <button>Login</button>
                                        </a>
                                        <a href="<c:url value=" /registration/create" />">
                                        <button>Registration</button>
                                        </a>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </body>

        </html>
