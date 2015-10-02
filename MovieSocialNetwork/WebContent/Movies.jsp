<%@page import="sun.net.www.http.PosterOutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.jose.orm.dao.*, edu.jose.orm.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All movies</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<% 
	MovieDAO movieDAO = new MovieDAO();
	
	String action = request.getParameter("action");
	
	String title = request.getParameter("title");
	String plot = request.getParameter("plot");
	String urlPoster = request.getParameter("urlPoster");
	String imdbId = request.getParameter("imdbId");
	String idStr = request.getParameter("id");
	
	System.out.println("*** action: " + action + ", idStr: " + idStr + ", title: " + title);
	
	if("create".equals(action)) {
		Movie movie = new Movie(title, plot, urlPoster, imdbId);
		movieDAO.createMovie(movie);		
	} 
	else if ("delete".equals(action)) {
		Integer id = Integer.parseInt(idStr);
		movieDAO.deleteMovie(id);
	}
	
	List<Movie> movies = movieDAO.readAllMovies();
	%>
	<strong>
		Movies
	</strong>
	<form action="">
	<table class="table table-striped">
	<tr>
		<th>Title</th>
		<th>Plot</th>
		<th>UrlPoster</th>
		<th>IMDB Movie ID</th>
		<th>&nbsp;</th>
	</tr>
	
	<tr>
		<td><input name="title" class="form-control"></td>
		<td><input name="plot" class="form-control"></td>
		<td><input name="urlPoster" class="form-control"></td>
		<td><input name="imdbId" class="form-control"></td>
		<td>
			<button class="btn btn-primary" type="submit" name="action" value="create">Create</button>
		</td>
	</tr>
	
	<%
	for(Movie m : movies) {
		%>
		<tr>
		<td><a href="MovieDetails.jsp?id=<%= m.getId()%>"><%= m.getTitle() %></a></td>
		<td><%= m.getPlot() %></td>
		<td><%= m.getUrlPoster() %></td>
		<td>imdbID=<%= m.getImdbId() %></td>
		
		<td><a class="btn btn-danger" href="Movies.jsp?action=delete&id=<%= m.getId() %>">Delete</a></td>
		</tr>
		<%
	}
	%>

	</table>
	</form>
</div>
</body>
</html>