<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">
	<h1>Project Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${projectForm.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${projectForm.name}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Code</label>
		<div class="col-sm-10">${projectForm.code}</div>
	</div>
	
	<spring:url value="/upload?projectId=${project.id}" var="importActionUrl" />
	<button data-toggle="tooltip" class="btn btn-primary" onclick="location.href='${importActionUrl}'" title="Import" style="float: right;"><span class="glyphicon glyphicon-import"></span> Import</button>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>