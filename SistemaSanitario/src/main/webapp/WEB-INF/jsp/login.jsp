<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <html lang="it">
    <head>
      <title>Login Page | SS Italia</title>
      
      <%@ include file="../jspf/meta.jspf" %> 

    </head>
    <body style="background-color: #f6f6f6;">
        <!-- HEADER -->
        <div class="it-header-slim-wrapper">
            <div class="container">
                <div class="row">
                  <div class="col-md-12">
                    <div class="it-header-slim-wrapper-content d-flex justify-content-start">
                        <div class="it-brand-wrapper mr-3 d-none d-lg-block">
                            <a href="./">
                                <div class="it-brand-text">
                                  <h3 class="mb-1">Sistema Sanitario Italia</h3>
                                </div>
                            </a>
                        </div> 
                        <a href="./" class="go-back mr-5 d-none d-lg-block"><svg class="icon icon-sm icon-white mr-2"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg>Annulla accesso</a>
                        <div class="col-1 d-lg-none">
                            <a href="./" class="go-back"><svg class="icon icon-sm icon-white" style="height: 28px; width: 28px;"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg></a>
                        </div>
                         <div class="col-10 d-lg-none">
                            <div class="it-brand-wrapper mr-2  d-flex  justify-content-center">
                                <a href="./">
                                    <div class="it-brand-text">
                                      <h3 class="mb-1">Sistema Sanitario Italia</h3>
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
        <div class="container mt-3 mb-3 mt-lg-5 mb-lg-5">
            <div class="row justify-content-center mb-3">
                <div class="col-xs-12  col-lg-6">
                   <c:if test="${not empty error}">  
                       <div class="alert alert-danger" role="alert">
                           <b>Autenticazione fallita.</b> Le credenziali inserite non risultano corrette. Ti invitiamo a riprovare.
                       </div>
                   </c:if>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-xs-12  col-md-8 col-lg-5">
                    <div class="card-wrapper">
                        <div class="card no-after" style="border:1px solid lightgray;">
                            <div class="card-body">
                                <div class="container rounded" >
                                    <div class="row">
                                        <div class="form-group col text-center">
                                            <p><h4 class="text-secondary">Inserisci le tue credenziali</h4></p>
                                        </div>
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
                                                 <div class="form-row pt-4">
                                                    <div class="col-5 form-check text-left" style="margin-top:0rem;">
                                                        <input class="form-check-input" type="checkbox" id="autoSizingCheck" name="rememberme">
                                                        <label class="form-check-label" for="autoSizingCheck">Ricordami</label>
                                                    </div>
                                                    <div class="col-7 text-right">
                                                       <a href="./forgotpassword">Ho dimenticato la password</a>
                                                   </div>
                                                </div>
                                                 
                                            </div>
                                        </div>
                                       
                                        <div class="form-row">
                                            <div class="form-group col-12 text-center">
                                                <button type="submit" class="btn btn-block btn-primary">Accedi</button>
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

        <script type="text/javascript" src="/SistemaSanitario/js/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="/SistemaSanitario/js/main.js"></script>
        <script type="text/javascript" src="/SistemaSanitario/js/owl.carousel.min.js"></script>
        <script type="text/javascript" src="/SistemaSanitario/js/popper.min.js"></script>
        <script type="text/javascript" src="/SistemaSanitario/js/bootstrap-italia.min.js"></script>
        </body>
</html>
