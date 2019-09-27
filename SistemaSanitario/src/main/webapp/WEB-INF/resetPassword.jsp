<%-- 
    Document   : resetPassword
    Created on : 13 set 2019, 15:03:39
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <html lang="it">
    <head>
      <title>Reset password page</title>
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
                         <div class="col-12 d-lg-none">
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
        <div class="row justify-content-center mt-3 mt-lg-5">
            <div class="col justify-content-center" style="text-align:center;">
                <div class="text-xs-center spacer-xs-bottom-40">
                    <h2>Ripristina password</h2>
                </div>
            </div>
        </div>
        <c:if test="${not empty success}">  
            <div class="row justify-content-center">
                <div class="alert alert-success" role="alert">
                    Password <b>modificata</b>con successo.
                </div>
            </div>
        </c:if> 
        <c:if test="${empty success}">  
        <div class="container mt-3 mt-lg-5">
            <div class="row justify-content-center">
                <div class="alert alert-warning" role="alert">
                    La password deve contenere almeno 8 caratteri, 1 numero e 1 lettera maiuscola.
                </div>
            </div>
             <c:if test="${not empty error}">  
             <div class="row justify-content-center mt-2 mt-lg-4">
                <div class="alert alert-danger" role="alert">
                    Le password inserite non <b>coincidono</b>.</div>
             </div>
             </c:if> 
        </div>
        <div class="container mt-2 mt-lg-4">
            <div class="row justify-content-center">
                <div class="col-xs-12  col-lg-6">
                    <div class="card-wrapper">
                        <div class="card no-after" style="border:1px solid lightgray;">
                            <div class="card-body">
                                <div class="container rounded">
                                    <div class="row mb-5">
                                        <div class="group col text-center">
                                            <p><h4 class="text-secondary">Inserisci la nuova password</h4></p>
                                        </div>
                                    </div>
                                    <form action="./reset" method="POST">
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <input type="password" class="form-control input-password" name="newpassword" placeholder="inserisci nuova password">
                                                <label for="newpassword" class="active" style="transition: none 0s ease 0s; width: auto;">Nuova password</label>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <input type="password" class="form-control input-password" name="confirm" placeholder="ripeti nuova password">
                                                <label for="confirm" class="active" style="transition: none 0s ease 0s; width: auto;">Conferma</label>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-12 text-center">
                                                    <button type="submit" class="btn btn-primary">Conferma</button>
                                            </div>
                                        </div>
                                        <input type="hidden" name="token" value="<c:out value="${token}"/>">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:if>  
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
    </body>
</html>
