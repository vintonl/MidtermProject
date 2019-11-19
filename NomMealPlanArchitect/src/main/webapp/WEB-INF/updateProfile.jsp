<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en">
<head>
<jsp:include page="navbar.jsp" />

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>Update Profile: Nom! Meal Plan Architect</title>

<!-- CSS -->
<link href="<c:url value="/CSS/main.css" />" rel="stylesheet">

</head>
<body>
	<div class="container">

		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h1>Update Profile Page</h1>
				<form:form action="updateUserProfile.do" method="POST"
					modelAttribute="user">
<%-- 					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<form:input class="form-control" path="username"
							placeholder="Username" type="hidden" />
						<form:errors path="username" />
					</div> --%>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<form:input class="form-control" path="firstName"
							placeholder="First Name" type="text" />
						<form:errors path="firstName" />
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<form:input class="form-control" path="lastName"
							placeholder="Last Name" type="text" />
						<form:errors path="lastName" />
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-envelope"></i>
							</span>
						</div>
						<form:input class="form-control" path="email"
							placeholder="Email address" type="email" />
						<form:errors path="email" />
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<form:input class="form-control" path="password"
							placeholder="Create password" type="password" />
						<form:errors path="password" />
					</div>
					<br>
					<div>
						<button type="submit" name="userId" value="${user.id}"
							class="btn btn-outline-light btn-md">Update Item</button>
					</div>
					<br>
				</form:form>

				<form:form action="deactivateUser.do" method="POST"
					modelAttribute="user">
					<div>
						<button type="submit" name="userId" value="${user.id}"
							class="btn btn-outline-danger btn-md">Deactivate Account</button>
					</div>
				</form:form>

				<br /> <a href="index.do" class="badge badge-light">Cancel</a>

				<div class="col-3"></div>
			</div>

		</div>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
</body>
</html>