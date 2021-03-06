<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <html lang="it">
    <head>
      <title>Reset Password | SS Italia</title>
      
      <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="${context}/css/bootstrap-italia.min.css">
    <link rel="stylesheet" type="text/css" href="${context}/css/main.css">

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
                                  <h3 class="mb-1">Sistema Sanitario Italia</h3>
                                </div>
                            </a>
                        </div> 
                         <div class="col-12 d-lg-none">
                            <div class="it-brand-wrapper mr-2  d-flex  justify-content-center">
                                <a href="./index">
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
        
        <c:if test="${not empty success}">  
            <div class="row justify-content-center">
                <div class="alert alert-success mt-5" role="alert">
                    Password <b>modificata</b> con successo.
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <a href="./login">Torna alla schermata di login</a>
                </div>
            </div>
        </c:if> 
        <c:if test="${empty success}">
            <div class="row justify-content-center mt-3 mt-lg-5">
                <div class="col justify-content-center" style="text-align:center;">
                    <div class="text-xs-center spacer-xs-bottom-40">
                        <h2>Ripristina password</h2>
                    </div>
                </div>
            </div>
            <div class="container mt-3">
                <div class="row justify-content-center">
                    <div class="callout warning">
                        <div class="callout-title" style="background-color: #f6f6f6;"><svg class="icon"><use xlink:href="./svg/sprite.svg#it-help-circle"></use></svg>Attenzione</div>
                        <p>La password deve contenere almeno 8 caratteri, 1 lettera maiuscola, 1 lettera maiuscola, 1 numero e 1 carattere speciale.</p>
                    </div>
                </div>
                 <c:if test="${not empty invalidPassword}">  
                    <div class="row justify-content-center mt-2 mt-lg-4">
                       <div class="alert alert-danger" role="alert">
                           Errore password invalida la password inserita non rispetta le regole</div>
                    </div>
                 </c:if> 
                <c:if test="${not empty invalidToken}">  
                    <div class="row justify-content-center mt-2 mt-lg-4">
                       <div class="alert alert-danger" role="alert">
                           Errore token non valido o scaduto</div>
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
                                        <form class="needs-validation"action="./resetpassword" method="POST" onsubmit="return validateResetPasswordForm();" novalidate>
                                            <div class="form-row">
                                                <div class="form-group col text-center">
                                                    <input type="password" class="form-control input-password " name="newPassword" id="newPassword" placeholder="inserisci nuova password" required>
                                                    <label for="newpassword" class="active" style="transition: none 0s ease 0s; width: auto;">Nuova password</label>
                                                     <div class="invalid-feedback">Le password non coincidono</div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col text-center">
                                                    <input type="password" class="form-control input-password" name="confirm" id="confirm" placeholder="ripeti nuova password" required>
                                                    <label for="confirm" class="active" style="transition: none 0s ease 0s; width: auto;">Conferma</label>
                                                    <div class="invalid-feedback">Le password non coincidono</div>
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
                                <script>
                                (function() {
                                  'use strict';
                                  window.addEventListener('load', function() {
                                    var forms = document.getElementsByClassName('needs-validation');
                                    var validation = Array.prototype.filter.call(forms, function(form) {
                                      form.addEventListener('submit', function(event) {
                                        if (validateResetPasswordForm() === false || form.checkValidity() === false) {
                                          event.preventDefault();
                                          event.stopPropagation();
                                        }
                                        form.classList.add('was-validated');
                                      }, false);
                                    });
                                  }, false);
                                })();
                                </script>
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
