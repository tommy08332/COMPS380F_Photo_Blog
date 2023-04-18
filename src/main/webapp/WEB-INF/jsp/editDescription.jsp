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
        .card {
            display: -webkit-box;
            max-width: 300px;
            margin: auto;
            text-align: center;
            font-family: arial;
        }
    </style>
    <title>Edit Page</title>
</head>
<body>
<div class="container container-table">
    <div class="row">
        <div class="text-center col-md-5 col-md-offset-3" style="margin-top: 25%;padding-bottom: 2%;background-color: whitesmoke">
            <h1 style="padding-bottom: 10%;">Edit Page</h1>
            <div class="card">

                <button style="background-color:transparent;border:none;outline:none;position: absolute; left: 92%;bottom:80%; border: none;">
                    <a href="<c:url value="/user/profile/${blogUsers.username}"/>" style="color:black;">

                        <h2>
                            <bold style="color:black;">X</bold>
                        </h2>
                    </a></button>

                <form:form method="POST" modelAttribute="edescription">


                    <form:label path="phoneNum">Phone number: </form:label>
                    <form:input type="text" path="phoneNum" value="${blogUsers.phoneNum}"/>


                    <form:label path="email" style="padding-left: 20%;">Email: </form:label>
                    <form:input type="text" path="email" value="${blogUsers.email}"/>


                    <form:label path="userDescription" style="padding-left: 7%;">Description: </form:label>
                    <form:input type="text" path="userDescription" value="${blogUsers.userDescription}"/>
                    </br>
                    </br>

                    <input style="width: 100%;border: none;background-color: green;color: white;height: 25%;" type="submit" value="Edit Description"/>


                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
