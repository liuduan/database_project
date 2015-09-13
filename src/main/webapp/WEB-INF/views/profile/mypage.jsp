<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<spring:url value="/projects/add" var="urlAddProject" />

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>User Profile</h1>
		<br />

		<div class="row">
			<div class="col-sm-3">
				<div class="panel widget light-widget panel-bd-top">
					<div class="panel-heading no-title"></div>
					<div class="panel-body">
						<div class="text-center vd_info-parent">
							<img alt="Avatar" width="200px" height="200px" src="img/avatar/big.jpg">
						</div>
						<div class="row">
							<div class="col-xs-12">
								<a class="btn"><i class="fa fa-envelope append-icon"></i>Send Message</a>
							</div>
						</div>
						<h2 class="font-semibold mgbt-xs-5">${profile.firstname} ${profile.lastname}</h2>
						<h4>${profile.organization}</h4>
						<p>${profile.notes}</p>
						<div class="mgtp-20">
							<table class="table table-striped table-hover">
								<tbody>
									<tr>
										<td style="width: 60%;">Status</td>
										<td><span class="label label-success">Active</span></td>
									</tr>
									<tr>
										<td>Member Since</td>
										<td>${profile.registereddt}</td>
									</tr>
									<tr>
										<td>Last visit</td>
										<td>${profile.lastvisitdt}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="tabs widget">
					<ul class="nav nav-tabs widget">
						<li class="active"><a data-toggle="tab" href="#profile-tab"> Profile <span class="menu-active"><i class="fa fa-caret-up"></i></span></a></li>
						<li class=""><a data-toggle="tab" href="#projects-tab">	Projects <span class="menu-active"><i class="fa fa-caret-up"></i></span></a></li>
					</ul>
					
					<div class="tab-content">
						<div id="profile-tab" class="tab-pane active">
							<div class="pd-20">
								<div class="vd_info tr" style="float: right;">
									<spring:url value="/users/update/${profile.id}" var="updateUserUrl" />
									<a class="btn btn-primary" onclick="location.href='${updateUserUrl}'"> <i class="glyphicon glyphicon-edit"></i> Edit</a>
								</div>
								<h3 class="mgbt-xs-15 mgtp-10 font-semibold"><i class="glyphicon glyphicon-user"></i> About</h3>
								<div class="row">
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">First Name:</label>
											<div class="col-xs-7 controls">${profile.firstname}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Last Name:</label>
											<div class="col-xs-7 controls">${profile.lastname}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Login:</label>
											<div class="col-xs-7 controls">${profile.login}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Email:</label>
											<div class="col-xs-7 controls">${profile.email}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">City:</label>
											<div class="col-xs-7 controls">${profile.city}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Country:</label>
											<div class="col-xs-7 controls">${profile.country}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">State:</label>
											<div class="col-xs-7 controls">${profile.state}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">ZIP:</label>
											<div class="col-xs-7 controls">${profile.zip}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Address</label>
											<div class="col-xs-7 controls">${profile.address1}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Address</label>
											<div class="col-xs-7 controls">${profile.address2}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Birthday:</label>
											<div class="col-xs-7 controls">${profile.birthday}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Interests:</label>
											<div class="col-xs-7 controls">${profile.interests}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Website:</label>
											<div class="col-xs-7 controls">
												<a href="${profile.website}">${profile.website}</a>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Phone:</label>
											<div class="col-xs-7 controls">${profile.phone}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Organization:</label>
											<div class="col-xs-7 controls">${profile.organization}</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="row mgbt-xs-0">
											<label class="col-xs-5 control-label">Organization:</label>
											<div class="col-xs-7 controls">${profile.organaddress}</div>
										</div>
									</div>
								</div>
							</div>

						</div>


						<div id="projects-tab" class="tab-pane">
							<div class="pd-20">
								<div class="vd_info tr" style="float: right;">
									<a href="${urlAddProject}" class="btn btn-success "> <i class="glyphicon glyphicon-plus"></i> Add Project</a>
								</div>
								<h3 class="mgbt-xs-15 mgtp-10 font-semibold">
									<i class="glyphicon glyphicon-flash"></i> Projects
								</h3>
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
									<c:forEach var="project" items="${projectsList}" varStatus="loop">
										<tr>
											<td>${loop.index + 1}</td>
											<td><img height="80" src="/ctvproject/img/in_vivo.png" alt="example image"></td>
											<td>${project.code}</td>
											<td>${project.name}</td>
											<td class="center">${project.starts}</td>
											<td class="center">${project.ends}</td>
											<td class="center">${project.access}</td>
											<td class="center">${project.status}</td>
											<td>
												<spring:url value="/projects/${project.id}" var="projectUrl" />
												<spring:url value="/projects/update/${project.id}" var="updateUrl" />
												<spring:url value="/projects/delete/${project.id}" var="deleteUrl" /> 
												
												<button class="btn btn-info" onclick="location.href='${projectUrl}'"><span class="glyphicon glyphicon-eye-open"></span></button>
												<button class="btn btn-primary" onclick="location.href='${updateUrl}'"><span class="glyphicon glyphicon-edit"></span></button>
												<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')"><span class="glyphicon glyphicon-trash"></span></button>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<div class=""></div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>