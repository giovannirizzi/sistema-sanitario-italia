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
    <div class="container mt-sm-4 mt-md-5">
        <div class="row">
            <div class="col-xl-10">
                
                <!-- PAGE TITLE -->
                <c:if test="${sessionScope.user != null}">
                    <h1 class="text-center text-primary mb-5">Profilo</h1>
                 </c:if>
                
                <%@ include file="./jspf/genericData.jspf" %>    
                    
                <c:if test="${sessionScope.user.type == 'PAZIENTE'}">
                  <%@ include file="./jspf/paziente/personalData.jspf" %> 
                </c:if>
                
                 
                
            </div>
            
            <!-- SIDEBAR -->
            <div class="d-none d-xl-block col-xl-2 bd-toc">
                <div class="sidebar-wrapper">
                  <h3><c:out value = "${PAGE_TITLE}"/></h3>
                  <ul class="section-nav">
                    <li class="toc-entry toc-h3"><a href="#doctype-html5">Doctype HTML5</a></li>
                    <li class="toc-entry toc-h3"><a href="#meta-tag-responsive">Meta tag responsive</a></li>
                    <li class="toc-entry toc-h3"><a href="#box-sizing">Box-sizing</a></li>
                    <li class="toc-entry toc-h3"><a href="#reboot">Reboot</a></li>
                  </ul>
                </div>
            </div>
            
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="./jspf/footer.jspf" %> 
    
</body>

</html>
