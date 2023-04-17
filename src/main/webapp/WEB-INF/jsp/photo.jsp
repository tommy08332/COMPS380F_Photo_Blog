<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
    function reset() {
        let edit = document.querySelectorAll('[id ^= "editForm"]');
        Array.prototype.forEach.call(edit, display_none);
        let button = document.getElementsByTagName("button");
        Array.prototype.forEach.call(button, display_inline);
        let label = document.getElementsByTagName("label");
        Array.prototype.forEach.call(label, display_inline);
    }
    function display_inline(element, iterator) {
        element.style.display = "inline";
    }
    function display_none(element, iterator) {
        element.style.display = "none";
    }
</script>
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


<button onclick="location.href='<c:url value="/" />'">Home</button>
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
                <img alt="img" src="data:image/${photos.photoFileType};base64,${photoImg}" />
                <div>
                    <label>Uploaded by:
                        <a href="<c:url value="/user/profile/${photos.uploadUsername}"/>"><c:out value="${photos.uploadUsername}" /></a>
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
                                        pattern="yyyy-MM-dd HH:mm:ss" />
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
                    <%--                                    <!-- Check comment list is not equal 0 --%>
                    <c:when test="${comments.size() eq 0}">
                        <tr>
                            <th>
                                <div>
                                    There doesn't have comments.
                                </div>
                            </th>
                        </tr>
                    </c:when>

                    <c:otherwise>

                        <%--                                        <!-- Loop out comment --%>
                        <c:forEach var="i" begin="0" end="${comments.size()-1}">
                            <tr>
                                <th>
                                    <div style="padding-bottom: 5%">
                                        date :
                                        <fmt:formatDate value="${comments.get(i).commentDatetime}"
                                                        pattern="yyyy-MM-dd HH:mm:ss" />
                                        <c:choose>

                                            <%--                                                            <!-- Check comment is not are current user --%>
                                            <c:when test="${comments.get(i).username == username}">

                                                <%--                                                                <!-- Just Allow logined and the user role is user to display --%>
                                                <security:authorize access="hasRole('USER')">
                                                    <%--                                                                    <!-- click to show edit form --%>
                                                    <button onclick="
                                                            reset();
                                                            document.getElementById('label${comments.get(i).commentId}').style.display = 'none';
                                                            document.getElementById('editForm${comments.get(i).commentId}').style.display = 'block';
                                                            this.style.display = 'none';
                                                            ">Edit</button>
                                                    <%--                                                                    <!-- click to delete the comment --%>
                                                    <button onclick="document.getElementById('deleteForm${comments.get(i).commentId}').submit();">
                                                        Delete
                                                    </button>

                                                    <%--                                                                    <!-- delete form --%>
                                                    <form:form action="./comment/delete" method="post" modelAttribute="comment" id="deleteForm${comments.get(i).commentId}">
                                                        <input type="hidden"value="${comments.get(i).commentId}" name="commentId">
                                                        <input type="hidden" name="photoId" value="${photos.photoId}">
                                                        <input type="hidden" value="DELETE" name="order">
                                                    </form:form>

                                                    <%--                                                                    <!-- edit form --%>
                                                    <div id="editForm${comments.get(i).commentId}"style="display:none;">
                                                        <form:form action="./comment/update" method="post" modelAttribute="comment" id="edit${comments.get(i).commentId}">
                                                                            <textarea rows="4" cols="40" name="commentText"
                                                                                      form="edit${comments.get(i).commentId}"><c:out value="${comments.get(i).commentText}" escapeXml="false"></c:out></textarea>
                                                            <!-- <input type="text" value="${comments.get(i).commentText}"> -->
                                                            <input type="hidden" name="commentId" value="${comments.get(i).commentId}">
                                                            <input type="hidden" name="photoId" value="${photos.photoId}">
                                                            <input type="hidden" name="order" value="UPDATE">
                                                            <br>
                                                            <input type="submit" value="Submit">
                                                        </form:form>
                                                    </div>

                                                </security:authorize>


                                            </c:when>
                                            <c:otherwise>

                                                <%--                                                                <!-- Just Allow logined and the user role is admin to display --%>
                                                <security:authorize access="hasRole('ADMIN')">
                                                    <%--                                                                    <!-- click to show edit form --%>
                                                    <button onclick="
                                                            reset();
                                                            document.getElementById('label${comments.get(i).commentId}').style.display = 'none';
                                                            document.getElementById('editForm${comments.get(i).commentId}').style.display = 'block';
                                                            this.style.display = 'none';
                                                            ">Edit</button>
                                                    <%--                                                                    <!-- click to delete the comment --%>
                                                    <button onclick="document.getElementById('deleteForm${comments.get(i).commentId}').submit();">
                                                        Delete
                                                    </button>

                                                    <%--                                                                    <!-- delete form --%>
                                                    <form:form action="./comment/delete" method="post" modelAttribute="comment" id="deleteForm${comments.get(i).commentId}">
                                                        <input type="hidden" value="${comments.get(i).commentId}" name="commentId">
                                                        <input type="hidden" name="photoId" value="${photos.photoId}">
                                                        <input type="hidden" value="DELETE" name="order">
                                                    </form:form>

                                                    <%--                                                                    <!-- edit form --%>
                                                    <div id="editForm${comments.get(i).commentId}" style="display:none;">
                                                        <form:form action="./comment/update" method="post" modelAttribute="comment" id="edit${comments.get(i).commentId}">
                                                                            <textarea rows="4" cols="40" name="commentText"
                                                                                      form="edit${comments.get(i).commentId}"><c:out value="${comments.get(i).commentText}" escapeXml="false"></c:out></textarea>
                                                            <!-- <input type="text" value="${comments.get(i).commentText}"> -->
                                                            <input type="hidden" name="commentId" value="${comments.get(i).commentId}">
                                                            <input type="hidden" name="photoId" value="${photos.photoId}">
                                                            <input type="hidden" name="order" value="UPDATE">
                                                            <br>
                                                            <input type="submit" value="Submit">
                                                        </form:form>
                                                    </div>

                                                </security:authorize>

                                            </c:otherwise>

                                        </c:choose>

                                    </div>
                                    <div>
                                        <label id="label${comments.get(i).commentId}">
                                            <c:out value="${comments.get(i).commentText}" escapeXml="false">
                                                Null Comment
                                            </c:out>
                                        </label>
                                        <br>
                                        By
                                        <c:out value="${comments.get(i).username}" escapeXml="false">
                                            Unknown User
                                        </c:out>
                                    </div>
                                </th>
                            </tr>

                        </c:forEach>

                    </c:otherwise>

                </c:choose>


                <%--                                <!-- User and Admin add new Comment --%>
                <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                    <tr>
                        <th>
                            <form:form action="./comment/insert" method="POST" modelAttribute="comment">
                                <form:label path="commentText">Comment</form:label>
                                <br>
                                <form:textarea type="text" path="commentText" />
                                <input type="hidden" value="INSERT" name="order">
                                <input type="hidden" value="${photos.photoId}" name="photoId">
                                <br>
                                <input type="submit" value="Comment">
                            </form:form>
                        </th>
                    </tr>
                </security:authorize>


                <%--                                <!-- Login and Registration --%>
                <c:if test="${empty username}">
                    <tr>
                        <th>
                            Login account to comment
                            <br>
                            <a href="<c:url value="/login" />">
                                <button>Login</button>
                            </a>
                            <a href="<c:url value="/registration/create" />">
                                <button>Registration</button>
                            </a>
                        </th>
                    </tr>
                </c:if>


                </tbody>
            </table>
        </div>
    </div>

</div>
</body>

</html>
