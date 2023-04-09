<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


</body>
</html>
