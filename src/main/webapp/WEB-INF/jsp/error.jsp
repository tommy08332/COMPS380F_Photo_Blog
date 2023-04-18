<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>

<c:choose>
    <c:when test="${empty err_message}">

        <h1>Error - 404 not found</h1>

    </c:when>
    <c:otherwise>

        <h1>${err_message}</h1>

    </c:otherwise>

</c:choose>

<a href="<c:url value="/"/>">Back to home page</a>


</body>
</html>
