<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="/ctvproject/scripts/js/deletePost.js"></script>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th></th>
			<th>Code</th>
			<th>Name</th>
			<th>Start</th>
			<th>End</th>
			<th>Access</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="project" items="${projectListFragment}" varStatus="loop">
		<tr>
			<td>${loop.index + 1}</td>
			<td><img height="80" src="/ctvproject/img/in_vivo.png" alt="example image"></td>
			<td>${project.code}</td>
			<td>${project.name}</td>
			<td class="center">${project.starts}</td>
			<td class="center">${project.ends}</td>
			<td class="center">${project.access.value}</td>
			<td class="center">${project.status.value}</td>
			<td>
				<spring:url value="/projects/${project.id}" var="projectUrl" />
				<spring:url value="/projects/update/${project.id}" var="updateUrl" />
				<spring:url value="/ctvproject/projects/delete/${project.id}" var="deleteUrl" /> 
				<spring:url value="/upload?projectId=${project.id}" var="importActionUrl" />
				<script type="text/javascript">
					var params = new Map();
					params.set('${_csrf.parameterName}', '${_csrf.token}');
				</script>
				
				<button class="btn btn-info" onclick="location.href='${projectUrl}'"><span class="glyphicon glyphicon-eye-open"></span></button>
				<button class="btn btn-primary" onclick="location.href='${updateUrl}'"><span class="glyphicon glyphicon-edit"></span></button>
				<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}', params)"><span class="glyphicon glyphicon-trash"></span></button>
				<button class="btn btn-primary pull-right" onclick="location.href='${importActionUrl}'">Import</button>

			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
