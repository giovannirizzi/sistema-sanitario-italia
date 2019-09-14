<%-- 
    Document   : index
    Created on : 13 set 2019, 15:04:27
    Author     : giovanni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
  <div class="it-header-wrapper it-header-sticky">
    <div class="it-nav-wrapper">
      <!-- HEADER CENTER -->
      <div class="it-header-center-wrapper">
        <div class="container">
          <div class="row">
            <div class="col-12">
              <div class="it-header-center-content-wrapper">
                <div class="it-brand-wrapper">
                  <a href="#">
                    <svg class="icon">
                      <use xlink:href="svg/sprite.svg#it-code-circle"></use>
                    </svg>
                    <div class="it-brand-text">
                      <h2 class="no_toc">Lorem Ipsum Lorem Ipsum</h2>
                      <h3 class="no_toc d-none d-md-block">Inserire qui la tag line</h3>
                    </div>
                  </a>
                </div>
                 <!-- ACCEDI AREA PERSONALE-->
                <div class="it-right-zone">
                  <!--
                      <div class="it-search-wrapper">
                      <a href="#" class="btn btn-primary btn-icon">
                        <span class="rounded-icon">
                          <svg class="icon icon-primary">
                            <use xlink:href="svg/sprite.svg#it-user"></use>
                          </svg>
                        </span>
                        <span class="d-none d-lg-block" id="hide-cloned">Accedi all'area personale</span>
                      </a>
                        </div>
                      -->
                  <div class="it-search-wrapper">
                    <div class="nav-item dropdown">

                      <a href="#" class="btn btn-primary btn-icon rounded" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true">
                        <span class="rounded-icon">
                          <svg class="icon icon-primary">
                            <use xlink:href="svg/sprite.svg#it-user"></use>
                          </svg>
                        </span>
                        <span class="d-none d-lg-block" id="hide-cloned">Accedi all'area personale</span>
                      </a>

                      <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <div class="link-list-wrapper">
                          <ul class="link-list">
                            <li>
                              <h3 id="esempio-header-1">Mario Rossi</h3>
                            </li>

                            <li><a class="list-item" href="#"><span>Area personale</span></a></li>

                            <li>
                              <span class="divider"></span>
                            </li>
                            <li><a class="list-item" href="#"><span>Disconnetti</span></a></li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- HEADER NAV BAR -->
      <div class="it-header-navbar-wrapper">
        <div class="container">
          <div class="row">
            <div class="col-12">
              <!--start nav-->
              <nav class="navbar navbar-expand-lg ">
                <button class="custom-navbar-toggler" type="button" aria-controls="nav02" aria-expanded="false"
                  aria-label="Toggle navigation" data-target="#nav02">
                  <svg class="icon">
                    <use xlink:href="svg/sprite.svg#it-burger"></use>
                  </svg>
                </button>
                <div class="navbar-collapsable" id="nav02" style="display: none;">
                  <div class="overlay" style="display: none;"></div>
                  <div class="close-div sr-only">
                    <button class="btn close-menu" type="button"><span class="it-close"></span>close</button>
                  </div>
                  <div class="menu-wrapper">
                    <ul class="navbar-nav">
                      <li class="nav-item active"><a class="nav-link active" href="#"><span>link 1</span><span
                            class="sr-only">current</span></a></li>
                      <li class="nav-item"><a class="nav-link" href="#sezione-di-esempio"><span>link 2</span></a></li>
                      <li class="nav-item"><a class="nav-link" href="#"><span>link 3</span></a></li>
                      <li class="nav-item"><a class="nav-link" href="#"><span>link 4</span></a></li>
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false">
                          <span>Dropdown item</span>
                          <svg class="icon icon-xs">
                            <use xlink:href="svg/sprite.svg#it-expand"></use>
                          </svg>
                        </a>
                        <div class="dropdown-menu">
                          <div class="link-list-wrapper">
                            <ul class="link-list">
                              <li>
                                <h3 class="no_toc" id="heading-es-5">Heading</h3>
                              </li>
                              <li><a class="list-item" href="#"><span>Link list 1</span></a></li>
                              <li><a class="list-item" href="#"><span>Link list 2</span></a></li>
                              <li><a class="list-item" href="#"><span>Link list 3</span></a></li>
                              <li><span class="divider"></span></li>
                              <li><a class="list-item" href="#"><span>Link list 4</span></a></li>
                            </ul>
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
            </div>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- HERO -->
  <div class="it-hero-wrapper it-dark it-overlay">
    <!-- - img-->
    <div class="img-responsive-wrapper">
      <div class="img-responsive">
        <div class="img-wrapper"><img
            src="https://animals.sandiegozoo.org/sites/default/files/2016-08/animals_hero_mountains.jpg"
            title="img title" alt="imagealt"></div>
      </div>
    </div>
    <!-- - texts-->
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="it-hero-text-wrapper bg-dark">
            <span class="it-category">Category</span>
            <h1 class="no_toc">Heading lorem ipsum dolor sit amet, consetetur sadipscing.</h1>
            <p class="d-none d-lg-block">Platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper dignissim
              cras. Dictum sit amet justo donec enim diam vulputate ut. Eu nisl nunc mi ipsum faucibus.</p>
            <div class="it-btn-container"><a class="btn btn-sm btn-secondary" href="#">Label button</a></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- SECTION -->
  <div class="section section-muted">
    <div class="section-content">
      <!-- contenuto di esempio START -->
      <div class="container">
        <div class="row mb-3">
          <div class="col-12">
            <h4>Morbi fermentum amet</h4>
          </div>
        </div>
        <div class="row">
          <div class="col-12 col-lg-6 col-xl-4 pr-0 pr-md-5 mb-3">Platea dictumst vestibulum rhoncus est pellentesque
            elit ullamcorper dignissim cras. Dictum sit amet justo donec enim diam vulputate ut. Eu nisl nunc mi ipsum
            faucibus.</div>
          <div class="col-12 col-lg-6 col-xl-4 pr-0 pr-md-5 mb-3">Eget egestas purus viverra accumsan. Diam maecenas
            ultricies mi eget mauris pharetra et. Etiam dignissim diam quis enim. Eu nisl nunc mi ipsum faucibus.</div>
          <div class="col-12 col-lg-6 col-xl-4 pr-0 pr-md-5">Euismod lacinia at quis risus sed vulputate. Scelerisque
            purus semper eget duis at tellus at urna condimentum. Mattis enim ut tellus elementum sagittis.</div>
        </div>
      </div>
      <!-- contenuto di esempio END -->
    </div>
  </div>

  <!-- SECTION -->
  <div class="section section-muted" id="sezione-di-esempio">
    <div class="section-content">
      <!-- contenuto di esempio START -->
      <div class="container">
        <div class="row mb-3">
          <div class="col-12">
            <h4>Morbi fermentum amet</h4>
          </div>
        </div>
        <div class="row">
          <div class="col-12 col-lg-6 col-xl-4 pr-0 pr-md-5 mb-3">Platea dictumst vestibulum rhoncus est pellentesque
            elit ullamcorper dignissim cras. Dictum sit amet justo donec enim diam vulputate ut. Eu nisl nunc mi ipsum
            faucibus.</div>
          <div class="col-12 col-lg-6 col-xl-4 pr-0 pr-md-5 mb-3">Eget egestas purus viverra accumsan. Diam maecenas
            ultricies mi eget mauris pharetra et. Etiam dignissim diam quis enim. Eu nisl nunc mi ipsum faucibus.</div>
          <div class="col-12 col-lg-6 col-xl-4 pr-0 pr-md-5">Euismod lacinia at quis risus sed vulputate. Scelerisque
            purus semper eget duis at tellus at urna condimentum. Mattis enim ut tellus elementum sagittis.</div>
        </div>
      </div>
      <!-- contenuto di esempio END -->
    </div>
  </div>

  <!-- BACK TO TOP -->
  <div class="d-flex align-items-center">
    <a href="#" aria-hidden="true" data-attribute="back-to-top" class="back-to-top shadow">
      <svg class="icon icon-light">
        <use xlink:href="svg/sprite.svg#it-arrow-up"></use>
      </svg>
    </a>
  </div>

  <script type="text/javascript" src="js/main.js"></script>
  <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
</body>

</html>