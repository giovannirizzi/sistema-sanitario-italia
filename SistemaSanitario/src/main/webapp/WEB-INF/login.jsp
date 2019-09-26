
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <html lang="it">
    <head>
      <title>Login Page</title>
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
                        <a href="./index" class="go-back mr-5 d-none d-lg-block"><svg class="icon icon-sm icon-white mr-2"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg>Annulla accesso</a>
                        <div class="col-1 d-lg-none">
                            <a href="#" class="go-back"><svg class="icon icon-sm icon-white" style="height: 28px; width: 28px;"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg></a>
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
        <div class="row justify-content-center mt-3 mt-lg-5">
            <div class="col justify-content-center" style="text-align:center;">
                    <div class="text-xs-center spacer-xs-bottom-40">
                        <h1>Accedi</h1>
                    </div>
            </div>
        </div>
        <div class="container mt-3 mt-lg-5">
            <div class="row justify-content-center">
                <div class="col-xs-12  col-lg-6">
                    <div class="card-wrapper">
                        <div class="card no-after" style="border:1px solid lightgray;">
                            <div class="card-body">
                                <div class="container rounded" >
                                    <div class="row">
                                        <div class="group col text-center">
                                            <p><h4 class="text-secondary">Inserisci le tue credenziali</h4></p>
                                        </div>
                                    </div>       
                                    <div class="row justify-content-center mb-3">
                                        <c:if test="${not empty error}">  
                                            <div class="alert alert-danger" role="alert">
                                                Credenziali <b>errate</b>.
                                            </div>
                                        </c:if>
                                    </div>
                                    <form action="./login" method="POST">
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <input type="text" class="form-control" name="username" placeholder="inserisci il tuo nome utente">
                                                <label for="username" class="active" style="transition: none 0s ease 0s; width: auto;">Nome utente</label>
                                            </div>
                                        </div>    
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <input type="password" class="form-control input-password" name="password" placeholder="inserisci la tua password">
                                                <span class="password-icon" aria-hidden="true">
                                                  <svg class="password-icon-visible icon icon-sm"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-visible"></use></svg>
                                                  <svg class="password-icon-invisible icon icon-sm d-none"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-invisible"></use></svg>
                                                </span>
                                                <label for="password" class="active" style="transition: none 0s ease 0s; width: auto;">Password</label>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <a href="./forgotpassword">Ho dimenticato la password?</a>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-6 auto text-center">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" id="autoSizingCheck" name="rememberme">
                                                    <label class="form-check-label" for="autoSizingCheck">Ricordami</label>
                                                </div>
                                            </div>
                                            <div class="form-group col-6 text-center">
                                                    <button type="submit" class="btn btn-primary">Accedi</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
    
    
        </body>
    
</html>
