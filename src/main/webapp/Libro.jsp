<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.*" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: deeecaaa
  Date: 11/07/23
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="JavaScript/jquery-3.7.0.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/styles.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="CSS/ShowBook.css">
    <title>E-CommerceBook</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
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
    int mode = (int) request.getSession().getAttribute("mode");
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
                    if(mode == 2){%>
                <a style="text-decoration: none" href="profilo.jsp"><button class="forButton">PROFILO</button></a>
                <%}else if(mode == 1) {%><a style="text-decoration: none" href="admin.jsp"><button class="forButton">ADMIN</button></a>
                <%} else{%><a style="text-decoration: none" href="Login.jsp"><button class="forButton">LOG-IN</button></a><%}%>
                <a style="text-decoration: none" href="MostraCarrelloServlet"><button class="forButton">CARRELLO(<span id="num_prod"><%=nprod%></span>)</button></a>
            </div>

            <div class="HeaderQuick2">
                <div><a href="MostraCarrelloServlet"><img src="CSS/ShopBag2.svg"></a></div>
                <%
                    if(mode == 2){%>
                <div><a href="profilo.jsp"><img src="CSS/Account.svg"></a></div>
                <%}else if(mode == 1) {%><div><a href="admin.jsp"><img src="CSS/Account.svg"></a></div>
                <%} else{%><div><a href="Login.jsp"><img src="CSS/Account.svg"></a></div><%}%>
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
                        <a href="MostraCarrelloServlet"><img style="width: 35px; height: 35px" src="CSS/ShopBag2.svg"></a>
                        <a href="MostraCarrelloServlet">Carrello(<span id="num_prod2"><%=nprod%></span>)</a>
                    </div>
                    <div>
                        <%
                            if(mode == 2){%>
                        <a style="text-decoration: none" href="profilo.jsp"><img style="width: 35px; height:  35px;" src="CSS/Account.svg"></a>
                        <a href="profilo.jsp">Profilo</a>
                        <%}else if(mode == 1) {%><a style="text-decoration: none" href="admin.jsp"><img style="width: 35px; height:  35px;" src="CSS/Account.svg"></a>
                        <a href="admin.jsp">Admin</a>
                        <%} else{%><a style="text-decoration: none" href="Login.jsp"><img style="width: 35px; height:  35px;" src="CSS/Account.svg"></a>
                        <a href="Login.jsp">Log-In/Sign-in</a><%}%>
                    </div>
                    <div>
                        <a href="MostraPreferitiServlet"><img style="width: 35px; height: 40px" src="CSS/Heart3.svg"></a>
                        <a href="MostraPreferitiServlet">Preferiti</a>
                    </div>
                </div>
            </div>

        </div>

    </div>
</header>

