<!DOCTYPE html>
<html>
<head>
    <title>Upload Photo</title>
</head>
<body>

<h1>Upload photo</h1>

<form:form method="POST" enctype="multipart/form-data" modelAttribute="photoUploadForm">

  <form:label path="photoTitle">Photo Title:</form:label><br><br>
  <form:input type="text" path="photoTitle"/><br><br>
  <form:label path="photoDescription">Photo Description:</form:label><br><br>
  <form:textarea type="text" path="photoDescription"/><br><br>

  <form:input type="file" path="photoData" accept="image/*" multiple="multiple"/><br><br>

  <input type="submit" value="Submit"/>

</form:form>

</body>
</html>
