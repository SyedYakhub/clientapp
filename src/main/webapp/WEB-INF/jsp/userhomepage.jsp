<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Homepage</title>
</head>
<body>
   <h1>Welcome ${user.username}</h1>
   
   <br/><br/>
   
   <h1>List of past entries</h1>
   
   <br/><br/>
   
   <form action="./addentry" method="post">
    <button type="submit">Add Entry</button>
   </form>
   
   <br/><br/>
   
   <table border="1">
   
       <tr>
         <th>Date</th>     
         <th colspan="3">Actions</th>
       </tr> 
       
       <c:if test="${entrieslist.size()==0}">
       <tr><td style="font-size:18px;color:green;text-align:center" colspan="4">User didnot added any diary entries till now</td></tr>
       </c:if>
       
       <c:forEach items="${entrieslist}" var="e">
       
          <tr>
          
          <td> 
          <fmt:formatDate value="${e.entrydate}" pattern="dd/MM/yyyy"/>
          </td>
          
          <td> <a href="./displayentry?id=${e.id}">View</a> </td>
          <td> <a href="./updateentry?id=${e.id}">Update</a> </td>
          <td> <a href="./deleteentry?id=${e.id}">Delete</a> </td>
          
          </tr>
          
       </c:forEach>
          
   </table>
   
   <br/><br/>
    <p><a href="./logout">Signout</a></p>
</body>
</html>