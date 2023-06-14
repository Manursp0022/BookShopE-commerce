<%--
  Created by IntelliJ IDEA.
  User: emanuelerosapepe
  Date: 15/05/23
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="logPage.css">
</head>
<body>
<body>
<div class="container">
        <form class="form signup" name="reg" method="post" action="registrazione-servlet">
        <h2>Registrazione</h2>
        <div class="inputBox">
            <input type="text" name="username" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Username</span>
        </div>
        <div class="inputBox">
            <input type="email" name="email" required="required" >
            <ion-icon name="mail-outline"></ion-icon>
            <span>Indirizzo E-Mail</span>
        </div>
        <div class="inputBox">
            <input type="password" id="password" name="password" required="required">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <span>Inserisci Password</span>
        </div>
        <div class="inputBox">
            <input type="password" id="passwordrep" name="passwordrep" required="required" onblur="registration()">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <span> Conferma Password</span>
        </div>
        <div class="inputBox">
            <input type="text" name="via" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Via</span>
        </div>
        <div class="inputBox">
            <input type="text" name="citta" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Città</span>
        </div>
        <div class="inputBox">
            <input type="text" name="CAP" required="required" maxlength="5" pattern="[0-9]">
            <ion-icon name="person-outline"></ion-icon>
            <span>CAP</span>
        </div>
        <div class="inputBox">
            <input type="tel" name="telefono" required="required" pattern="[0-9]{10}">
            <ion-icon name="person-outline"></ion-icon>
            <span>Telefono</span>
        </div>
        <div class="inputBox">
            <input type="submit"  id="bottone" value="Create Account">
        </div>
        <p>Sei già registrato? <a href="Login.jsp" class="login">Accesso</a></p>
            <label for="passwordrep" id="confirm" style="color:red; display: none">
                Le password non corrispondono.
            </label>
        </form>
</div>

</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script>

    function registration(){
    let button = document.getElementById("bottone");
    let passrep = document.forms["reg"]["passwordrep"].value;
    let pass = document.forms["reg"]["password"].value;
    if( pass.localeCompare(passrep) !== 0) {
        document.getElementById("confirm").style.display="block";
        button.disabled = true;
    }
    else{
        document.getElementById("confirm").style.display="none";
        button.disabled = false;
    }
    }
</script>
</body>
</html>