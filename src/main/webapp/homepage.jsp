
<%@ page import="Model.Bean.Carrello" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <script src="JavaScript/jquery-3.7.0.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/styles.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <title>E-CommerceBook</title>
    <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>


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


<!-- Fine Header -->
<div class="Slider">
    <figure class="figure">

        <div class="slide">
            <img id="slide1" src="CSS/BookWallp.png" alt="BOOKSS">
            <h2 class="slide-text1">Immergiti nel mondo dell'Arte</h2>
            <h3 class="slide-text2">Con la nostra selezione di libri</h3>
            <div class="InnerSlide">
                <%if(mode == 3){%>
                <a class="linkButt" href="Registrazione.jsp"><button class="ScopriButt">Scopri</button></a>
                <%}else{%><a class="linkButt" href="MostraLibriServlet?id=7"><button class="ScopriButt">Scopri</button></a><%}%>
            </div>
        </div>

        <div class="slide">
            <img id="slide2" src="CSS/Gialli2.jpeg" alt="BOOKSS">
            <h2 class="slide-text3">Scopri la nostra collezione</h2>
            <h3 class="slide-text4">dei migliori libri Gialli!</h3>
            <div class="InnerSlide">
                <%if(mode == 3){%>
                <a class="linkButt" href="Registrazione.jsp"><button class="ScopriButt">Scopri</button></a>
                <%}else{%><a class="linkButt" href="MostraLibriServlet?id=8"><button class="ScopriButtYell">Scopri</button></a><%}%>
            </div>
        </div>

        <div class="slide Shadow">
            <img id="slide3" src="CSS/EnsglishBooks.jpeg" alt="BOOKSS">
            <h2 class="slide-text5">Immergiti in storie affascinanti</h2>
            <h3 class="slide-text6">Scritte in Lingua Inglese!</h3>
            <div class="InnerSlide2">
                <%if(mode == 3){%>
                <a class="linkButt" href="Registrazione.jsp"><button class="ScopriButt">Scopri</button></a>
                <%}else{%><a class="linkButt" href="MostraLibriServlet?id=6"><button class="ScopriButtYell">Scopri</button></a><%}%>
            </div>
        </div>

    </figure>
    <div class="barContainer">
        <div class="bar">
            <div class="fillBar" id="bar1"></div>
        </div>
        <div class="bar">
            <div class="fillBar" id="bar2"></div>
        </div>
        <div class="bar">
            <div class="fillBar" id="bar3"></div>
        </div>
    </div>
</div>

<section> <!-- Definisce una sezione in un documento. Rappresenta un insieme coerente di contenuti che hanno un tema comune o un flusso logico -->
    <div class="containerTopTen">
        <div class="TitoloClassifica">
            <p>La Top 3 dei Libri più venduti</p>
        </div>
        <div class="content">
            <div  class="card">
                <div class="card-Content">
                    <div class="ButtonRank">
                        <button>1</button>
                    </div>
                    <div class="image">
                        <a href="LibriServlet?codice=9788838945496" style="text-decoration: none"><img src="LibriIMG/9788838945496.jpg " alt=" "></a>
                    </div>
                    <div class="nameBook">
                           <a href="LibriServlet?codice=9788838945496" style="text-decoration: none"> <span class="name">ELP</span> </a>
                        <span class="author">Antonio Manzini</span>
                    </div>
                    <div class="button">
                        <button class="AddPrefers" value="9788838945496"><img style="width: 15px; height: 15px; color: white" src="CSS/Heart3.svg"></button>
                        <button class="AddCart" value="9788838945496"><img style="width: 15px; height: 15px; color: white" src="CSS/ShopBag2.svg"></button>
                    </div>
                </div>
            </div>
            <div  class="card">
                <div class="card-Content">
                    <div class="ButtonRank">
                        <button>2</button>
                    </div>
                    <div class="image">
                        <a href="LibriServlet?codice=9788820007058" style="text-decoration: none"><img src="LibriIMG/9788820007058.jpg " alt=" "></a>
                    </div>
                    <div class="nameBook">
                        <a href="LibriServlet?codice=9788820007058" style="text-decoration: none"><span class="name">IT</span></a>
                        <span class="author">Stephen King</span>
                    </div>
                    <div class="button">
                        <button class="AddPrefers" value="9788820007058"><img style="width: 15px; height: 15px; color: white" src="CSS/Heart3.svg"></button>
                        <button class="AddCart" value="9788820007058"><img style="width: 15px; height: 15px; color: white" src="CSS/ShopBag2.svg"></button>
                    </div>
                </div>
            </div>
            <div  class="card">
                <div class="card-Content">
                    <div class="ButtonRank">
                        <button>3</button>
                    </div>
                    <div class="image">
                        <a href="LibriServlet?codice=2000000114675" style="text-decoration: none"><img src="LibriIMG/2000000114675.jpg " alt=" "></a>
                    </div>
                    <div class="nameBook">
                        <a href="LibriServlet?codice=2000000114675" style="text-decoration: none"><span class="name">Violeta</span></a>
                        <span class="author">Isabel Allende</span>
                    </div>
                    <div class="button">
                        <button class="AddPrefers" value="2000000114675"><img style="width: 15px; height: 15px; color: white" src="CSS/Heart3.svg"></button>
                        <button class="AddCart" value="2000000114675"><img style="width: 15px; height: 15px; color: white" src="CSS/ShopBag2.svg"></button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>

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
    $(".AddPrefers").click(function addPref() {
        let codice = $(this).val();
        let button = $(this);
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.status == 200 && this.readyState == 4) {
                let s = this.responseText;
                if(s === "1"){
                    button.html('<img style="width: 15px; height: 15px; color: white" src="CSS/FullHeart.svg">');
                }
                else if (s === "-1-2" || s === "-1"){
                    button.html('<img style="width: 15px; height: 15px; color: white" src="CSS/Heart3.svg">');
                }
            }
        }
        xhttp.open("POST", "preferito-servlet");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhttp.send("codice=" + codice);
    })
</script>

<script>
    $(".AddCart").click(function addCart() {
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
</script>

</html>