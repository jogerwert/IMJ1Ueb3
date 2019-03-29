<%@page import="de.stl.saar.internetentw1.model.Category"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html> 
  <head> 
    <title>Speisekarte</title> 
  </head> 
  <body>  
  	<%
  		LocalDate today = LocalDate.now();
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd.MM.yyyy");
  		String todayAsString = today.format(formatter);
  	%>
    <h1>Mensaplan für <%= todayAsString %></h1> 
    <p>Bitte wählen Sie Ihr Menü</p>
	
	<% 
		for (Category category: Category.values()) {%>
			<h3><%= category.toString() %></h3>     
		     <table>
		     	<tr>
				    <c:forEach items="${dishes}" var="dish">
				     
				     	<td>
				     		<img src="images/${dish.imageName}.jpg" style="width:5cm" />
				     	</td>
				     	<td>
				     		${dish.dishName} <br />
				     		${dish.price} Euro <br />
				     		${dish.category.categoryName} <br />
				     		${category.categoryName}
				     	</td>
			     	</c:forEach>
		     	<tr>
		  	 </table>
	<%}%>
	
  </body> 
</html> 