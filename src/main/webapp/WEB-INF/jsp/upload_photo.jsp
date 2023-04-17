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
        }</style>

    <title>Upload Photo</title>
</head>
<body>

<security:authorize access="hasAnyRole('USER', 'ADMIN')">

    <div style="padding-top: 3%;padding-left: 10%;">
        <ul >

            <li>
                <bold>
                    <a style="text-decoration: none;" href='<c:url value="/"/>'>Home Page</a>
                </bold>
            </li>

            <li>
                <bold>
                    <a style="text-decoration: none;" href="<c:url value="/admin/panel/user"/>">User
                        Management</a>
                </bold>
            </li>
            <li>
                <bold>
                    <a style="text-decoration: none;" href="<c:url value="/admin/panel/history"/>">Upload
                        History</a>
                </bold>
            </li>
            <li>
                <bold>
                    <button style="background-color: white;border:none;"><a style=" text-decoration: none;"
                                                                            href="<c:url value="/admin/panel/registration/create"/>">Create
                        new
                        user</a></button>
                </bold>
            </li>

            <li>
                <bold>
                    <c:url var="logoutUrl" value="/logout"/>
                    <form action="${logoutUrl}" method="POST" style="padding-left: 90%">
                        <input style="color: #0000EE;background-color:white;border: none;text-decoration: none;"
                               type="submit" class="link-button" value="Log out">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </bold>
            </li>


        </ul>
    </div>

    <div class="container container-table col-md-offset-4">
        <div class="row vertical-center-row" style="padding-top: 25%">
            <div class="text-center col-md-5 " style="background-color: beige;width: 55%;height: 23%">
                <h1 style="padding-bottom: 5%;">Upload photo</h1>

                <form:form method="POST" enctype="multipart/form-data" modelAttribute="photoUploadForm">
                    <div style="display:inline-flex;padding-bottom: 5%;padding-right: 20%;">
                        <div style="display: block;">
                            <form:label path="photoTitle">Photo Title:</form:label>
                            <form:input type="text" path="photoTitle"/>
                        </div>
                        <div style="padding-left: 40%;">
                            <form:label style="display: table-cell;padding-bottom: 5%;"
                                        path="photoDescription">Photo Description:</form:label>
                            <form:textarea type="text" path="photoDescription"/>
                        </div>
                    </div>
                    <div>
                        <form:input style="padding-left: 10%;" type="file" path="photoData" accept="image/*"
                                    multiple="multiple"/>
                        <div style="padding-left: 80%;padding-bottom: 2%;">
                            <input style="background-color:green;border: none;color:white;width: 60%" type="submit"
                                   value="Submit"/>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>

</security:authorize>
</body>
</html>
