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
							<img alt="example image" src="img/avatar/big.jpg">
						</div>
						<div class="row">
							<div class="col-xs-12">
								<a class="btn vd_btn vd_bg-grey btn-xs btn-block no-br"><i
									class="fa fa-envelope append-icon"></i>Send Message</a>
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
								<div class="vd_info tr">
									<a class="btn vd_btn btn-xs vd_bg-yellow"> <i
										class="fa fa-pencil append-icon"></i> Edit
									</a>
								</div>
								<h3 class="mgbt-xs-15 mgtp-10 font-semibold">
									<i class="icon-user mgr-10 profile-icon"></i> ABOUT
								</h3>
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
								<div class="vd_info tr">
									<a class="btn vd_btn btn-xs vd_bg-yellow"> <i
										class="fa fa-plus append-icon"></i> Add Project
									</a>
								</div>
								<h3 class="mgbt-xs-15 mgtp-10 font-semibold">
									<i class="fa fa-bolt mgr-10 profile-icon"></i> PROJECTS
								</h3>
								<table class="table table-striped table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>Logo / Photos</th>
											<th>Clients</th>
											<th>Start Date</th>
											<th>Status</th>
											<th>Revenue</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td><img height="80" src="img/avatar/avatar.jpg"
												alt="example image"></td>
											<td>Zoe Corp</td>
											<td class="center">2010/02/03</td>
											<td class="center"><span class="label label-success">Finish</span>
											</td>
											<td class="center"><strong>$ 250</strong></td>
											<td class="menu-action rounded-btn"><a
												class="btn menu-icon vd_bg-green" data-placement="top"
												data-toggle="tooltip" data-original-title="view"> <i
													class="fa fa-eye"></i>
											</a> <a class="btn menu-icon vd_bg-yellow" data-placement="top"
												data-toggle="tooltip" data-original-title="edit"> <i
													class="fa fa-pencil"></i>
											</a> <a class="btn menu-icon vd_bg-red" data-placement="top"
												data-toggle="tooltip" data-original-title="delete"> <i
													class="fa fa-times"></i>
											</a></td>
										</tr>
										<tr>
											<td>2</td>
											<td><img height="80" src="img/avatar/avatar-2.jpg"
												alt="example image"></td>
											<td>Zoe Corp</td>
											<td class="center">2010/02/03</td>
											<td class="center"><span class="label label-success">Finish</span>
											</td>
											<td class="center"><strong>$ 250</strong></td>
											<td class="menu-action rounded-btn"><a
												class="btn menu-icon vd_bg-green" data-placement="top"
												data-toggle="tooltip" data-original-title="view"> <i
													class="fa fa-eye"></i>
											</a> <a class="btn menu-icon vd_bg-yellow" data-placement="top"
												data-toggle="tooltip" data-original-title="edit"> <i
													class="fa fa-pencil"></i>
											</a> <a class="btn menu-icon vd_bg-red" data-placement="top"
												data-toggle="tooltip" data-original-title="delete"> <i
													class="fa fa-times"></i>
											</a></td>
										</tr>
										<tr>
											<td>3</td>
											<td><img height="80" src="img/avatar/avatar-3.jpg"
												alt="example image"></td>
											<td>Zoe Corp</td>
											<td class="center">2010/02/03</td>
											<td class="center"><span class="label label-success">Finish</span>
											</td>
											<td class="center"><strong>$ 250</strong></td>
											<td class="menu-action rounded-btn"><a
												class="btn menu-icon vd_bg-green" data-placement="top"
												data-toggle="tooltip" data-original-title="view"> <i
													class="fa fa-eye"></i>
											</a> <a class="btn menu-icon vd_bg-yellow" data-placement="top"
												data-toggle="tooltip" data-original-title="edit"> <i
													class="fa fa-pencil"></i>
											</a> <a class="btn menu-icon vd_bg-red" data-placement="top"
												data-toggle="tooltip" data-original-title="delete"> <i
													class="fa fa-times"></i>
											</a></td>
										</tr>
										<tr>
											<td>4</td>
											<td><img height="80" src="img/avatar/avatar-4.jpg"
												alt="example image"></td>
											<td>Zoe Corp</td>
											<td class="center">2010/02/03</td>
											<td class="center"><span class="label label-success">Finish</span>
											</td>
											<td class="center"><strong>$ 250</strong></td>
											<td class="menu-action rounded-btn"><a
												class="btn menu-icon vd_bg-green" data-placement="top"
												data-toggle="tooltip" data-original-title="view"> <i
													class="fa fa-eye"></i>
											</a> <a class="btn menu-icon vd_bg-yellow" data-placement="top"
												data-toggle="tooltip" data-original-title="edit"> <i
													class="fa fa-pencil"></i>
											</a> <a class="btn menu-icon vd_bg-red" data-placement="top"
												data-toggle="tooltip" data-original-title="delete"> <i
													class="fa fa-times"></i>
											</a></td>
										</tr>
										<tr>
											<td>5</td>
											<td><img height="80" src="img/avatar/avatar-5.jpg"
												alt="example image"></td>
											<td>Zoe Corp</td>
											<td class="center">2010/02/03</td>
											<td class="center"><span class="label label-success">Finish</span>
											</td>
											<td class="center"><strong>$ 250</strong></td>
											<td class="menu-action rounded-btn"><a
												class="btn menu-icon vd_bg-green" data-placement="top"
												data-toggle="tooltip" data-original-title="view"> <i
													class="fa fa-eye"></i>
											</a> <a class="btn menu-icon vd_bg-yellow" data-placement="top"
												data-toggle="tooltip" data-original-title="edit"> <i
													class="fa fa-pencil"></i>
											</a> <a class="btn menu-icon vd_bg-red" data-placement="top"
												data-toggle="tooltip" data-original-title="delete"> <i
													class="fa fa-times"></i>
											</a></td>
										</tr>


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