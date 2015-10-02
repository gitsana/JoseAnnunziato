<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.jose.rest.client.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie Details</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<%
		String idimdb = request.getParameter("idimdb");
		MyApiFilmsClient myApiFilmsClient = new MyApiFilmsClient();
		Movie movie = myApiFilmsClient.findMovieByImdbId(idimdb);
	%>
	<h1><%= movie.getTitle() %></h1>
	<p>
		<img alt="" src="<%= movie.getUrlPoster()%>" style="float: left;margin-right: 10px">
		<%= movie.getPlot() %>
	</p>
</body>
</html>