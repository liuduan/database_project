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
						<spring:url value="/projects/${project.id}" var="projectUrl" />
						<spring:url value="/projects/delete/${project.id}" var="deleteUrl" /> 
						<spring:url value="/projects/update/${project.id}" var="updateUrl" />
						<spring:url value="/projects/select/${project.id}" var="selectUrl" />

						<button class="btn btn-info" onclick="location.href='${projectUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-primary" onclick="location.href='${selectUrl}'">Select</button>
						<%-- <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td> --%>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>