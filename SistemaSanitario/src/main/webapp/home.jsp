
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var = "PAGE_TITLE" value = "Dashboard"/>
      

<!DOCTYPE html>
<html lang="it">
<head>
    
    <title><c:out value = "${PAGE_TITLE}"/> | Sistema Sanitario</title>
    
    <%@ include file="./WEB-INF/jspf/meta.jspf" %> 
    
</head>

<body>
    
    <!-- HEADER -->
    <%@ include file="./WEB-INF/jspf/header.jspf" %>
    
    <!-- MAIN -->
    <div class="container mt-sm-4 mt-md-5" style="height: 100%;">
        <div class="row">
            <div class="col-xl-10">
                
                <!-- PAGE TITLE -->
                <h1 class="text-center text-primary mb-5"><c:out value = "${PAGE_TITLE}"/>  </h1>

                <!-- CONTENT OF PAGE -->
                <div class="main-pills  text-center mt-sm-4 mt-md-5">
                   <div class="main-pills-wrap ">
                       <h2 class="text-secondary pb-4">Titolo sezione</h2>
                       <p>Ciao fra come stai</p>
                       <p>Ciao fra come stai</p>
                       <p>Ciao fra come stai</p>
                       <p>Ciao fra come stai</p>
                   </div>
               </div> 
                
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
    <%@ include file="./WEB-INF/jspf/footer.jspf" %> 
    
</body>

</html>