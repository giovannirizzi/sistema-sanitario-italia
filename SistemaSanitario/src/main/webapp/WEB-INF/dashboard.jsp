<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var = "PAGE_TITLE" value = "Dashboard"/>
      
<!DOCTYPE html>
<html lang="it">
<head>
    
    <title><c:out value = "${PAGE_TITLE}"/> | Sistema Sanitario</title>
    
    <%@ include file="./jspf/meta.jspf" %> 
    
</head>

<body>
    
    <c:if test="${sessionScope.user.type == 'PAZIENTE'}">
        <jsp:forward page="./esamiPaziente" />
    </c:if>
    <c:if test="${sessionScope.user.type == 'MEDICO_BASE'}">
        <jsp:forward page="./pazientiMedicoBase" />
    </c:if>
    <c:if test="${sessionScope.user.type == 'MEDICO_SPECIALISTA'}">
        <jsp:forward page="./profile" />
    </c:if>
    <c:if test="${sessionScope.user.type == 'SS_PROVINCIALE'}">
        <jsp:forward page="./profile" />
    </c:if>
    
</body>

</html>
