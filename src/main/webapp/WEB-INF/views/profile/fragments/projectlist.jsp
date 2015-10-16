<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="/database_project/scripts/js/deletePost.js"></script>

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
			<spring:url value="/database_project/img/in_vitro.png" var="projectTypeImg" />
			<c:choose>
				<c:when test="${project.projecttypes.code=='INVITRO'}">
					<c:set var="projectTypeImg" value="in_vitro.png" />
				</c:when>
				<c:otherwise>
					<c:set var="projectTypeImg" value="in_vivo.png" />
				</c:otherwise>
			</c:choose>
			
			
			<td>${loop.index + 1}</td>
			<td><img height="80" src="/database_project/img/${projectTypeImg}" alt="Project Type Image"></td>
			<td>${project.code}</td>
			<td>${project.name}</td>
			<td class="center">${project.starts}</td>
			<td class="center">${project.ends}</td>
			<td class="center">${project.access.value}</td>
			<td class="center">${project.status.value}</td>
			<td>
				<spring:url value="/projects/${project.id}" var="projectUrl" />
				<spring:url value="/projects/update/${project.id}" var="updateUrl" />
				<spring:url value="/projects/delete/${project.id}" var="deleteUrl" /> 
				<spring:url value="/upload?projectId=${project.id}" var="importActionUrl" />
				<spring:url value="/export/${project.id}" var="exportActionUrl" />
				
				<button data-toggle="tooltip" class="btn btn-info" onclick="location.href='${projectUrl}'" title="View the Project"><span class="glyphicon glyphicon-eye-open"></span></button>
				<c:if test="${allowEditFragment eq true}">
					<button data-toggle="tooltip" class="btn btn-warning" onclick="location.href='${updateUrl}'" title="Update the Project"><span class="glyphicon glyphicon-edit"></span></button>
				</c:if>
				<c:if test="${allowDeleteFragment eq true}">
					<form method="POST" action="${deleteUrl}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button type="submit" value="Select" data-toggle="tooltip" class="btn btn-danger" title="Delete the Project"><span class="glyphicon glyphicon-trash"></span></button>
					</form>
					<%-- <button data-toggle="tooltip" class="btn btn-danger" onclick="this.disabled=true;delete_record('${deleteUrl}')" title="Delete the Project"><span class="glyphicon glyphicon-trash"></span></button> --%>
				</c:if>
				<c:if test="${allowEditFragment eq true}">
					<button data-toggle="tooltip" class="btn btn-primary" onclick="location.href='${importActionUrl}'" title="Import"><span class="glyphicon glyphicon-import"></span></button>
				</c:if>
				<button data-toggle="tooltip" class="btn btn-primary" onclick="location.href='${exportActionUrl}'" title="Export"><span class="glyphicon glyphicon-export"></span></button>

			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
