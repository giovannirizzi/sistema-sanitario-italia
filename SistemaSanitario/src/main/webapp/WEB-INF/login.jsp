
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <h1>Login page</h1>
        <form method="POST" action="./login">
            Username: <input type="text" name="username">
            Password: <input type="password" name="password">
            Remember me: <input type="checkbox" name="rememberme">
            <input type="submit"> 
            
            <c:if test="${not empty errorMessage}">
                <h2 style="color: red;"><c:out value="${errorMessage}"/></h2>
            </c:if>
        </form>
    </body>
</html>
