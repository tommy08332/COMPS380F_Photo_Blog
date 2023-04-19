<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.0/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <style>
        html, body, .container-table {
            height: 100%;
        }
        .container-table {
            display: table;
        }
        .vertical-center-row {
            display: table-cell;
            vertical-align: middle;
        }
        a:link {
            text-decoration: none;
        }
        a:visited {
            text-decoration: none;
        }
        a:hover {
            text-decoration: none;
        }
        a:active {
            text-decoration: none;
        }
    </style>


    <title>Login</title>
</head>
<body>


<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-5 col-md-offset-3" style="background-color: aqua">

            <%--            <button style="background-color:transparent;border:none;outline:none;position: absolute; left: 92%;bottom:62%;"><a href="<c:url value="/"/>" style="color:black;">--%>
            <button style="background-color:transparent;border:none;outline:none;position: absolute; left: 92%;bottom:62%;"><a href="javascript:history.back()" style="color:black;">
                <h2><bold style="color:black;">X</bold></h2>
            </a></button>

            <div>
                <h2>Login</h2>
            </div>
            <form action="login" method="POST">
                <div>
                    <label>Username</label>
                    <input type="text" name="username"/>
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" name="password" id="input-password"/>
                    <i class="far fa-eye" id="password-eye-icon" onclick="const password = document.getElementById('input-password');const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            this.classList.toggle('fa-eye-slash');" style="margin-left: -22px;cursor: pointer;"></i>
                </div>
                <div>
                    <label>Remember me</label>
                    <input type="checkbox" name="remember-me" />
                </div>

                <div style="padding-left: 3%;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button style="background-color: green; color: white;" type="submit" value="Login">Login</button>
                </div>


            </form>
                <c:if test="${param.error != null}">

                    <div style="color: black; font-weight: bold">Incorrect username or password</div>

                </c:if>
        </div>
    </div>
</div>
</body>
</html>
