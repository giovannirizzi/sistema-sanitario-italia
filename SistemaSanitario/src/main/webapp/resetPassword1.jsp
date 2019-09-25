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
      <title>Reset password page</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

      <link rel="stylesheet" type="text/css" href="css/bootstrap-italia.min.css">
      <link rel="stylesheet" type="text/css" href="css/main.css">

    </head>
    <body>
        <!-- HEADER -->
        <div class="it-header-slim-wrapper">
            <div class="container">
                <div class="row">
                  <div class="col-md-12">
                    <div class="it-header-slim-wrapper-content d-flex justify-content-start">
                        <div class="it-brand-wrapper mr-2 d-none d-lg-block">
                            <a href="./index">
                                <div class="it-brand-text">
                                  <h3 class="no_toc">Sistema Sanitario</h3>
                                </div>
                            </a>
                        </div> 
                        <a href="./index" class="go-back mr-5 d-none d-lg-block"><svg class="icon icon-sm icon-white mr-2"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg>Annulla accesso</a>
                            <a href="#" class="go-back"><svg class="icon icon-sm icon-white d-lg-none"><use xlink:href="./svg/sprite.svg#it-arrow-left"></use></svg></a>
                            <div class="it-brand-wrapper mr-2  d-lg-none d-flex  justify-content-center">
                            <a href="./index">
                                <div class="it-brand-text">
                                  <h3 class="no_toc">Sistema Sanitario</h3>
                                </div>
                            </a>
                        </div>
                      </div>
                    </div>
                  </div>
            </div>
        </div>
  </div>
        </div>
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col-12 col-lg-6">
                    <div class="card-wrapper">
                        <div class="card">
                            <div class="card-body">
                                <div class="container rounded" style="border:3px solid lightgray;">
                                    <div class="row">
                                            <div class="group col text-center">
                                                <p><h2>Ripristina password</h2></p>
                                            </div>
                                    </div>
                                    <div class="row justify-content-center" style="margin-bottom: 5%">
                                        <div class="alert alert-danger" role="alert">
                                            Le password inserite non <b>coincidono</b>.
                                        </div>
                                    </div>
                                    <div class="row justify-content-center" style="margin-bottom: 5%">
                                        <div class="alert alert-success" role="alert">
                                            Password <b>modificata</b>con successo.
                                        </div>
                                    </div>
                                    <div class="row justify-content-center" style="margin-bottom: 5%">
                                        <div class="alert alert-warning" role="alert">
                                            La password deve contenere almeno 8 caratteri, 1 numero e 1 lettera maiuscola.
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
                                                <input type="password" class="form-control input-password" name="confirm" placeholder="ripeti password">
                                                <label for="confirm" class="active" style="transition: none 0s ease 0s; width: auto;">Conferma</label>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-12 text-center">
                                                    <button type="submit" class="btn btn-primary">Conferma</button>
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

