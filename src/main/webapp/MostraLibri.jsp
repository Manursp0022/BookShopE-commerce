<%@ page import="Model.Bean.LibroElettronico" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.LibroCartaceo" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Model.Bean.Carrello" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/styles.css">
    <link rel="stylesheet" href="CSS/visLibri.css">
    <title>E-CommerceBook</title>
    <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
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
                <input class="searchpanel" type="text" name="search" placeholder="Cerca tra milioni di prodotti.... " >
            </form>


            <div class="HeaderQuick">
                <a class="noDec" href="#"><button class="forButton">PREFERITI</button></a>
                <a class="noDec" href="Registrazione.jsp"><button class="forButton">LOG-IN</button></a>
                <a class="noDec" href="#"><button class="forButton">CARRELLO(<%=nprod%>)</button></a>
            </div>

            <div class="hamburger" id="openBr">
                <div class="HambIcon">
                    <span class="bar1"></span>
                    <span class="bar2"></span>
                    <span class="bar3"></span>
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
        <button class="add" onclick="addtocart(<%=codice%>)">Aggiungi al carrello</button>
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
        <button class="add" onclick="addtocart(<%=codice%>)">Aggiungi al carrello</button>
    </span>
        </td>


    <%
                }
    }
    %>

    </table>

</div>
</body>
<script>
    function addtocart(codice) {
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.status == 200 && this.readyState == 4) {
                let s = this.responseText;
                if (s === "-1") {
                    alert("Utente non loggato. Impossibile aggiungere al carrello.")
                } else {
                    <% nprod++ ;%>
                }
            }
        }
        xhttp.open("GET", "carrelloservlet?codice=codice");
        xhttp.send();
    }
</script>
</html>
