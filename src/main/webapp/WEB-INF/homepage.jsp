<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../CSS/styles.css">
    <title>E-CommerceBook</title>
    <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
    <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
</head>
<body>
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
                <a class="noDec" href="#"><button class="forButton">CARRELLO</button></a>
            </div>

        </div>


        <nav>
            <ul class="nav">
                <li><a  href="#" class="menuItem">Libri</a></li>
                <li><a href="#" class="menuItem">Bambini e Ragazzi</a></li>
                <li><a href="#" class="menuItem">Fumetti e manga</a></li>
                <li><a href="#" class="menuItem">Libri Vintage</a></li>
                <li><a href="#" class="menuItem">E-Book</a></li>
                <li><a href="#" class="menuItem">Libri in Inglese</a></li>
            </ul>
        </nav>

    </div>
</header>
</body>
</html>