<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var = "PAGE_TITLE" value = "Profilo"/>
      
<!DOCTYPE html>
<html lang="it">
<head>
    
    <title><c:out value = "${PAGE_TITLE}"/> | Sistema Sanitario</title>
    
    <%@ include file="./jspf/meta.jspf" %> 
    
</head>

<body style="height: 100%;">
    
    <!-- HEADER -->
    <%@ include file="./jspf/header.jspf" %>
    
    <!-- MAIN -->
    <div class="container container-extended mt-sm-4 mt-md-5">
        <div class="row">
            
            <%@ include file="./jspf/navbar.jspf" %>
            
            <div class="col-lg-9">
                
                <!-- PAGE TITLE -->
                <c:if test="${sessionScope.user != null}">
                    <h1 class="text-center text-primary mb-5">Profilo</h1>
                 </c:if>
                
                <%@ include file="./jspf/userData.jspf" %>    
                    
                <c:if test="${sessionScope.user.type == 'PAZIENTE'}">
                  <%@ include file="./jspf/paziente/datiAnagrafici.jspf" %> 
                </c:if>
 
            </div>        
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="./jspf/footer.jspf" %> 
    
</body>

</html>
