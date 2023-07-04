<%@ page import="Model.Bean.Carrello" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/styles.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <title>E-CommerceBook</title>

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
    int mode = (int) request.getSession().getAttribute("mode");
%>
<header class="header">

    <div class="headerDivTop">
        <!--  Comments      -->
        <div class="movein">
            <a href="#" class="writec ok2">HOME</a>
            <a href="#" class="writec ok2">FAQ & INFO</a>
            <a href="#" class="writec ok2">CONVENZIONI</a>
        </div>
    </div>

    <div class="HeaderfirstDiv">

        <div class="SonFirstDiv">

            <div class="homeElement">
                <a href="homepage.jsp">
                    <lord-icon
                            class="interEl"
                            src="https://cdn.lordicon.com/kipaqhoz.json"
                            trigger="morph"
                            colors="primary:royalblue"
                            style="width:60px;height:60px">
                    </lord-icon>
                </a>
            </div>


            <form class="formSearch" action="/search">
                <input class="searchpanel" type="text" name="search" >
            </form>


            <div class="HeaderQuick">
                <a class="noDec" href="#"><button class="forButton">PREFERITI</button></a>
                <%
                    if(mode == 2 || mode == 1){%>
                        <a class="noDec" href="LogOutServlet"><button class="forButton">LOG-OUT</button></a>
                    <%}else{%><a class="noDec" href="Login.jsp"><button class="forButton">LOG-IN</button></a><%}%>
                <a class="noDec" href="#"><button class="forButton">CARRELLO(<%=nprod%>)</button></a>
            </div>

            <div class="hamburger" id="openBr">
                <div class="HambIcon"> <!-- come se fosse <i> -->
                    <span class="bar1"></span>
                    <span class="bar2"></span>
                    <span class="bar3"></span>
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
                <div class="HeaderQuick2">
                    <a class="noDec2" href="#"><button class="forButton2">CARRELLO(<%=nprod%>)</button></a>
                    <%
                        if(mode == 2 || mode == 1){%>
                            <a class="noDec2" href="LogOutServlet"><button class="forButton2">LOG-OUT</button></a>
                                <%}else{%><a class="noDec2" href="Login.jsp"><button class="forButton2">LOG-IN</button></a><%}%>
                    <a class="noDec2" href="#"><button class="forButton2">PREFERITI</button></a>
                </div>
            </div>

        </div>


        <nav>
            <ul class="nav">
                <li><a href="MostraLibriServlet?id=1" class="menuItem">Libri</a></li>
                <li><a href="MostraLibriServlet?id=2" class="menuItem">Bambini e Ragazzi</a></li>
                <li><a href="MostraLibriServlet?id=3" class="menuItem">Fumetti e manga</a></li>
                <li><a href="MostraLibriServlet?id=4" class="menuItem">Libri Vintage</a></li>
                <li><a href="MostraLibriServlet?id=5" class="menuItem">E-Book</a></li>
                <li><a href="MostraLibriServlet?id=6" class="menuItem">Libri in Inglese</a></li>
            </ul>
        </nav>

    </div>
</header>
<div class="Slider">
    <figure class="figure">
        <div class="slide">
            <img id="slide1" src="CSS/BookWallp.png" alt="BOOKSS">
            <p class="slide-text1">I MIGLIORI LIBRI</p>
            <p class="slide-text2">Del PANORAMA Artistico</p>
        </div>
        <div class="slide">
            <img id="slide2" src="CSS/BookWallp.png" alt="BOOKSS">
            <p class="slide-text">Testo per la seconda immagine</p>
        </div>
        <div class="slide">
            <img id="slide3" src="CSS/BookWallp.png" alt="BOOKSS">
            <p class="slide-text">Testo per la terza immagine</p>
        </div>
    </figure>
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

</html>