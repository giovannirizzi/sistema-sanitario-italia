<%-- 
    Document   : resetPassword
    Created on : 22 set 2019, 15:03:39
    Author     : Omar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <html lang="it">
    <head>
      <title>Forgot password page</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

      <link rel="stylesheet" type="text/css" href="css/bootstrap-italia.min.css">
      <link rel="stylesheet" type="text/css" href="css/main.css">

    </head>
    <body style="background-color: #f6f6f6;">
        <!-- HEADER -->
        <div class="it-header-slim-wrapper">
            <div class="container">
                <div class="row">
                  <div class="col-md-12">
                    <div class="it-header-slim-wrapper-content d-flex justify-content-start">
                        <div class="it-brand-wrapper mr-3 d-none d-lg-block">
                            <a href="./index">
                                <div class="it-brand-text">
                                  <h3 class="mb-1">Sistema Sanitario</h3>
                                </div>
                            </a>
                        </div> 
                        <a href="./login" class="go-back mr-5 d-none d-lg-block"><svg class="icon icon-sm icon-white mr-2"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg>Annulla</a>
                        <div class="col-1 d-lg-none">
                            <a href="./login" class="go-back"><svg class="icon icon-sm icon-white" style="height: 28px; width: 28px;"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg></a>
                        </div>
                         <div class="col-10 d-lg-none">
                            <div class="it-brand-wrapper mr-2  d-flex  justify-content-center">
                                <a href="./index">
                                    <div class="it-brand-text">
                                      <h3 class="mb-1">Sistema Sanitario</h3>
                                    </div>
                                </a>
                            </div>
                        </div>
                      </div>
                    </div>
                  </div>
            </div>
        </div>
        <div class="container mt-3 mt-lg-5">
            <div class="row justify-content-center">
                <c:if test="${not empty email}">  
                    <div class="row justify-content-center mb-5">
                        <div class="alert alert-success" role="alert">
                            Una mail è stata inviata all'indirizzo <b><c:out value="${email}"/></b>
                        </div>
                    </div>
                        
                        <div class="row">
                            <div class="col-12">
                                <a href="./">Torna alla home page</a>
                            </div>
                        <div>
                        
                </c:if> 
                
                <c:if test="${empty email}">  
                    <div class="col-xs-12  col-lg-6">
                        <div class="card-wrapper">
                            <div class="card no-after" style="border:1px solid lightgray;">
                                <div class="card-body">
                                    <div class="container rounded" >
                                        <div class="row">
                                            <div class="group col text-center mb-5">
                                                <p><h4 class="text-secondary">Password dimenticata</h4></p>
                                            </div>
                                        </div>                                    
                                        <form action="./forgotpassword" method="POST">  
                                            <div class="form-row">
                                                <div class="form-group col text-center">
                                                    <input type="email" class="form-control input-password" name="email" placeholder="inserisci la tua email">
                                                    <label for="email" class="active" style="transition: none 0s ease 0s; width: auto;">Email</label>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-col-12 text-center">
                                                    <button type="submit" class="btn btn-primary">Conferma</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>    
            </div>
        </div>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
    </body>
</html>

