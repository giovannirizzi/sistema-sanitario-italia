
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
        
        <link rel="stylesheet" type="text/css" href="css/bootstrap-italia.min.css">
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
        <br> <br>  
        <div class="container mw-75">
            <h1> Please Login </h1> <br><br>
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="text" class="form-control" id="inputUsername" placeholder="inserisci il tuo username">
                    <label for="inputUsername" class="active" style="transition: none 0s ease 0s; width: auto;">Username</label>
                </div>
            </div>    
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="password" class="form-control input-password" id="exampleInputPassword" placeholder="inserisci la tua password">
                    <span class="password-icon" aria-hidden="true">
                      <svg class="password-icon-visible icon icon-sm"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-visible"></use></svg>
                      <svg class="password-icon-invisible icon icon-sm d-none"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-invisible"></use></svg>
                    </span>
                    <label for="exampleInputPassword" class="active" style="transition: none 0s ease 0s; width: auto;">Password</label>
                </div>
            </div>
            <div class="col-auto">
                <div class="form-check m-0">
                    <button type="submit" class="btn btn-primary">Invia</button>
                    <input class="form-check-input" type="checkbox" id="autoSizingCheck">
                    <label class="form-check-label" for="autoSizingCheck">
                        Ricordami
                    </label>
                    
                </div>

            </div>
        </div>
    </body>
</html>
