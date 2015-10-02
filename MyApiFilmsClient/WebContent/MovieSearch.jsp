<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.jose.rest.client.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie Search</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<h1>Movie Search Results</h1>
	<table>
	<%
		MyApiFilmsClient myApiFilmsClient = new MyApiFilmsClient();
		Movie[] movies = myApiFilmsClient.findMovieByTitle("back to the future");
		for(Movie movie : movies) {
			%>
			<tr>
				<td><a href="MovieDetails.jsp?idimdb=<%= movie.getIdIMDB()%>"><%= movie.getTitle() %></a></td>		
				<td><%= movie.getIdIMDB() %></td>
				<td><%= movie.getPlot() %></td>
				<td><img src="<%= movie.getUrlPoster()%>"></td>
			</tr>
			<% 
		}
	%>
	</table>
</body>
</html>