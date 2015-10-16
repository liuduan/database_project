<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<script type="text/javascript" src="/database_project/scripts/js/deletePost.js"></script>
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

		<h1>All Projects</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Code</th>
					<th>Name</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="project" items="${projects}">
				<tr>
					<td>${project.id}</td>
					<td>${project.code}</td>
					<td>${project.name}</td>
					<td>
						<spring:url value="/projects/select/${project.id}" var="selectUrl"/>
						<form method="GET" action="${selectUrl}">
						 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="hidden" name="todoaction" value="${todoaction}" />
							<input type="submit" value="Select"	class="btn btn-primary">
						</form>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>