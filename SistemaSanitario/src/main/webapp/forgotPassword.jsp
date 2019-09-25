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
    <body>
        <!-- HEADER -->
        <div class="it-header-wrapper it-header-sticky">
      <div class="it-nav-wrapper">
        <!-- HEADER CENTER -->
        <div class="it-header-center-wrapper">
          <div class="container mw-75">
            <div class="row">
              <div class="col-6">
                <div class="it-header-center-content-wrapper">
                  <div class="it-brand-wrapper">
                    <a href="#">
                      <svg class="icon">
                        <use xlink:href="svg/sprite.svg#it-code-circle"></use>
                      </svg>
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
                                                <p><h2>Password dimenticata</h2></p>
                                            </div>
                                    </div>
                                    <div class="row justify-content-center" style="margin-bottom: 5%">
                                        <div class="alert alert-success" role="alert">
                                            Una mail è stata <b>inviata</b> all'indirizzo fornito.
                                        </div>
                                    </div>
                                    <form action="./forgot" method="POST">  
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <input type="email" class="form-control input-password" name="email" placeholder="inserisci la tua email">
                                                <label for="email" class="active" style="transition: none 0s ease 0s; width: auto;">Email</label>
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

