<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
            <div id="entry-form">
                <h2>Add Entry Here</h2>
                <form action="./saveentry" method="post">
                
                    <label>Date</label><br>
                    <input type="date" name="entrydate"><br>
                    
                    <label>Description</label><br>
                    <textarea rows="10" cols="30" name="description"></textarea><br>
                    
                    <input type="hidden" name="userid" value="${user.id}">
                    
                    <input type="submit" value="Save Entry">
                </form>
            </div>
        </div>
    </main>
</body>
</html>


