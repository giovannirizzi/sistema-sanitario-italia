
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main JSP Page</title>
    </head>
    <body>
        
        <c:if test="${sessionScope.user != null}">
            <h1>Welcome <c:out value="${sessionScope.user.username}"/></h1>
        </c:if>
    </body>
</html>
