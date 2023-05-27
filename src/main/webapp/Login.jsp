<%--
  Created by IntelliJ IDEA.
  User: deeecaaa
  Date: 27/05/23
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Accesso</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="logPage.css">
</head>
<body>
<body>
<div class="container">
  <div class="form signup">
    <h2>Accesso</h2>
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
      <input type="submit"  value="Create Account">
    </div>
    <p>Non sei ancora registrato? <a href="Registrazione.jsp" class="login">Registrati</a></p>
  </div>
  <div class="form signin">

  </div>
</div>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>