<html>
<head>
    <title>Login Page</title>
    <script src="/js/myScript.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div>
    <form method="post" action="loginservlet" name="Login" class="login" onsubmit="return isEmpty(Login)">
        <p>
            <label for="login">Login:</label>
            <input type="text" name="login" id="login" placeholder="Example Login" maxlength="20" value=""/>
        </p>

        <p>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Password" maxlength="20" value=""/>
        </p>

        <p class="login-submit">
            <button type="submit" class="login-button">Login</button>
        </p>
    </form>
</div>
</body>
</html>