<% String tipo = (String) request.getAttribute("tipo");
    String visualize = (String) request.getAttribute("visualize");
    String pref = "false";
    String p = (String) request.getAttribute("pref");
    if(p != null){
        pref = p;
    }
    DecimalFormat df = new DecimalFormat("#.00");
    if(tipo != null){
        LibroCartaceo libro = new LibroCartaceo();
        LibroElettronico libroE = new LibroElettronico();
        String codice = "";
        String titolo = "";
        float prezzo = 0;
        String autore = "";
        String descrizione = "";
        int quantDisp = 0;
        String formato = "";
        if(tipo.equals("bi-tipo") && visualize.equals("cartaceo")){
            libroE = (LibroElettronico) request.getAttribute("libroE");
            libro = (LibroCartaceo) request.getAttribute("libro");
            codice = libro.getCodice();
            titolo = libro.getTitolo();
            prezzo = libro.getPrezzo();
            autore = libro.getAutore();
            descrizione = libro.getDescrizione();
            quantDisp = libro.getQuantitaDisp();
        } else if (tipo.equals("bi-tipo") && visualize.equals("elettronico")) {
            libroE = (LibroElettronico) request.getAttribute("libroE");
            libro = (LibroCartaceo) request.getAttribute("libro");
            codice = libroE.getCodice();
            titolo = libroE.getTitolo();
            prezzo = libroE.getPrezzo();
            autore = libroE.getAutore();
            descrizione = libroE.getDescrizione();
            formato = libroE.getFormato();
        } else if(tipo.equals("cartaceo")) {
            libro = (LibroCartaceo) request.getAttribute("libro");
            codice = libro.getCodice();
            titolo = libro.getTitolo();
            prezzo = libro.getPrezzo();
            autore = libro.getAutore();
            descrizione = libro.getDescrizione();
            quantDisp = libro.getQuantitaDisp();
        }else if(tipo.equals("elettronico")){
            libroE = (LibroElettronico) request.getAttribute("libroE");
            codice = libroE.getCodice();
            titolo = libroE.getTitolo();
            prezzo = libroE.getPrezzo();
            autore = libroE.getAutore();
            descrizione = libroE.getDescrizione();
            formato = libroE.getFormato();
        }
     %>
<div class="ShowBook">
    <div class="BookImg">
        <div><img src="LibriIMG/<%=codice + ".jpg"%>"></div>
    </div>
    <div class="infoBook">
        <div class="TotalInfo">
            <div><h1><%="Titolo: " + titolo%></h1></div>
            <div><h2><%="Autore: " + autore%></h2></div>
            <div><p><%="Prezzo: " + df.format(prezzo) + "€"%></p></div>
            <%
                if(tipo.equals("cartaceo") || (tipo.equals("bi-tipo") && visualize.equals("cartaceo"))){%>
                    <div><p><%="Quantità: " + quantDisp%></p></div>
                <%}else{%>
                    <div><p><%="Formato: " + formato%></p></div>
                <%}
            %>
        </div>
        <div class="Buttons">
            <div class="CartOrPref">
                <%
                    if(visualize.equals("cartaceo")){%>
                        <%
                            if(pref.equals("true")){%>
                                <button id="addPref" value="<%=libro.getCodice()%>"><img src="CSS/FullHeart.svg"></button>
                            <%}else{%>
                                <button id="addPref" value="<%=libro.getCodice()%>"><img src="CSS/Heart3.svg"></button>
                            <%}
                        %>
                    <%} else{%>
                        <%
                            if(pref.equals("true")){%>
                                <button id="addPref" value="<%=libroE.getCodice()%>"><img src="CSS/FullHeart.svg"></button>
                            <%}else{%>
                                <button id="addPref" value="<%=libroE.getCodice()%>"><img src="CSS/Heart3.svg"></button>
                            <%}
                        %>
                    <%}
                    if(visualize.equals("cartaceo")){%>
                        <button id="addCart" value="<%=libro.getCodice()%>"><img src="CSS/ShopBag2.svg"></button>
                    <%} else{%>
                        <button id="addCart" value="<%=libroE.getCodice()%>"><img src="CSS/ShopBag2.svg"></button>
                    <%}
                %>
            </div>
            <div class="ElCa">
                <%
                    if(tipo.equals("bi-tipo")){%>
                        <a href="LibriServlet?codice=<%=libro.getCodice()%>"><button><p>Cartaceo</p></button></a>
                        <a href="LibriServlet?codice=<%=libroE.getCodice()%>"><button><p>Elettronico</p></button></a>
                    <%}
                %>
            </div>
        </div>

    </div>
    <div class="Description">
        <div><h2>Descrizione</h2></div>
        <div><p><%=descrizione%></p></div>
    </div>
</div>

<% } %>
<div class="finalInfo">
    <div class="Icone">
        <div>
            <img style="width: 30px; height: 30px" src="CSS/FaceBookImg.svg" alt="Checkk">
            <p>BookShopPage</p>
        </div>
        <div>
            <img style="width: 30px; height: 30px" src="CSS/InstaImg.svg" alt="Checkk">
            <p>@BookShop</p>
        </div>
        <div>
            <img style="width: 30px; height: 30px" src="CSS/Twitter.svg" alt="Checkk">
            <p>BookShopTW</p>
        </div>
    </div>

</div>
</body>

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
    window.addEventListener('resize', function() {
        if (window.innerWidth > 1180) {
            mobileMenu.style.transform = 'translateX(-100%)'; // sposta il menu a sinistra
        }
    });
</script>

<script>
    $(document).ready(function (){
        $("#addCart").click(function addCart() {
            let codice = $(this).val();
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.status == 200 && this.readyState == 4) {
                    let s = this.responseText;
                    if(s === "-2"){
                        alert("Quantità non disponibile")
                    }
                    else {

                        const array = s.split("-");
                        document.getElementById("num_prod").innerHTML = array[0];
                        document.getElementById("num_prod2").innerHTML = array[0];


                    }
                }
            }
            xhttp.open("POST", "carrelloservlet");
            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhttp.send("codice=" + codice);
        })
        $("#addPref").click(function addPref() {
            let codice = $(this).val();
            let button = $(this);
            const xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.status == 200 && this.readyState == 4) {
                    let s = this.responseText;
                    if(s === "1"){
                        button.html('<img src="CSS/FullHeart.svg">');
                    }
                    else if (s === "-1-2" || s === "-1"){
                        button.html('<img src="CSS/Heart3.svg">');
                    }
                }
            }
            xhttp.open("POST", "preferito-servlet");
            xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhttp.send("codice=" + codice);
        })
    })
</script>
</html>
