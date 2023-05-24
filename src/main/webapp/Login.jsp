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
    <title>Log-In</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="logPage.css">
</head>
<body>
<body>
<div class="container">
    <div class="form signup">
        <h2>Sign Up</h2>
        <div class="inputBox">
            <input type="text" required="required">
            <ion-icon name="person-outline"></ion-icon>
            <span>Username</span>
        </div>
        <div class="inputBox">
            <input type="text" required="required">
            <ion-icon name="mail-outline"></ion-icon>
            <span>E-mail Address</span>
        </div>
        <div class="inputBox">
            <input type="password" required="required">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <span>createPassword</span>
        </div>
        <div class="inputBox">
            <input type="password" required="required">
            <ion-icon name="lock-closed-outline"></ion-icon>
            <span>ConfirmPassword</span>
        </div>
        <div class="inputBox">
            <input type="submit"  value="Create Account">
        </div>
        <p>Already a member ? <a href="" class="login">Log in</a></p>
    </div>
    <div class="form signin">

    </div>
</div>
</body>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
