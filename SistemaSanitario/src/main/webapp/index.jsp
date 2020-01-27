<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="it">
<head> 
    <title>Sistema Sanitario Italia</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="${context}/css/bootstrap-italia.min.css">
    <link rel="stylesheet" type="text/css" href="${context}/css/main.css">
</head>

<body>
    
    <div id="main">
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
                  <a data-attribute="forward"  href="#main">
                    <svg class="icon">
                      <use xlink:href="svg/sprite.svg#it-pa"></use>
                    </svg>
                    <div class="it-brand-text">
                      <h2 class="no_toc">Sistema Sanitario Italia</h2>
                      <h3 class="no_toc d-none d-md-block">Il nuovo portale della sanità online</h3>
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
                      <li class="nav-item active"><a class="nav-link active forward" data-attribute="forward"  href="#main"><span>Home</span></a></li>
                      <li class="nav-item"><a class="nav-link forward" data-attribute="forward" href="#servizi"><span>Servizi</span></a></li>
                      <li class="nav-item"><a class="nav-link forward" data-attribute="forward" href="#chisiamo"><span>Chi siamo</span></a></li>
                      <li class="nav-item"><a class="nav-link forward" data-attribute="forward" href="#novita"><span>Novita</span></a></li>
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
  <div class="it-hero-wrapper it-dark it-overlay" >
    <!-- - img-->
    <div class="img-responsive-wrapper">
      <div class="img-responsive">
        <div class="img-wrapper"><img src="./images/hero2.png" title="img title" alt="imagealt"></div>
      </div>
    </div>
    <!-- - texts-->
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="it-hero-text-wrapper bg-dark">
            <h1 class="no_toc">Sistema Sanitario Italia</h1>
            <h4 class= "no_toc text-white">Il nuovo canale tematico dedicato alla salute dei cittadini e ai servizi sanitari regionali.</h4>
            <p class="d-none d-lg-block mt-2">
             Questa è la piattaforma elettronica che consente ai cittadini residenti nelle province di Trento e Bolzano di fruire dei servizi connessi alla loro salute. 
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- SECTION SERVIZI -->
  <a class="anchor" id="servizi"></a>
  <div class="section section-muted">
    <div class="section-content">
      <!-- contenuto di esempio START -->
      <div class="container">
        <div class="row mb-3">
          <div class="col-12">
            <h2 class="text-center" >Servizi del cittadino</h2>
          </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-4">
              <!--start card-->
              <div class="card-wrapper card-space">
                <div class="card card-img card-bg no-after">
                  <div class="img-responsive-wrapper">
                    <div class="img-responsive">
                      <figure class="img-wrapper">
                          <img src="./images/cambiaMedico2.jpg" title="img medico" alt="medico">
                      </figure>
                    </div>
                  </div>
                  <div class="card-body">
                    <h5 class="card-title">Cambia il medico</h5>
                    <p>Cambia il tuo medico di base o consulta i medici disponibili nella tua provincia.</p>
                  </div>
                </div>
              </div>
              <!--end card-->
            </div>
            <div class="col-12 col-lg-4">
              <!--start card-->
              <div class="card-wrapper card-space">
                <div class="card card-img card-bg no-after">
                  <div class="img-responsive-wrapper">
                    <div class="img-responsive">
                      <figure class="img-wrapper">
                        <img src="./images/ricette.png" title="img ricette" alt="ricette">
                      </figure>
                    </div>
                  </div>
                  <div class="card-body">
                    <h5 class="card-title">Ritira le ricette</h5>
                    <p>Scarica e stampa le ricette dematerializzate che ti sono state prescritte.</p>
                  </div>
                </div>
              </div>
              <!--end card-->
            </div>
            <div class="col-12 col-lg-4">
              <!--start card-->
              <div class="card-wrapper card-space">
                <div class="card card-img card-bg no-after">
                  <div class="img-responsive-wrapper">
                    <div class="img-responsive">
                      <figure class="img-wrapper">
                        <img src="./images/referti.png" title="img referti" alt="referti">
                      </figure>
                    </div>
                  </div>
                  <div class="card-body">
                    <h5 class="card-title">Visualizza la cartella clinica</h5>
                    <p>Consulta e scarica i referti di tutti gli esami che hai svolto e visualizza i ticket.</p>
                  </div>
                </div>
              </div>
              <!--end card-->
            </div>
        </div>
      </div>
      <!-- contenuto di esempio END -->
    </div>
  </div>
 
   <!-- SECTION CHI-SIAMO -->
   <a class="anchor" id="chisiamo"></a>
    <div class="section bg-dark p-0 section-3">
      <div class="section-content">
        <div class="container white-color">
          <div class="row">
            <div class="col-12 col-lg-2 col-right-bg order-2">
              <div class="col-right-bg-inner bg-dark"></div>
            </div>
            <div class="col-12 col-lg-10 px-4 py-5 order-1 bg-dark">
              <h2>Chi Siamo</h2>
                <p>Progetto Sanità è stato attivato nel 2019, il servizio è stato implementato per consentire ai cittadini di avere un unico interlocutore. <br>
                 La piattaforma permette di prenotare visite ed esami della sanità pubblica e privata.
                 Progetto Sanità consente la prenotazione online con i browser Explorer, FireFox e GoogleChrome. <br>
                 Con questo servizio si può consultare in tempo reale la disponibilità delle prestazioni e l’attesa. <br>
                 Le modalità di prenotazione delle visite sono telefonica, online e presso uno degli sportelli dislocati sul territorio provinciale. 
                </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    
   <!-- SECTION NOVITA -->
    <a class="anchor" id="novita"></a>
    <section>
    <div class="py-5">
      <div class="container px-4">
        <div class="row">
          <div class="col">
            <h3 class="mb-4 text-primary">Tutte le novità</h3>
          </div>
        </div>
        <div class="row">
          <div class="col-12 col-sm-6 col-lg-4">
            <!--start card-->
            <div class="card-wrapper">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title big-heading">Ultime notizie</h5>
                  <p class="card-text">Si chiama Stereotaxis il robot 'elettricista del cuore' che, all'ospedale Mauriziano di Torino,
                      ha salvato la vita ad un paziente di 65 anni affetto da una grave aritmia cardiaca ventricolare. 
                      Il sistema è stato utilizzato, per la prima volta in Italia, per una procedura di ablazione.</p>
                  <a class="read-more" href="#">
                    <span class="text">Leggi di più</span>
                    <svg class="icon">
                      <use xlink:href="svg/sprite.svg#it-arrow-right"></use>
                    </svg>
                  </a>
                </div>
              </div>
            </div>
            <!--end card-->
          </div>
          <div class="col-12 col-sm-6 col-lg-4">
            <!--start card-->
            <div class="card-wrapper">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title big-heading">Comunicati</h5>
                  <p class="card-text">Cup Trentino, prenotazioni sospese per un guasto al server.<br>
                      Attivo il nuovo numero per prenotare col cellulare.
                  </p>
                </div>
              </div>
            </div>
            <!--end card-->
          </div>
          <div class="col-12 col-sm-6 col-lg-4">
            <!--start card-->
            <div class="card-wrapper">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title big-heading">Eventi</h5>
                  <p class="card-text">
                      L’Apss organizza la Giornata della trasparenza come occasione per illustrare le azioni che essa intraprende
                        e attua per la promozione della cultura della trasparenza, della legalità, e per rendicontare le attività e le
                        performance dell’azienda sanitaria.
                  </p>
                </div>
              </div>
            </div>
            <!--end card-->
          </div>
        </div>
      </div>
    </div>
  </section>    
    
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
                  <use
                    xlink:href="svg/sprite.svg#it-pa"></use>
                </svg>
                <div class="it-brand-text">
                  <h2 class="no_toc">Sistema Sanitario Italia</h2>
                  <h3 class="no_toc d-none d-md-block">
                    Il nuovo portale della sanità online
                  </h3>
                </div>
              </a>
            </div>
          </div>
        </div>
      </section>
      <section>
        <div class="row">
          <div class="col-lg-3 col-md-3 pb-2">
            <h4>
             <a>Contatti</a>
            </h4>
            <p>
              <strong>Comune di Trento</strong><br/>
              Via Alcide Degasperi 79 - 38123 Trento <br/> Codice fiscale / P. IVA: 01429410226
            </p>
            <p>
              <strong>Telefono</strong><br/>
              848 816 816 <br/>
              Posta elettronica certificata: urp@apss.tn.it
            </p>
          </div>
            <div class="col-lg-3 col-md-3 pb-2">
            <h4>
              <a>Servizi</a>
            </h4>
            <div class="link-list-wrapper">
              <ul class="footer-list link-list clearfix">
                <li>
                  <a class="list-item">Cambia il medico </a>
                </li>
                <li>
                  <a class="list-item">Rititra le ricette </a>
                </li>
                <li>
                  <a class="list-item">Visualizza la cartella clinica </a>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 pb-2">
            <h4>
              <a href="#" title="Vai alla pagina: Newsletter">Newsletter</a>
            </h4>
            <form action="#" class="form-newsletter" method="post">
              <label
                class="text-white font-weight-semibold"
                for="input-newsletter">Iscriviti per riceverla</label>
              <input
                class="form-control"
                id="input-newsletter"
                name="input-newsletter"
                placeholder="mail@example.com"
                type="email"
              />
              <button class="btn btn-primary btn-icon" type="submit">
                <svg class="icon icon-white">
                  <use
                    xlink:href="svg/sprite.svg#it-mail"></use>
                </svg>
                <span>Iscriviti</span>
              </button>
            </form>
          </div>
          <div class="col-lg-3 col-md-3 pb-2">
            <h4>
              <a href="#" title="Vai alla pagina: Seguici su">Seguici su</a>
            </h4>
            <ul class="list-inline text-left social">
              <li class="list-inline-item">
                <a class="p-2 text-white" href="#" target="_blank">
                  <svg class="icon icon-sm icon-white align-top">
                    <use
                      xlink:href="svg/sprite.svg#it-designers-italia"></use>
                  </svg>
                  <span class="sr-only">Designers Italia</span></a>
              </li>
              <li class="list-inline-item">
                <a class="p-2 text-white" href="#" target="_blank">
                  <svg class="icon icon-sm icon-white align-top">
                    <use
                      xlink:href="svg/sprite.svg#it-twitter"></use>
                  </svg>
                  <span class="sr-only">Twitter</span></a>
              </li>
              <li class="list-inline-item">
                <a class="p-2 text-white" href="#" target="_blank">
                  <svg class="icon icon-sm icon-white align-top">
                    <use
                      xlink:href="svg/sprite.svg#it-medium"></use>
                  </svg>
                  <span class="sr-only">Medium</span></a>
              </li>
              <li class="list-inline-item">
                <a class="p-2 text-white" href="#" target="_blank">
                  <svg class="icon icon-sm icon-white align-top">
                    <use
                      xlink:href="svg/sprite.svg#it-behance"></use>
                  </svg>
                  <span class="sr-only">Behance</span></a>
              </li>
            </ul>
          </div>
        </div>
      </section>
    </div>
  </div>
  <div class="it-footer-small-prints clearfix">
    <div class="container">
      <h3 class="sr-only">Sezione Link Utili</h3>
      <ul
        class="it-footer-small-prints-list list-inline mb-0 d-flex flex-column flex-md-row">
        <li class="list-inline-item">
          <a href="#" title="Note Legali">Media policy</a>
        </li>
        <li class="list-inline-item">
          <a href="#" title="Note Legali">Note legali</a>
        </li>
        <li class="list-inline-item">
          <a href="#" title="Privacy-Cookies">Privacy policy</a>
        </li>
        <li class="list-inline-item">
          <a href="#" title="Mappa del sito">Mappa del sito</a>
        </li>
      </ul>
    </div>
  </div>
</footer>
  
  </div>

<script type="text/javascript" src="${context}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${context}/js/main.js"></script>
<script type="text/javascript" src="${context}/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="${context}/js/popper.min.js"></script>
<script type="text/javascript" src="${context}/js/bootstrap-italia.min.js"></script>
</body>

</html>