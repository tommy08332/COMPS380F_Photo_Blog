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
</div>
<div class="container container-table">
    <div class="row">
        <div class="text-center col-md-5 col-md-offset-3" style="padding-top:5%;">

            <div class="card">
                <h1><c:out value="${blogUsers.username}"/> </h1>
                <p><c:out value="${blogUsers.phoneNum}"/></p>
                <p><c:out value="${blogUsers.email}"/></p>
                <p><c:out value="${blogUsers.userDescription}"/></p>
                <p>
                    <button>Edit</button>
                </p>
            </div>

        </div>
    </div>
</div>


<div class="container">
    <div class="tableFixHead">
        <table>
            <thead>

            </thead>
            <tbody>
            <tr>
                <th>
                    <img src="https://pbs.twimg.com/profile_images/428316729220276224/EdBZ2Kgp.jpeg" width="100%"
                         height="100%">
                </th>

                <th>
                    <img src="https://www.dumpaday.com/wp-content/uploads/2017/01/random-pictures-74.jpg" width="100%"
                         height="100%">
                </th>
                <th>
                    <img src="http://cdn.ebaumsworld.com/mediaFiles/picture/1035099/85708057.jpg"
                         width="100%" height="100%">
                </th>
            </tr>

            <tr>
                <th>
                    <img src="https://i.ytimg.com/vi/-K3SbSQ2oNI/hqdefault.jpg"
                         width="100%" height="100%">
                </th>
                <th>
                    <img src="https://img.izismile.com/img/img12/20190327/640/random_things_that_look_like_something_else_640_high_02.jpg"
                         width="100%" height="100%">
                </th>
                <th>
                    <img src="https://cdn.ebaumsworld.com/mediaFiles/picture/2117880/82537959.jpg"
                         width="100%" height="100%">
                </th>

            </tr>

            </tbody>
        </table>
    </div>


</body>
</html>
