<%-- 
    Document   : profile
    Created on : 16 ott 2019, 10:18:08
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sistema Sanitario</title>
        <%@ include file="./WEB-INF/jspf/meta.jspf" %> 
    </head>
    <body>
        <%@ include file="./WEB-INF/jspf/header.jspf" %>
        <div class="container">
            <nav class="breadcrumb-container" aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="#">Home</a><span class="separator">/</span></li>
                  <li class="breadcrumb-item"><a href="#">Subsection</a><span class="separator">/</span></li>
                  <li class="breadcrumb-item active" aria-current="page">Current section</li>
                </ol>
            </nav>
            <div class="section">
                <div class="section-content">
                        <div class="row">
                            <div class="col-md-5 d-flex ">
                                <div class="d-flex flex-column mb-3 justify-content-center align-items-center">
                                    <div class="p-2"><img src="https://via.placeholder.com/240x120/ebebeb/808080/?text=Immagine" class="rounded-circle" width="200px" height="200px"></div>
                                    <div class="p-2">
                                        <form method="post" action="" enctype="multipart/form-data">
                                            <label class="d-flex justify-content-center" for="upload1">Cambia immagine</label>
                                            <input type="file" name="upload1" id="upload1" multiple="multiple" style="visibility:hidden;"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-7">
                                <table class="table">
                                    <tbody>
                                      <tr>
                                        <td scope="row"><h3>Mario Rossi</h3></td>
                                        <td></td>
                                      </tr>
                                      <tr>
                                        <td scope="row"><svg class="icon icon-secondary"><use xlink:href="./svg/sprite.svg#it-mail"></use></svg></td>
                                        <td>mario.rossi@gmail.com</td>
                                      </tr>
                                      <tr>
                                        <th scope="row"><svg class="icon icon-secondary"><use xlink:href="./svg/sprite.svg#it-card"></use></svg></th>
                                        <td>RSS MRA 75 L01 H501 A</td>
                                      </tr>
                                      <tr>
                                        <th scope="row"><svg class="icon icon-secondary"><use xlink:href="./svg/sprite.svg#it-pin"></use></svg></th>
                                        <td>Roma (RM)</td>
                                      </tr>
                                      <tr>
                                        <th scope="row"><svg class="icon icon-secondary"><use xlink:href="./svg/sprite.svg#it-user"></use></svg></th>
                                        <td>M</td>
                                      </tr>
                                    </tbody>
                                </table>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                Modifica password
                                </button>
                            </div>
                        </div>
                </div>
            </div>
            
            <!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" tabindex="-1" role="dialog" id="exampleModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Modifica password</h4>
      </div>
      <div class="modal-body">
        <form action=" " method="POST">
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="text" class="form-control" name="username" placeholder="inserisci password attuale">
                    <label for="username" class="active" style="transition: none 0s ease 0s; width: auto;">Password attuale</label>
                </div>
            </div>    
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="password" class="form-control input-password" name="password" placeholder="inserisci nuova password">
                    <span class="password-icon" aria-hidden="true">
                      <svg class="password-icon-visible icon icon-sm"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-visible"></use></svg>
                      <svg class="password-icon-invisible icon icon-sm d-none"><use xlink:href="/SistemaSanitario/svg/sprite.svg#it-password-invisible"></use></svg>
                    </span>
                    <label for="password" class="active" style="transition: none 0s ease 0s; width: auto;">Password nuova</label>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col text-center">
                    <input type="password" class="form-control input-password" name="password" placeholder="conferma nuova password">
                    <span class="password-icon" aria-hidden="true">
                      <svg class="password-icon-visible icon icon-sm"><use xlink:href="./svg/sprite.svg#it-password-visible"></use></svg>
                      <svg class="password-icon-invisible icon icon-sm d-none"><use xlink:href="./svg/sprite.svg#it-password-invisible"></use></svg>
                    </span>
                    <label for="password" class="active" style="transition: none 0s ease 0s; width: auto;">Conferma password nuova</label>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary">Conferma</button>
                </div>
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary btn-sm" data-dismiss="modal" type="button">Close</button>
      </div>
    </div>
  </div>
</div>
            
            
    </body>
</html>
