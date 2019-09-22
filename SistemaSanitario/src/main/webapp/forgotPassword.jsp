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
        <div class="container border" style="max-width: 400px;">
        <form action="ForgotPasswordServlet">    
             <div class="form-row">
                 <div class="form-group col text-center">
                    <p><h3>Password dimenticata?</h3></p>
                    <p>Inserisci il tuo indirizzo email e segui il procedimento per il ripristino della password</p>
                 </div>
             </div>
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="email" class="form-control" name="email" placeholder="inserisci email">
                    <label for="email" class="active" style="transition: none 0s ease 0s; width: auto;">Email</label>
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

