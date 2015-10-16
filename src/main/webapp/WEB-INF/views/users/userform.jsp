<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp" />

<script src="/database_project/scripts/external/ckeditor/ckeditor.js"></script>

<div class="container">

	<c:choose>
		<c:when test="${userForm['new']}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/users" var="userActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${userActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="login">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Login</label>
				<div class="col-sm-10">
					<form:input path="login" type="text" class="form-control " id="login" placeholder="Login" />
					<form:errors path="login" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<form:password path="password" class="form-control" id="password" placeholder="Password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="confirmPassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Confirm Password</label>
				<div class="col-sm-10">
					<form:password path="confirmPassword" class="form-control" id="confirmPassword" placeholder="Confirm Password" />
					<form:errors path="confirmPassword" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="firstname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10">
					<form:input path="firstname" type="text" class="form-control " id="firstname" placeholder="First Name" />
					<form:errors path="firstname" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="lastname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-10">
					<form:input path="lastname" type="text" class="form-control " id="lastname" placeholder="Last Name" />
					<form:errors path="lastname" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="sex">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Sex</label>
				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton path="sex" value="M" /> Male
					</label> <label class="radio-inline"> <form:radiobutton path="sex" value="F" /> Female
					</label> <br />
					<form:errors path="sex" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="birthday">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Birthday</label>
				<div class="col-sm-5">
					<form:input path="birthday"  placeholder="MM/DD/YYYY"/> <!-- type="date" pattern="mm/dd/yyyy" cssClass="date-picker" -->
					<form:errors path="birthday" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" type="text" class="form-control" id="email" placeholder="Email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="address1">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<form:input path="address1" type="text" class="form-control" id="address1" placeholder="Address" />
					<form:errors path="address1" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="address2">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<form:input path="address2" type="text" class="form-control" id="address2" placeholder="Address" />
					<form:errors path="address2" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="phone">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Phone Number</label>
				<div class="col-sm-10">
					<form:input path="phone" type="text" class="form-control" id="phone" placeholder="Phone" />
					<form:errors path="phone" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="city">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Country</label>
				<div class="col-sm-10">
					<form:input path="city" type="text" class="form-control" id="city" placeholder="City" />
					<form:errors path="city" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="country">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Country</label>
				<div class="col-sm-10">
					<form:input path="country" type="text" class="form-control" id="country" placeholder="Country" />
					<form:errors path="country" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="state">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">State</label>
				<div class="col-sm-10">
					<form:input path="state" type="text" class="form-control" id="state" placeholder="State" />
					<form:errors path="state" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="zip">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Zip Code</label>
				<div class="col-sm-10">
					<form:input path="zip" type="text" class="form-control" id="zip" placeholder="Zip" />
					<form:errors path="zip" class="control-label" />
				</div>
			</div>
		</spring:bind>		
		
		<spring:bind path="organization">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Organization Name</label>
				<div class="col-sm-10">
					<form:input path="organization" type="text" class="form-control" id="organization" placeholder="Organization" />
					<form:errors path="organization" class="control-label" />
				</div>
			</div>
		</spring:bind>		
		
		<spring:bind path="organaddress">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Organization Address</label>
				<div class="col-sm-10">
					<form:input path="organaddress" type="text" class="form-control" id="organaddress" placeholder="Organization Address" />
					<form:errors path="organaddress" class="control-label" />
				</div>
			</div>
		</spring:bind>	
			
		<spring:bind path="notes">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Notes</label>
				<div class="col-sm-10">
					<form:textarea path="notes" rows="5" class="form-control" id="ckeditornotes" placeholder="Notes" />
					<form:errors path="notes" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<script type="text/javascript">CKEDITOR.replace('ckeditornotes');</script>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${userForm['new']}">
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