
<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <title>Sistema Sanitario</title>
  <%@ include file="./WEB-INF/jspf/meta.jspf" %> 

</head>

<body>

  <!-- HEADER -->
  <div class="it-header-wrapper it-header-sticky">
    <div class="it-nav-wrapper">
      <!-- HEADER CENTER -->
      <div class="it-header-center-wrapper">
        <div class="container container-extended" style="width:100%;">
          <div class="row">
            <div class="col-12">
              <div class="it-header-center-content-wrapper">
                <div class="it-brand-wrapper">
                  <a href="#">
                    <svg class="icon">
                      <use xlink:href="svg/sprite.svg#it-pa"></use>
                    </svg>
                    <div class="it-brand-text">
                      <h2 class="no_toc">Sistema Sanitario Pregiato</h2>
                      <h3 class="no_toc d-none d-md-block">Il sisema sanitario come lo voi tu</h3>
                    </div>
                  </a>
                </div>
                 <!-- ACCEDI AREA PERSONALE-->
                <div class="it-right-zone">
                      <div class="it-search-wrapper">
                      <a href="login" class="btn btn-primary btn-icon">
                        <span class="rounded-icon">
                          <svg class="icon icon-primary">
                            <use xlink:href="svg/sprite.svg#it-user"></use>
                          </svg>
                        </span>
                        <span class="d-none d-lg-block" id="">Accedi all'area personale</span>
                      </a>
                        </div>
                       <!--
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
                        -->
                      
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- HEADER NAV BAR -->
      <div class="it-header-navbar-wrapper">
        <div class="container container-extended">
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
  
  
  <!-- FOOTER -->
  <footer class="it-footer">
  <div class="it-footer-main">
    <div class="container">
      <section>
        <div class="row clearfix">
          <div class="col-sm-12">
            <div class="it-brand-wrapper">
              <a href="#">
                <svg class="icon">
                  <use xlink:href="/bootstrap-italia/dist/svg/sprite.svg#it-code-circle"></use>
                </svg>
                <div class="it-brand-text">
                  <h2 class="no_toc">Lorem Ipsum</h2>
                  <h3 class="no_toc d-none d-md-block">Inserire qui la tag line</h3>
                </div>
              </a>
            </div>
          </div>
        </div>
      </section>
      <section>
        <div class="row">
          <div class="col-lg-3 col-md-3 col-sm-6 pb-2">
            <h4>
              <a href="#" title="Vai alla pagina: Amministrazione">Amministrazione</a>
            </h4>
            <div class="link-list-wrapper">
              <ul class="footer-list link-list clearfix">
                <li><a class="list-item" href="#" title="Vai alla pagina: Giunta e consiglio">Giunta e consiglio</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Aree di competenza">Aree di competenza</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Dipendenti">Dipendenti</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Luoghi">Luoghi</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Associazioni e società partecipate">Associazioni e società partecipate</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-6 pb-2">
            <h4>
              <a href="#" title="Vai alla pagina: Servizi">Servizi</a>
            </h4>
            <div class="link-list-wrapper">
              <ul class="footer-list link-list clearfix">
                <li><a class="list-item" href="#" title="Vai alla pagina: Pagamenti">Pagamenti</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Sostegno">Sostegno</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Domande e iscrizioni">Domande e iscrizioni</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Segnalazioni">Segnalazioni</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Autorizzazioni e concessioni">Autorizzazioni e concessioni</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Certificati e dichiarazioni">Certificati e dichiarazioni</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-6 pb-2">
            <h4>
              <a href="#" title="Vai alla pagina: Novità">Novità</a>
            </h4>
            <div class="link-list-wrapper">
              <ul class="footer-list link-list clearfix">
                <li><a class="list-item" href="#" title="Vai alla pagina: Notizie">Notizie</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Eventi">Eventi</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Comunicati stampa">Comunicati stampa</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-6">
            <h4>
              <a href="#" title="Vai alla pagina: Documenti">Documenti</a>
            </h4>
            <div class="link-list-wrapper">
              <ul class="footer-list link-list clearfix">
                <li><a class="list-item" href="#" title="Vai alla pagina: Progetti e attività">Progetti e attività</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Delibere, determine e ordinanze">Delibere, determine e ordinanze</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Bandi">Bandi</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Concorsi">Concorsi</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: Albo pretorio">Albo pretorio</a></li>
              </ul>
            </div>
          </div>
        </div>
      </section>
      <section class="py-4 border-white border-top">
        <div class="row">
          <div class="col-lg-4 col-md-4 pb-2">
            <h4><a href="#" title="Vai alla pagina: Contatti">Contatti</a></h4>
            <p>
              <strong>Comune di Lorem Ipsum</strong><br> Via Roma 0 - 00000 Lorem Ipsum Codice fiscale / P. IVA: 000000000
            </p>
            <div class="link-list-wrapper">
              <ul class="footer-list link-list clearfix">
                <li><a class="list-item" href="#" title="Vai alla pagina: Posta Elettronica Certificata">Posta Elettronica Certificata</a></li>
                <li><a class="list-item" href="#" title="Vai alla pagina: URP - Ufficio Relazioni con il Pubblico">URP - Ufficio Relazioni con il Pubblico</a></li>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
  <div class="it-footer-small-prints clearfix">
    <div class="container">
      <h3 class="sr-only">Sezione Link Utili</h3>
      <ul class="it-footer-small-prints-list list-inline mb-0 d-flex flex-column flex-md-row">
        <li class="list-inline-item"><a href="#" title="Note Legali">Media policy</a></li>
        <li class="list-inline-item"><a href="#" title="Note Legali">Note legali</a></li>
        <li class="list-inline-item"><a href="#" title="Privacy-Cookies">Privacy policy</a></li>
        <li class="list-inline-item"><a href="#" title="Mappa del sito">Mappa del sito</a> </li>
      </ul>
    </div>
  </div>
</footer>
  
  

  <script type="text/javascript" src="js/main.js"></script>
  <script type="text/javascript" src="js/bootstrap-italia.bundle.min.js"></script>
</body>

</html>