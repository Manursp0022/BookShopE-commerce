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
    <div class="form signup">
        <h2>Registrazione</h2>
        <div class="inputBox">
            <input type="text" id="username" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Username</span>
        </div>
        <div class="inputBox">
            <input type="text" id="email" required="required">
            <ion-icon name="mail-outline"></ion-icon>
            <span>Indirizzo E-Mail</span>
        </div>
        <div class="inputBox">
            <input type="password" id="password" required="required">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <span>Inserisci Password</span>
        </div>
        <div class="inputBox">
            <input type="password" id="passwordrep" required="required">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <span>Conferma Password</span>
        </div>
        <div class="inputBox">
            <input type="text" id="via" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Via</span>
        </div>
        <div class="inputBox">
            <input type="text" id="citta" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Città</span>
        </div>
        <div class="inputBox">
            <input type="text" id="CAP" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>CAP</span>
        </div>
        <div class="inputBox">
            <input type="text" id="telefono" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Telefono</span>
        </div>
        <div class="inputBox">
            <input type="submit"  value="Create Account">
        </div>
        <p>Sei già registrato? <a href="Login.jsp" class="login">Accesso</a></p>
    </div>
    <div class="form signin">

    </div>
</div>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
