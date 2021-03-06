<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
           <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="resources/css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="outerregisterBox">
	<div id="registerBox">

		<form:form id="editForm" modelAttribute="user" action="/edit_profile"
			method="post">
			<table class="space">
				<tr>
					<td><form:label path="username">Username</form:label>
					<form:input path="username" name="username" id="username"  class="form-control"/>
					</td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:label path="password">Password</form:label>
					<form:password path="password" name="password"
							id="password"  class="form-control"/></td>
				</tr>
				
				<tr>
					<td><form:label path="firstname">FirstName</form:label>
					<form:input path="firstname" name="firstname"
							id="firstname"  class="form-control"/></td>
			
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:label path="lastname">LastName</form:label>
					<form:input path="lastname" name="lastname" id="lastname"  class="form-control"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="dob">Birth Date </form:label>
					<form:input path="dob" name="dob" id="dob"  class="form-control"/></td>
				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:label path="sex">Gender</form:label>
					<form:input path="sex" name="sex" id="sex"  class="form-control"/></td>
				</tr>
				<tr>
					<td><form:label path="address.email">Email</form:label>
					<form:input path="address.email" name="email" id="email"  class="form-control" />
					</td>
				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:label path="address.city">City</form:label>
					<form:input path="address.city" name="city" id="city"  class="form-control"/></td>
				</tr>
				<tr >
					<td><form:label path="address.state">State</form:label>
					<form:input path="address.state" name="state" id="state"  class="form-control"/>
					</td>
				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:label path="address.country">Country</form:label>
					<form:input path="address.country" name="country"
							id="country"  class="form-control"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:label path="address.phone">Phone</form:label>
					<form:input path="address.phone" name="phone" id="phone"  class="form-control"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/></td>
					<td><form:button id="register" name="register" class="btn btn-info btn-md">Save Changes</form:button>
					</td>
				</tr>
				<tr></tr>
			</table>
			
		</form:form>
		<table >
			<tr>
				<td style="font-style: italic; color: red;">${editmessage}</td>
			</tr>
		</table>
</div>
	</div>
        
        
	<a href="/home" >home</a>
</body>
</html>