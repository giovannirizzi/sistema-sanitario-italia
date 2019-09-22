<%-- 
    Document   : resetPassword
    Created on : 13 set 2019, 15:03:39
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
    </head>
    <body>
        <h1>Reset password page</h1>
        <form method="POST">
            Nuova password <input type="password" name="newPassword">
            <input type="hidden" name="token" value="<c:out value="${token}"/>">
            <input type="submit"> 
        </form>
        </form>
    </body>
</html>
