<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand/logo -->
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/index.jsp">User</a>

		<c:choose> 
		
			<c:when test="${sessionScope.principal == null}">
				<!-- Links -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/user/join.jsp">회원가입</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/index.jsp">유저리스트</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/user/login.jsp">로그인</a></li>
				</ul>
				
			</c:when>
			
			<c:otherwise>
			
				<!-- Links -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="/testProject/index.jsp">유저리스트</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
				</ul>
			</c:otherwise>
			
		</c:choose>

	</nav>
	<br>