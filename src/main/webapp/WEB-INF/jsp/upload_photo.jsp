<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


    <title>Upload Photo</title>
</head>
<body>

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
</body>
</html>
