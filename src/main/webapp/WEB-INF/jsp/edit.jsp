<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Page</title>
</head>
<body>

<security:authorize access="hasRole('ADMIN')">

  <h1>Edit Page</h1>


  <form:form method="POST" modelAttribute="editPhotoUser">


    <form:label path="username">Username: <c:out value="${user.username}"/></form:label>

    <br/>

    <form:label path="password">Password: </form:label>
    <form:input type="text" path="password" value="${fn:substringAfter(user.password, '{noop}')}" />
    <br/>

    <form:label path="email">Email: </form:label>
    <form:input type="text" path="email" value="${user.email}"/>
    <br/>

    <form:label path="phoneNum">Phone number: </form:label>
    <form:input type="text" path="phoneNum" value="${user.phoneNum}"/>
    <br/>

    <form:label path="userDescription">Description: </form:label>
    <form:input type="text" path="userDescription" value="${user.userDescription}"/>
    <br/>

    <c:forEach items="${user.userRoles}" var="role">

      <c:if test="${role.userRole == 'ROLE_USER'}">
        <c:set var = "isUserChecked" value="checked"/>
      </c:if>

      <c:if test="${role.userRole == 'ROLE_ADMIN'}">
        <c:set var = "isAdminChecked" value="checked"/>
      </c:if>

    </c:forEach>

    <form:checkbox path="userRole" value="ROLE_USER" checked="${isUserChecked}"/>Normal User
    <form:checkbox path="userRole" value="ROLE_ADMIN" checked="${isAdminChecked}"/>Admin User


    <br/>
    <input type="submit" value="Apply Changes"/>

    <%--        <c:forEach items="${user.userRoles}" var="roles" >--%>

    <%--            <c:choose>--%>
    <%--                <c:when test="${roles.userRole == 'ROLE_USER'}">--%>

    <%--                    --%>

    <%--                </c:when>--%>

    <%--            </c:choose>--%>


    <%--        </c:forEach>--%>

  </form:form>

</security:authorize>

</body>
</html>
