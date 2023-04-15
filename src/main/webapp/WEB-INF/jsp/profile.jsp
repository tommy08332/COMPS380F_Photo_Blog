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

        <li>
            <bold>
                <a href='<c:url value="/"/>'>Home Page</a>
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
            <tbody>
            <tr>
                <th>
                    <div style="display: inline;padding-right: 10%;">
                        <img src="https://pbs.twimg.com/profile_images/428316729220276224/EdBZ2Kgp.jpeg"
                             style="width: 300px;">
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Title:</h3></br></br></br>
                        Monkey</label>
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Description :</h3></br></br></br>
                        This is a Monkey</label>
                    </div>
                    <div style="display: inline">
                        <label><h3>Upload-On :</h3></br></br></br>
                            2023/04/07</label>
                    </div>

                </th>
            </tr>
            <tr>
                <th>
                    <div style="display: inline;padding-right: 10%;">
                        <img src="https://www.dumpaday.com/wp-content/uploads/2017/01/random-pictures-74.jpg"
                             style= "width:300px">
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Title:</h3></br></br></br>
                            Bear</label>
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Description :</h3></br></br></br>
                            This is a Bear</label>
                    </div>
                    <div style="display: inline">
                        <label><h3>Upload-On :</h3></br></br></br>
                            2023/05/01</label>
                    </div>

                </th>      
            </tr>
            <tr>
                <th>
                    <div style="display: inline;padding-right: 10%;">
                        <img src="https://i.ytimg.com/vi/-K3SbSQ2oNI/hqdefault.jpg"   style= "width:300px">
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Title:</h3></br></br></br>
                            Wong Ping Kun</label>
                    </div>
                    <div style="display: inline;padding-right: 10%;">
                        <label><h3>Description :</h3></br></br></br>
                            This is a Wong Ping Kun</label>
                    </div>
                    <div style="display: inline">
                        <label><h3>Upload-On :</h3></br></br></br>
                            2023/04/23</label>
                    </div>
                </th> 
            </tr>
            </tbody>
        </table>
    </div>


</body>
</html>
