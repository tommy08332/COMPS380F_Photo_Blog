<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </style>
    <title>Welcome to PhotoBlog</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Welcome to PhotoBlog! </h1>
<%--        nav bar--%>
        <ul>
            <li>
                <bold>
                    <a href='<c:url value="/photo/show"/>'>Photo</a>
                </bold>
            </li>

            <li>
                <bold>
                    <a href="<c:url value="/profile"/>">Profile</a>
                </bold>
            </li>

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

            <li>
                <bold>
                    <a href='<c:url value="/admin_page"/>'>Admin Page</a>
                </bold>
            </li>

            <li>
                <bold>
                    <a href='<c:url value="/photo/upload"/>'>Upload Photo Page</a>
                </bold>
            </li>

        </ul>

        <c:choose>

            <c:when test="${empty img} == 0">

                Please Upload photo

            </c:when>

            <c:otherwise>

                ${filetype.values()}

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
                                                 src="data:image/${photos.get(i).photoFileType};base64,${fileContent.get(i)}"
                                                 width="100%" height="100%"/>
                                        </a>

                                    </td>

                        </c:forEach>

                    </tbody>

                </table>

                </div>

            </c:otherwise>

        </c:choose>

            <div class="tableFixHead">
                <table>
                    <thead>

                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <img src="https://cdn.ebaumsworld.com/mediaFiles/picture/1151541/84693449.png" width="100%" height="100%">
                        </td>

                        <td>
                            <img src="https://upload.wikimedia.org/wikipedia/commons/b/be/Random_pyramids.jpg" width="100%" height="100%">
                        </td>
                        <td>
                            <img src="https://en.wikipedia.org/wiki/Wikipedia:Picture_of_the_day#/media/File:Landscape_Arch_Utah.jpg" width="100%" height="100%">
                        </td>

                    </tr>
                    <tr>
                        <td>
                        1 ======================
                        </td>
                        <td>
                           2 ======================
                        </td>
                        <td>
                           3 ======================
                        </td>

                    </tr>
                    <tr>
                        <td>
                        1 ================
                        </td>
                        <td>
                            2======================
                        </td>
                        <td>
                            3======================
                        </td>

                    </tr>
                    <tr>
                        <td>
                        1==========
                        </td>
                        <td>
                           2 ======================
                        </td>
                        <td>
                            3======================
                        </td>

                    </tr>
                    <tr>
                        <td>
                       1 ========
                        </td>
                        <td>
                           2 ======================
                        </td>
                        <td>
                            3======================
                        </td>
                    </tr>
                    <tr>
                        <td>
                      1 ===================
                        </td>
                        <td>
                           2 ======================
                        </td>
                        <td>
                            3======================
                        </td>

                    </tr>
                    <tr>
                        <td>
                          1------
                        </td>
                        <td>
                            2======================
                        </td>
                        <td>
                            3======================
                        </td>

                    </tr>
                    <tr>
                        <td>
                          1-----
                        </td>
                        <td>
                           2 ======================
                        </td>
                        <td>
                           3 ======================
                        </td>

                    </tr>
                    </tbody>
                </table>
        </div>

    </div>

</div>

</body>
</html>
