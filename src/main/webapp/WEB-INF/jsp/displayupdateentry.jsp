<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
    <title>MyDiary App</title>
</head>
<body>
    <header>
        <h1 id="app-title">MyDiary App</h1>
        
        Welcome ${user.username}
        
    </header>
    <main>
        <div id="content">
            <div id="displayupdate-form">
                <h2>Update Details Here</h2>
                <form action="./processentryupdate" method="post">
                
                    <label>Date</label><br>
                    <input type="text" name="entrydate" value="<fmt:formatDate value="${entry.entrydate}" pattern="yyyy-MM-dd"/>" readonly><br>
                    
                    <label>Description</label><br>
                    <textarea rows="10" cols="30" name="description">${entry.description}</textarea><br>
                  
                    
                    <input type="hidden" name="userid" value="${user.id}">
                    <input type="hidden" name="id" value="${entry.id}">
                    
                    <input type="submit" value="Update Entry">
                </form>
            </div>
        </div>
    </main>
</body>
</html>


