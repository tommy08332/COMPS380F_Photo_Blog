<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


    <style>
        html, body, .container-table {
            height: 100%;
        }
        .vertical-center-row {
            display: table-cell;
            vertical-align: middle;
        }
        .container-table {
            display: table;
        }
    </style>
    <title>Register User</title>
</head>
<body>


<div class="container container-table col-md-offset-3">
    <div class="row vertical-center-row">
        <div class="text-center col-md-5 " style="background-color: whitesmoke;width: 70%;height: 23%">

            <h1 style="margin:0;padding-bottom: 5%;padding-top: 2%;">
                <bold>Register Page</bold>
            </h1>

            <button style="background-color:transparent;border:none;outline:none;position: absolute; left: 95%;bottom:70%; border: none;"><a href="<c:url value="/"/>" style="color:black;">

                <h2><bold style="color:black;">X</bold></h2>
            </a></button>

            <form:form method="POST" modelAttribute="createPhotoUser">


                <div style="display: flex;">
                    <div style="padding-left: 8%;">
                        <form:label path="username">Username: </form:label>
                        <form:input type="text" path="username"/>
                        </br>

                        <form:label path="password">Password: </form:label>
                        <form:input type="text" path="password"/>
                    </div>
                    <div style="padding-left: 15%;">
                        <form:label path="phoneNum">Phone number: </form:label>
                        <form:input type="text" path="phoneNum"/>
                        </br>

                        <form:label cssStyle="padding-left: 20%;" path="email">Email: </form:label>
                        <form:input type="text" path="email"/>

                    </div>
                </div>
                <security:authorize access="hasRole('ADMIN')">

                    <form:label path="userRole">User Roles: </form:label>

                    <form:checkbox path="userRole" value="ROLE_USER"/>Normal User
                    <form:checkbox path="userRole" value="ROLE_ADMIN"/>Admin User

                </security:authorize>


                <br/>
                <input class="btn btn-success" type="submit" value="Register"/>


            </form:form>




        </div>
    </div>
</div>
</body>
</html>
