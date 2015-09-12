<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp" />


<link href="/ctvproject/scripts/external/select2/css/select2.css" rel="stylesheet" />
<script src="/ctvproject/scripts/external/select2/js/select2.js"></script>
<script src="/ctvproject/scripts/external/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
	function formatValues(data) {
		return data.firstname + ' ' + data.lastname;
	}
</script>

<div class="container">

	<c:choose>
		<c:when test="${projectForm['new']}">
			<h1>Add Project</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Project</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/projects" var="projectActionUrl" />

	<form:form class="form-horizontal" method="post"
		modelAttribute="projectForm" action="${projectActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="code">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Code</label>
				<div class="col-sm-10">
					<form:input path="code" type="text" class="form-control "
						id="firstName" placeholder="Project Code" />
					<form:errors path="code" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Project Name</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control "
						id="firstName" placeholder="Project Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="access">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Access Level</label>
				<div class="col-sm-5">
					<form:select path="access" class="form-control">
						<form:options items="${accessList}" />
					</form:select>
					<form:errors path="access" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>

   		<spring:bind path="projecttypes">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Project Type</label>
				<div class="col-sm-5">
					<form:select path="projecttypes">
						<c:forEach items="${projectTypesCache}" var="pt">
			                <c:choose>
			                    <c:when test="${projecttypes.getId()==pt.id}">
			                        <option value="${pt.id}" selected="selected">${pt.name}</option>
			                    </c:when>
			                    <c:otherwise>
			                        <option value="${pt.id}">${pt.name}</option>
			                    </c:otherwise>
			                </c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>

		<spring:bind path="notes">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Project Description</label>
				<div class="col-sm-10">
					<form:textarea path="notes" rows="5" class="form-control" id="ckeditornotes" placeholder="Description" />
					<form:errors path="notes" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<script type="text/javascript">CKEDITOR.replace('ckeditornotes');</script>

		<spring:bind path="projectmanagerses">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Project Managers</label>
				<div class="col-sm-10">
					<form:select multiple="true" path="projectmanagerses">
						<c:forEach items="${usersCache}" var="user">
			                <c:set var="isSelected" value="false" />
			                <c:forEach items="${projectmanagerses}" var="pr">
			                    <c:if test="${pr.getUsers().getId()==user.id}">
			                        <c:set var="isSelected" value="true" />
			                    </c:if>
			                </c:forEach>
			                <c:choose>
			                    <c:when test="${isSelected}">
			                        <option value="${user.id}" selected="selected">${user.firstname}</option>
			                    </c:when>
			                    <c:otherwise>
			                        <option value="${user.id}">${user.firstname}</option>
			                    </c:otherwise>
			                </c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>
		
		<spring:bind path="projectreviewerses">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Project Reviewers</label>
				<div class="col-sm-10">
					<form:select multiple="true" path="projectreviewerses">
						<c:forEach items="${usersCache}" var="user">
			                <c:set var="isSelected" value="false" />
			                <c:forEach items="${projectreviewerses}" var="pr">
			                    <c:if test="${pr.getUsers().getId()==user.id}">
			                        <c:set var="isSelected" value="true" />
			                    </c:if>
			                </c:forEach>
			                <c:choose>
			                    <c:when test="${isSelected}">
			                        <option value="${user.id}" selected="selected">${user.firstname}</option>
			                    </c:when>
			                    <c:otherwise>
			                        <option value="${user.id}">${user.firstname}</option>
			                    </c:otherwise>
			                </c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>
		
		<spring:bind path="projectmemberses">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Project Members</label>
				<div class="col-sm-10">
					<form:select multiple="true" path="projectmemberses">
						<c:forEach items="${usersCache}" var="user">
			                <c:set var="isSelected" value="false" />
			                <c:forEach items="${projectmemberses}" var="pm">
			                    <c:if test="${pm.getUsers().getId()==user.id}">
			                        <c:set var="isSelected" value="true" />
			                    </c:if>
			                </c:forEach>
			                <c:choose>
			                    <c:when test="${isSelected}">
			                        <option value="${user.id}" selected="selected">${user.firstname}</option>
			                    </c:when>
			                    <c:otherwise>
			                        <option value="${user.id}">${user.firstname}</option>
			                    </c:otherwise>
			                </c:choose>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>

		<script type="text/javascript">
			function select2function(selectObject, desc) {
				selectObject.select2({
							placeholder : "Search for a " + desc,
							multiple : true,
							minimumInputLength : 2
						});
			};

			select2function($("#projectmanagerses"), "managers");
			select2function($("#projectreviewerses"), "reviewers");
			select2function($("#projectmemberses"), "members");
		</script>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${projectForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />
</body>
</html>