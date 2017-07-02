<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<form:form id="changeEmailForm"	modelAttribute="modifiedUser" action="/change_username" method="post">
		<table align="center">
			<tr>
				<td><form:label for="currentPassword" path="password">Current password: </form:label>
				</td>
				<td><form:password path="password" class="form-control"
						id="password" name="password" /></td>
			</tr>
			<tr>
				<td><form:label path="username">New Username:</form:label></td>
				<td><form:input path="username" name="username"
						class="form-control" id="username" /></td>
			</tr>
			<tr>
				<td></td>
				
				<td align="left"><form:button class="btn btn-default"
						id="apply" name="apply">Apply</form:button></td>
			</tr>
			<tr></tr>
		</table>
		
		
												<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
		
	</form:form>
	<form id="changePasswordForm"
		action="/change_password" method="post">
		<table align="center">
			<tr>
				<td><label for="oldPassword">Current password: </label>
				</td>
				<td><input type="password" class="form-control"
						id="oldPassword" name="currentpassword" /></td>
						<td><form:errors path="password" cssClass="error" />
			</tr>
			<tr>
				<td><label>New Password:</label></td>
				<td><input type="password"  class="form-control"
						id="password" name="newPassword" /></td>
						<td><form:errors path="password" cssClass="error" />
			</tr>
			<tr>
				<td><label>Repeat Password:</label>
				</td>
				<td><input type="password"  class="form-control"
						id="repeatPassword" name="repeatPassword"/></td>
						<td><form:errors path="password" cssClass="error" />
			</tr>
			<tr>
				<td></td>
				
				<td align="left"><button class="btn btn-default"
						id="applyPass" name="applyPass">Apply</button></td>
			</tr>
			<tr></tr>
		</table>
								<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
		
	</form>

	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>
		<a href="/home" >home</a>
</body>
</html>