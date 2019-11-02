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
    
    <!-- HEADER -->
    <%@ include file="./jspf/header.jspf" %>
    
    <!-- MAIN -->
    <div class="container mt-sm-4 mt-md-5" style="height: 100vh;">
        <div class="row">
            <div class="col-xl-10">
                
                <!-- PAGE TITLE -->
                
                <%--<c:if test="${sessionScope.user != null}">
                    <h1 class="text-center text-primary mb-5">Benvenuto paziente <c:out value="${sessionScope.user.username}"/></h1>
                </c:if>--%>
                
                <h1 class="text-center text-primary mb-5">Esami</h1>

                <!-- CONTENT OF PAGE -->
                <div class="main-pills  text-center mt-sm-4 mt-md-5">
                   <div class="main-pills-wrap ">
                       <div class="form-group">
                        <input type="search" class="autocomplete" placeholder="Cerca esami..."
                          id="autocomplete-regioni"
                          name="autocomplete-regioni"
                          data-autocomplete='[{"text":"Abruzzo","link":"#"},{"text":"Basilicata","link":"#"},{"text":"Calabria","link":"#"},{"text":"Campania","link":"#"},{"text":"Emilia Romagna","link":"#"},{"text":"Friuli Venezia Giulia","link":"#"},{"text":"Lazio","link":"#"},{"text":"Liguria","link":"#"},{"text":"Lombardia","link":"#"},{"text":"Marche","link":"#"},{"text":"Molise","link":"#"},{"text":"Piemonte","link":"#"},{"text":"Puglia","link":"#"},{"text":"Sardegna","link":"#"},{"text":"Sicilia","link":"#"},{"text":"Toscana","link":"#"},{"text":"Trentino Alto Adige","link":"#"},{"text":"Umbria","link":"#"},{"text":"Valle d’Aosta","link":"#"},{"text":"Veneto","link":"#"}]'>
                        <span class="autocomplete-icon" aria-hidden="true">
                          <svg class="icon icon-sm"><use xlink:href="../svg/sprite.svg#it-search"></use></svg>
                        </span>
                        <label for="autocomplete-regioni" class="sr-only">Cerca nel sito</label>
                      </div>

                   </div>
               </div>
                
                
            </div>
            
            <!-- SIDEBAR -->
            <div class="d-none d-xl-block col-xl-2 bd-toc">
                <div class="sidebar-wrapperp">
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
