<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
    <title>MyDiary App</title>
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <h1 id="app-title">MyDiary App</h1>
    </header>
    <main>
        <div id="content">
            <div id="registration-form">
                <h2>Register Here</h2>
                <form action="./registersuccess" method="post">
                
                    <label for="username">Username:</label><br>
                    <input type="text" id="username" name="username"><br>
                    
                    <label for="password">Password:</label><br>
                    <input type="password" id="password" name="password"><br>
                    
                    <input type="submit" value="Register">
                </form>
                <p><a href="./login">Existing User? Login Here</a></p>
            </div>
        </div>
    </main>
</body>
</html>


