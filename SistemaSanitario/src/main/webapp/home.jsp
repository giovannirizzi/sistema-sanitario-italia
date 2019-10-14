
<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <title>Sistema Sanitario</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <link rel="stylesheet" type="text/css" href="css/bootstrap-italia.min.css">
  <link rel="stylesheet" type="text/css" href="css/main.css">

</head>

<body>

  <!-- HEADER -->
  <div class="it-header-navbar-wrapper">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <!--start nav-->
        <nav class="navbar navbar-expand-lg has-megamenu">
          <div class="navbar-collapsable" id="nav1" style="display: none;">
            <div class="overlay" style="display: none;"></div>
            <div class="close-div sr-only">
              <button class="btn close-menu" type="button"><span class="it-close"></span>close</button>
            </div>
            <div class="row">
                <div class="col-4 d-flex">
                    <div class="it-header-slim-wrapper-content ml-5 d-flex justify-content-start">
                        <div class="it-brand-wrapper d-none d-lg-flex align-items-center">
                            <a href="./">
                                <div class="it-brand-text d-flex align-items-center">
                                  <h3 style="color:white;">Sistema Sanitario</h3>
                                </div>
                            </a>
                        </div> 
                    </div>
                 </div>
                    
                <div class="menu-wrapper">
                  <ul class="navbar-nav">
                    <li class="nav-item active"><a class="nav-link active" href="#"><span>Esami</span><span class="sr-only">current</span></a></li>
                    <li class="nav-item"><a class="nav-link disabled" href="#"><span>Medico</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><span>Ricette</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="#"><span>Profilo</span></a></li>
                  </ul>
                </div>
          </div>
          </div>
        </nav>
      </div>
    </div>
  </div>
</div>
  <div class="container mt-3 d-none" >
    <div class="row">
        <div class="col-12">
            <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">Cognome</th>
            <th scope="col">Username</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>Mario</td>
            <td>Verdi</td>
            <td>mario.verdi</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>Francesco</td>
            <td>Bianchi</td>
            <td>francesco.bianchi</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td>Alessandro</td>
            <td>Rossi</td>
            <td>alessandro.rossi</td>
          </tr>
        </tbody>
      </table>
        </div>
    </div>
  </div>
  <div class="container mt-3">
      <div class="it-list-wrapper">
        <ul class="it-list">
          <li>
            <a href="#">
              <div class="it-right-zone">
                <span class="text">RX Mano DX</span>
                <svg class="icon">
                  <use xlink:href="/SistemaSanitario/svg/sprite.svg#it-more-actions"></use>
                </svg>
              </div>
            </a>
          </li>
          <li>
            <a>
            <div class="it-right-zone">
                <p>
                    La radiografia (RX), o esame radiologico, è un'indagine diagnostica che prevede l'utilizzo di particolari onde elettromagnetiche, i raggi X, in grado di attraversare il corpo umano e di essere misurati da un sistema fotosensibile analogamente a un sistema fotografico.
                </p>
            </div>
            </a>
          </li>
          <li>
            <a class="active" href="#">
              <div class="it-right-zone">
                <span class="text">Analisi del sangue</span>
                <svg class="icon">
                  <use xlink:href="/SistemaSanitario/svg/sprite.svg#it-more-actions"></use>
                </svg>
              </div>
            </a>
          </li>
        </ul>
      </div>
  </div>
  <script type="text/javascript" src="js/main.js"></script>
  <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
</body>

</html>