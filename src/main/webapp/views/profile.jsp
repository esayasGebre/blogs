<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="resources/css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="header.jsp"/>
<a href="/home">home</a>

		<table align="center">
			<tr>
				<td><label >Username</label></td>
				<td><label > ${searchUser.username}</label></td>
				
			</tr>			
			<tr>
				<td><label >Firstname:</label></td>
				<td><label > ${searchUser.firstname}</label></td>
			</tr>
			<tr>
				<td><label >Lastname:</label></td>
				<td><label > ${searchUser.lastname}</label></td>
			</tr>
			<tr>
				<td><label >Date Birth:</label></td>
				<td><label > ${searchUser.dob}</label></td>
			</tr>
			<tr>
				<td><label >Gender:</label></td>
				<td><label > ${searchUser.sex}</label></td>
			</tr>
			<tr>
				<td><label >Email:</label></td>
				<td><label > ${searchUser.address.email}</label></td>
			</tr>
			<tr>
				<td><label >City:</label></td>
				<td><label > ${searchUser.address.city}</label></td>
			</tr>
			<tr>
				<td><label >State:</label></td>
				<td><label > ${searchUser.address.state}</label></td>
			</tr>
			<tr>
				<td><label >Country:</label></td>
				<td><label > ${searchUser.address.country}</label></td>
			</tr>
			<tr>
				<td><label >Phone:</label></td>
				<td><label > ${searchUser.address.phone}</label></td>
			</tr>
		</table>
		
		
</body>
</html>