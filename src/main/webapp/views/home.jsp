<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="resources/css/style.css" rel="stylesheet" />

</head>
<body>
<jsp:include page="header.jsp"/>

<jsp:useBean id="today" class="java.util.Date" scope="page" />
<%-- <fmt:formatDate value="${today}" pattern="MM.dd.yyyy" /> --%>

	<div class="left">
		<p class="effect">Welcome: ${currentUser.firstname} </p>
		<hr>
		<a href="/edit_profile" >Edit Profile</a>
		<hr>
		<a href="/settings" >Account Setting</a>
		<hr>
	</div>
	<div class="middle">
	<div>
		<h4>Blogger Name: ${currentUser.firstname}</h4>
		<h4>Current date: ${today} </h4>
	<hr>
	</div>
		<div class="newPostContainer">
		
			<form action="/app/post/addpost" method="post">
				<p class="effect">Post Title:</p>
				<input type="text" name="title" class="form-control"/>
				<input type="hidden" name="userid" value="${currentUser.id}" />
				
				<p class="effect">Post Content:</p>
				<textarea class="form-control" name="content" rows="6" id="postContainer"></textarea>
				<div class="position"><input type="submit" value="post" class="btn btn-info btn-sm"/></div>
			<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>

		</div>
		
	
		<div class="listPostContainer">


			<c:forEach var="postp" items="${posts}">
			<hr>
				<div class="postContainer">
					<span class="effect">
					<a href="profile/un/${postp.post.owner.username}">
						${postp.post.owner.username}
					</a>
					</span>
					<span class="effect">Post title:</span> ${postp.post.title} 
					<span class="effect">Post created date:</span> ${postp.post.createdDate} 
					<hr>
						${postp.post.content} 
					<hr>
				
<!-- 


 -->
					<c:if test="${postp.post.owner.id eq currentUser.id}">
						<form action="deletePost" method="post">
							<input type="hidden" name="userid" value="${currentUser.id}" /> 
							<input type="hidden" name="postid" value="${postp.post.id}" />
						
							<div class="position"><input id="btnp${postp.post.id}" onclick="myEditPost(${postp.post.id})" type="button" value="edit" class="btn btn-info btn-sm" />
							<input type="submit" value="delete" class="btn btn-info btn-sm"/></div>
							<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>


							<div id="post${postp.post.id}" class="postEditClass" style="display: none;" >
							<form   action="updatePost" method="post">
								<input type="hidden" name="userid" value="${currentUser.id}" /> <input
									type="hidden" name="postid" value="${postp.post.id}" /> 		
									<p class="effect">Post Content:</p>
									<textarea name="content" rows="4" id="postContainer"  class="form-control" >${postp.post.content}</textarea>
									
									<div class="position"><input 	type="submit" value="update" class="btn btn-info btn-sm"/></div>				
									<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
									
							</form>
							</div>


					</c:if>

					<c:if test="${postp.status eq true}">

						<form action="unLikePost" method="post">
							<input type="hidden" name="userid" value="${currentUser.id}" /> <input
								type="hidden" name="postid" value="${postp.post.id}" />
								<input id="unlike" type="submit" value="UnLike" class="btn btn-info btn-sm"/>
								<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
								 : ${ postp.post.likesize()} 
						</form>

					</c:if>

					<c:if test="${postp.status eq false}">
					
						<form action="likePost" method="post">
							<input type="hidden" name="userid" value="${currentUser.id}" /> <input
								type="hidden" name="postid" value="${postp.post.id}" /> 
								
								<input type="submit" id="likebutton" value="Like"  class="btn btn-info btn-sm"/>
								<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
							:${ postp.post.likesize()} 
						</form>

					</c:if>

				
					<hr><p class="effect">Comments:</p><hr>
					<c:forEach var="comment" items="${postp.post.comments}">
						<div class="commentcontainer">
					
							<span class="effect">
							<a href="profile/un/${comment.owner.username} ">
								${comment.owner.username} 
							</a>
							
							</span> : ${comment.content}
					
						<c:if test="${comment.owner.id eq currentUser.id}"> 
							
							<form action="deleteComment" method="post">
								<input type="hidden" name="userid" value="${currentUser.id}" /> 
								<input type="hidden" name="commentid" value="${comment.id}" /> 
									
								<div class="position"><input id="btnc${comment.id}" onclick="myEditComment(${comment.id})" type="button" value="edit" class="btn btn-info btn-sm"/>
								<input type="submit" value="delete" class="btn btn-info btn-sm"/></div>
								<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
							</form>
							
							<div id="comment${comment.id}" class="commentEditClass" style="display: none;" >
							<form   action="updateComment" method="post">
								<input type="hidden" name="userid" value="${currentUser.id}" /> 
								<input type="hidden" name="commentid" value="${comment.id}" /> 
	
									<textarea name="content" rows="2" id="comment" class="form-control">${comment.content}</textarea>
									<div class="position"><input 	type="submit" value="update" class="btn btn-info btn-sm"/></div>
				
									<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
									
									
							</form>
							</div>
							
							
						</c:if>

					</div>
					</c:forEach>
					<!--  -->
					<form action="/addcomment" method="post">
						<textarea class="form-control" name="content" cols="60" rows="2" id="comment"></textarea>
					
						<input type="hidden" name="userId" value="${currentUser.id}" /> 
						<input type="hidden" name="postId" value="${postp.post.id}" /> 
						
							<div class="position"><input type="submit" value="comment" class="btn btn-info btn-sm"/></div>
							<input type="hidden" name= "${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>

				</div>

			</c:forEach>
		</div>


	</div>
	<div class="right">right
	
	 <a style="color: red" href="logout">Logout</a>
	<hr>
	</div>

<script src="resources/js/script.js" type="text/javascript" ></script>

</body>

</html>


