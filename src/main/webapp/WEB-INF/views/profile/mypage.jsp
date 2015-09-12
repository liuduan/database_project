<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp" />

<body>
<div class="container">
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>User Profile</h1>
	<br />
	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${profile.firstname}</div>
	</div>
	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${profile.lastname}</div>
	</div>
	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${profile.email}</div>
	</div>
	
	<h1>Project list</h1>
	<br />
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#ID</th>
				<th>Name</th>
				<th>Code</th>
			</tr>
		</thead>

		<c:forEach var="project" items="${projectsCache}">
			<tr>
				<td>${project.id}</td>
				<td>${project.code}</td>
				<td>${project.name}</td>
				<td>
					<spring:url value="/projects/${project.id}" var="projectUrl" />
					<spring:url value="/projects/delete/${project.id}" var="deleteUrl" /> 
					<spring:url value="/projects/update/${project.id}" var="updateUrl" />
					<spring:url value="/projects/select/${project.id}" var="selectUrl" />

					<button class="btn btn-info" onclick="location.href='${projectUrl}'">Query</button>
					<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
					<button class="btn btn-primary" onclick="location.href='${selectUrl}'">Select</button>
					<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<jsp:include page="../fragments/footer.jsp" />
</body>
</html>