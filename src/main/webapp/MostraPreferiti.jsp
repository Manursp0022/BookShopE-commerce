<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.swing.*" %>
<%@ page import="Model.Bean.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="CSS/styles.css">
  <link rel="stylesheet" href="CSS/visLibri.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
  <title>E-CommerceBook</title>
  <script src="JavaScript/jquery-3.7.0.js"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,wght@1,700&family=Rubik:ital,wght@1,300&display=swap" rel="stylesheet">
  <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,wght@1,700&family=Rubik:ital,wght@1,300&display=swap" rel="stylesheet">
</head>
<body>
<%
  Carrello cart = (Carrello) request.getSession(false).getAttribute("cart");
  int nprod;
  if(cart == null){
    nprod = 0;
  }
  else{
    nprod = cart.getnLibri();
  }
  int mode = (int) request.getSession(false).getAttribute("mode");
  List<PreferitoC> prefC = (ArrayList<PreferitoC>) request.getSession().getAttribute("prefC");
  List<PreferitoE> prefE = (ArrayList<PreferitoE>) request.getSession().getAttribute("prefE");
  List<String> codiciPref = new ArrayList<>();
  if(prefC != null){
    for(PreferitoC pref : prefC){
      codiciPref.add(pref.getLibroCartaceo());
    }
  }
  if(prefE != null) {
    for (PreferitoE pref : prefE) {
      codiciPref.add(pref.getLibroElettronico());
    }
  }
%>
<header class="header">

  <div class="HeaderfirstDiv">

    <div class="SonFirstDiv">

      <div class="homeElement">
        <a class="logoc" href="homepage.jsp">
          <img class="imgOptions" src="CSS/LogoOff.png">
        </a>
      </div>

      <ul class="nav">
        <li><a href="MostraLibriServlet?id=1" class="menuItem">Libri</a></li>
        <li><a href="MostraLibriServlet?id=2" class="menuItem">Bambini e Ragazzi</a></li>
        <li><a href="MostraLibriServlet?id=3" class="menuItem">Fumetti e manga</a></li>
        <li><a href="MostraLibriServlet?id=4" class="menuItem">Libri Vintage</a></li>
        <li><a href="MostraLibriServlet?id=5" class="menuItem">E-Book</a></li>
      </ul>


      <form class="formSearch" action="search-servlet">
        <input class="searchpanel" type="text" name="search" placeholder="Cerca">
        <div class="divForSearch">
          <lord-icon
                  class="searchBotton"
                  src="https://cdn.lordicon.com/xfftupfv.json"
                  trigger="hover"
                  colors="primary:#2516c7"
                  style="width:28px;height:28px">
          </lord-icon>
        </div>
        </input>
      </form>

      <div class="HeaderQuick">
        <a class="noDec" href="MostraPreferitiServlet"><button class="forButton">PREFERITI</button></a>
        <%
          if(mode == 2 || mode == 1){%>
        <a style="text-decoration: none" href="LogOutServlet"><button class="forButton">LOG-OUT</button></a>
        <%}else{%><a style="text-decoration: none" href="Login.jsp"><button class="forButton">LOG-IN</button></a><%}%>
        <a style="text-decoration: none" href="#"><button class="forButton">CARRELLO(<span id="num_prod"><%=nprod%></span>)</button></a>
      </div>

      <div class="HeaderQuick2">
        <div><a href=""><img src="CSS/ShopBag2.svg"></a></div>
        <div><a href="Login.jsp"><img src="CSS/Account.svg"></a></div>
      </div>

      <div class="hamburger" id="openBr">
        <div class="HambIcon">          <!-- come se fosse <i> -->
          <a><img src="CSS/134216_menu_lines_hamburger_icon.svg"></a>
        </div>
        <div class="PrefersResp">
          <a href="MostraPreferitiServlet"><img src="CSS/Heart3.svg"></a>
        </div>
      </div>

      <div id="mobileMenu" class="mobileMenu">
        <i id="closeMenu" class="closeIcon">X</i>
        <div class="nav2">
          <a href="MostraLibriServlet?id=1" class="menuItem2">
            <button class="forButton3">Libri</button>
            <span  class="Arrow material-symbols-outlined">arrow_forward_ios</span>
          </a>
          <a href="MostraLibriServlet?id=2" class="menuItem2">
            <button class="forButton3">Bambini e Ragazzi</button>
            <span class="Arrow material-symbols-outlined">arrow_forward_ios</span>
          </a>
          <a href="MostraLibriServlet?id=3" class="menuItem2">
            <button class="forButton3">Fumetti e manga</button>
            <span class="Arrow material-symbols-outlined">arrow_forward_ios</span>
          </a>
          <a href="MostraLibriServlet?id=4" class="menuItem2">
            <button class="forButton3">Libri Vintage</button>
            <span class="Arrow material-symbols-outlined">arrow_forward_ios</span>
          </a>
          <a href="MostraLibriServlet?id=5" class="menuItem2">
            <button class="forButton3">E-Book</button>
            <span class="Arrow material-symbols-outlined">arrow_forward_ios</span>
          </a>
          <a href="MostraLibriServlet?id=6" class="menuItem2">
            <button class="forButton3">Libri in Inglese</button>
            <span class="Arrow material-symbols-outlined">arrow_forward_ios</span>
          </a>
        </div>
        <div class="HeaderQuick3">
          <div>
            <a href=""><img style="width: 35px; height: 35px" src="CSS/ShopBag2.svg"></a>
            <a href="">Carrello(<span id="num_prod2"><%=nprod%></span>)</a>
          </div>
          <div>
            <%
              if(mode == 2 || mode == 1){%>
            <a href="LogOutServlet"><img style="width: 35px; height:  35px;" src="CSS/Account.svg"></a>
            <a href="Registrazione.jsp">Log-Out</a>
            <%}else{%><a style="text-decoration: none" href="Login.jsp"><img style="width: 35px; height:  35px;" src="CSS/Account.svg"></a>
            <a href="Registrazione.jsp">Log-in/Sign-Up</a><%}%>
          </div>
          <div>
            <a href="MostraPreferitiServlet"><img style="width: 35px; height: 40px" src="CSS/Heart3.svg"></a>
            <a href="MostraPreferitiServlet">Preferiti</a>
          </div>
        </div>
        <div class="bluBan"></div>
      </div>

    </div>

  </div>
