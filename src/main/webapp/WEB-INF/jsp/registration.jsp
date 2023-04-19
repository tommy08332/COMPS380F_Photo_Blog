<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
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

            <security:authorize access="hasRole('ADMIN')">

                <c:set var="redirectPath" value="admin/panel/user" />

            </security:authorize>


            <button style="background-color:transparent;border:none;outline:none;position: absolute; left: 95%;bottom:70%; border: none;"><a href="<c:url value="/${redirectPath}"/>" style="color:black;">

                <h2><bold style="color:black;">X</bold></h2>
            </a></button>

            <form:form method="POST" modelAttribute="createPhotoUser">


                <div style="display: flex;">
                    <div style="padding-left: 8%;">
                        <form:label path="username">Username: </form:label>
                        <form:input type="text" path="username" /></br>
                        <form:errors path="username" cssStyle="font-weight: bold; color: red; font-size: 4px;"/>
                        </br>

                        <form:label path="password">Password: </form:label>
                        <form:password path="password" id="input-password"/>
                        <i class="far fa-eye" id="password-eye-icon" onclick="const password = document.getElementById('input-password'); const type = password.getAttribute('type') === 'password' ? 'text' : 'password'; password.setAttribute('type', type); this.classList.toggle('fa-eye-slash');" style="margin-left: -22px;cursor: pointer;"></i>
                        </br>
                        <form:errors path="password" cssStyle="font-weight: bold; color: red; font-size: 4px;"/>
                    </div>
                    <div style="padding-left: 15%;">
                        <form:label path="phoneNum">Phone number: </form:label>
                        <form:input type="text" path="phoneNum"/></br>
                        <form:errors path="phoneNum" cssStyle="font-weight: bold; color: red; font-size: 4px;"/>
                        </br>

                        <form:label cssStyle="padding-left: 20%;" path="email">Email: </form:label>
                        <form:input type="text" path="email"/></br>
                        <form:errors path="email" cssStyle="font-weight: bold; color: red; font-size: 4px;"/>

                    </div>
                </div>
                <security:authorize access="hasRole('ADMIN')">

                    <form:label path="userRole">User Roles: </form:label>

                    <form:checkbox path="userRole" value="ROLE_USER"/>Normal User
                    <form:checkbox path="userRole" value="ROLE_ADMIN"/>Admin User
                    <form:errors path="userRole" cssStyle="font-weight: bold; color: red; font-size: 4px;"/>

                </security:authorize>

                <br/>
                <input class="btn btn-success" type="submit" value="Register"/>

            </form:form>

        </div>
    </div>
</div>
</body>
</html>
