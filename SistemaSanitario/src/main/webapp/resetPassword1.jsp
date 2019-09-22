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
        <div class="container border" style="max-width: 400px;">
        <form action="LoginServlet">    
             <div class="form-row">
                 <div class="form-group col text-center">
                    <p><h3>Ripristina password</h3></p>
                 </div>
             </div>
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="password" class="form-control input-password" name="password" placeholder="inserisci nuova password">
                    <span class="password-icon" aria-hidden="true">
                      <svg class="password-icon-visible icon icon-sm"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-visible"></use></svg>
                      <svg class="password-icon-invisible icon icon-sm d-none"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-invisible"></use></svg>
                    </span>
                    <label for="password" class="active" style="transition: none 0s ease 0s; width: auto;">Nuova password</label>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="password" class="form-control input-password" name="confirm" placeholder="conferma nuova password">
                    <span class="password-icon" aria-hidden="true">
                      <svg class="password-icon-visible icon icon-sm"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-visible"></use></svg>
                      <svg class="password-icon-invisible icon icon-sm d-none"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-invisible"></use></svg>
                    </span>
                    <label for="confirm" class="active" style="transition: none 0s ease 0s; width: auto;">Conferma password</label>
                </div>
            </div>
            </form>
            <div class="form-row">
                <div class="form-group col-12 text-center">
                        <button type="submit" class="btn btn-primary">Conferma</button>
                </div>
            </div>
        </div>
         <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
    </body>
</html>

