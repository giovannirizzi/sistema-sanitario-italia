
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
                                                <p><h2>Accedi al servizio</h2></p>
                                            </div>
                                        </div>
                                        <div class="row justify-content-center" style="margin-bottom: 5%">
                                                <div class="alert alert-danger" role="alert">
                                                    Credenziali <b>errate</b>.
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
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col text-center">
                                                <a href="index">Hai dimenticato la password?</a>
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
