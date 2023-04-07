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

        th,
        td {
            padding: 8px 16px;
            border: 1px solid #ccc;
        }

        th {
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


            <div class="tableFixHead">
                <table>
                    <thead>
                    <tr>
                        <th>
                            <h3><bold>PHOTOS</bold></h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>
                            <img src="https://cdn.ebaumsworld.com/mediaFiles/picture/1151541/84693449.png" width="100%" height="100%">
                        </th>

                        <th>
                            <img src="https://upload.wikimedia.org/wikipedia/commons/b/be/Random_pyramids.jpg" width="100%" height="100%">
                        </th>
                        <th>
                            <img src="https://en.wikipedia.org/wiki/Wikipedia:Picture_of_the_day#/media/File:Landscape_Arch_Utah.jpg" width="100%" height="100%">
                        </th>

                    </tr>
                    <tr>
                        <th>
                        1 ======================
                        </th>
                        <th>
                           2 ======================
                        </th>
                        <th>
                           3 ======================
                        </th>

                    </tr>
                    <tr>
                        <th>
                        1 ================
                        </th>
                        <th>
                            2======================
                        </th>
                        <th>
                            3======================
                        </th>

                    </tr>
                    <tr>
                        <th>
                        1==========
                        </th>
                        <th>
                           2 ======================
                        </th>
                        <th>
                            3======================
                        </th>

                    </tr>
                    <tr>
                        <th>
                       1 ========
                        </th>
                        <th>
                           2 ======================
                        </th>
                        <th>
                            3======================
                        </th>
                    </tr>
                    <tr>
                        <th>
                      1 ===================
                        </th>
                        <th>
                           2 ======================
                        </th>
                        <th>
                            3======================
                        </th>

                    </tr>
                    <tr>
                        <th>
                          1------
                        </th>
                        <th>
                            2======================
                        </th>
                        <th>
                            3======================
                        </th>

                    </tr>
                    <tr>
                        <th>
                          1-----
                        </th>
                        <th>
                           2 ======================
                        </th>
                        <th>
                           3 ======================
                        </th>

                    </tr>
                    </tbody>
                </table>
        </div>

    </div>

</div>

</body>
</html>