</header>
<div class="libri">
  <table>
    <tr>
      <% List<LibroElettronico> elettronicos = (List<LibroElettronico>) request.getAttribute("libriE");
        List<LibroCartaceo> cartaceos = (List<LibroCartaceo>) request.getAttribute("libri");
        List<String> titoli= new ArrayList<>();
        int i = 0;
        if(cartaceos != null)
          for (LibroCartaceo c: cartaceos) {
            String codice = c.getCodice();
            String titolo = c.getTitolo();
            float prezzo = c.getPrezzo();
            titoli.add(titolo);
            if(i == 3) { %>
    </tr>
    <tr>
      <%
          i = 0; }
        i++;
      %>
      <td>
    <span class="libro">
        <%=titolo%>
        <%=" "%>
        <%=prezzo + "€"%>
        <button class="add" value="<%=codice%>">Aggiungi al carrello</button>
        <%
          if(codiciPref != null && codiciPref.contains(codice)){
        %>
        <button class="pref" value="<%=codice%>">Rimuovi dai preferiti</button>
        <%
        }
        else{
        %>
        <button class="pref" value="<%=codice%>">Aggiungi ai preferiti</button>
        <%
          }
        %>
    </span>
      </td>
      <%
          }
      %>
    </tr>
    <%
      i = 0;
      if(elettronicos != null)
        for (LibroElettronico c: elettronicos) {
          String codice = c.getCodice();
          String titolo = c.getTitolo();
          float prezzo = c.getPrezzo();
          if (titoli.contains(c.getTitolo())) {
          }
          else{
            if(i == 3) { %>
    </tr>
    <tr>
        <%
        i = 0;}
            i++;
    %>
      <td>
    <span class="libro">
        <%=titolo%>
        <%=" "%>
        <%=prezzo + "€"%>
        <button class="add" value="<%=codice%>">Aggiungi al carrello</button>
        <%
          if(codiciPref != null && codiciPref.contains(codice)){
        %>
        <button class="pref" value="<%=codice%>">Rimuovi dai preferiti</button>
        <%
        }
        else{
        %>
        <button class="pref" value="<%=codice%>">Aggiungi ai preferiti</button>
        <%
          }
        %>
    </span>
      </td>
        <%
                }
    }
            if(((elettronicos == null) && (cartaceos == null))){
                %>
      <div class="noLibri" style="align-content: center; color: red; font-size: large">
        Nessun libro trovato.
      </div>
        <% }else {
              boolean cvuoto = false;
              boolean evuoto = false;
              if (elettronicos != null && cartaceos == null) {
                if(elettronicos.isEmpty()) {
                  evuoto = true;
                  cvuoto = true;
                }
              }else if(elettronicos == null && cartaceos != null){
                if(cartaceos.isEmpty()){
                  cvuoto = true;
                  evuoto = true;
                }
              }
              else if(elettronicos != null && cartaceos != null){
                if(cartaceos.isEmpty() && elettronicos.isEmpty()){
                  cvuoto = true;
                  evuoto = true;
                }
              }
              if((cvuoto) && (evuoto)){
                %>
            <div class="noLibri" style="align-content: center; color: red; font-size: large">
            Nessun libro trovato.
            </div>
            <%}
            }
          %>
          <div class="noLibri" id="ajaxRemove" style="align-content: center; color: red; font-size: large"></div>
  </table>

</div>
</body>
<script>
  $(".add").click(function addCart() {
    let codice = $(this).val();
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (this.status == 200 && this.readyState == 4) {
        let s = this.responseText;
        if(s === "-2"){
          alert("Quantità non disponibile")
        }
        else {
          document.getElementById("num_prod").innerHTML = s;
          document.getElementById("num_prod2").innerHTML = s;

        }
      }
    }
    xhttp.open("POST", "carrelloservlet");
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("codice=" + codice);
  })
</script>
<script>
  let hamburger = document.querySelector(".HambIcon");
  let closeIcon = document.getElementById("closeMenu");
  let mobileMenu = document.getElementById("mobileMenu");

  hamburger.addEventListener("click", function() {
    mobileMenu.style.transform = 'translateX(0)'; // sposta il menu a destra
  });

  closeIcon.addEventListener("click", function() {
    mobileMenu.style.transform = 'translateX(-100%)'; // sposta il menu a sinistra
  });
</script>
<script>
  $(".pref").click(function addPref() {
    let codice = $(this).val();
    let button = $(this);
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (this.status == 200 && this.readyState == 4) {
        let s = this.responseText;
        if(s === "1"){
          button.html("Rimuovi dai preferiti");
        }
        else if (s === "-1"){
          button.parent().hide();
        }
        else if (s === "-1-2"){
          button.parent().hide();
          $("#ajaxRemove").html("Nessun libro trovato");
        }
      }
    }
    xhttp.open("POST", "preferito-servlet");
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("codice=" + codice);
  })
</script>
</html>
