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
        
            <div id="view-details">
            
                <h2>VIEW DETAILS HERE</h2>
                
                    <label>Date</label> ${entry.entrydate}<br/><br/>
                    
                    <label>Description</label> ${entry.description}<br/><br/>
                     
                    <input type="hidden" name="userid" value="${user.id}">
                    
                    <form action="./userhome" method="post">
                    
				    <button type="submit">BACK TO HOME</button>
				    
				   </form>
                    
            </div>
            
        </div>
    </main>
</body>
</html>


