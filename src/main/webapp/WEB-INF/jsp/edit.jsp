<!DOCTYPE html>
<html>
<head>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

  <title>Edit Page</title>
</head>
<body>

<security:authorize access="hasRole('ADMIN')">
<div style="padding: 0%" class="container container-table col-md-offset-5">
  <div class="row">
    <div class="col-lg">
      <h1>
        <bold>Edit Page</bold>
      </h1>


      <ul style="list-style-type: none;display: flex;padding-bottom: 10%;padding-top: 1.5%;">
        <li>
          <bold>
            <a style="text-decoration: none;" href="<c:url value="/admin/panel/user"/>">User Management</a>
          </bold>
        </li>


        <li>
          <bold>
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="POST" style="padding-left: 90%;">
              <input style="color: #0000EE;background-color:white;border: none;text-decoration: none;"
                     type="submit" class="link-button" value="Log out">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
          </bold>
        </li>

      </ul>
    </div>

    <div class="col-lg" style="width: 35%; text-align: -webkit-center; height: 50%;background-color: whitesmoke">
      <form:form method="POST" modelAttribute="editPhotoUser" style="padding-top:15%;width: 30%;">
        <div style="display:table-caption;padding-right:15%;">

          <form:label path="username">Username: <c:out value="${user.username}"/></form:label></br></br></br>

          <form:label path="password">Password: </form:label>
          <form:input type="text" path="password" value="${fn:substringAfter(user.password, '{noop}')}"/>


          <form:label path="email">Email: </form:label>
          <form:input type="text" path="email" value="${user.email}"/>


          <form:label path="phoneNum">Phone number: </form:label>
          <form:input type="text" path="phoneNum" value="${user.phoneNum}"/>


          <form:label path="userDescription">Description: </form:label>
          <form:input type="text" path="userDescription" value="${user.userDescription}"/>


          <c:forEach items="${user.userRoles}" var="role">

            <c:if test="${role.userRole == 'ROLE_USER'}">
              <c:set var="isUserChecked" value="checked"/>
            </c:if>

            <c:if test="${role.userRole == 'ROLE_ADMIN'}">
              <c:set var="isAdminChecked" value="checked"/>
            </c:if>

          </c:forEach>

          <form:checkbox path="userRole" value="ROLE_USER" checked="${isUserChecked}"/>Normal User</br>
          <form:checkbox path="userRole" value="ROLE_ADMIN" checked="${isAdminChecked}"/>Admin User
          <br/>
          <br/>
          <br/>
          <input style="width: 100%;border: none;color: white;background-color: green;" type="submit"
                 value="Apply Changes"/>
        </div>
      </form:form>

      </security:authorize>
    </div>
  </div>
</div>
</body>
</html>
