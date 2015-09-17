<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Web ToxPi</title>

<link href="/ctvproject/scripts/external/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="/ctvproject/scripts/css/hello.css" rel="stylesheet" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.11.4/jquery-ui.min.js"></script>

        
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/globalize/0.1.1/globalize.min.js"></script>
<script type="text/javascript" src="/ctvproject/scripts/external/dx/js/dx.webappjs.js"></script>
<!--  <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/15.1.6/js/dx.webappjs.js"></script> -->
<link rel="stylesheet" type="text/css" href="/ctvproject/scripts/external/dx/css/dx.common.css" />
<link rel="stylesheet" type="text/css" href="/ctvproject/scripts/external/dx/css/dx.dark.css" />
<link rel="stylesheet" type="text/css" href="/ctvproject/scripts/external/dx/css/dx.spa.css" />
<link rel="stylesheet" type="text/css" href="/ctvproject/scripts/external/font-awesome/css/font-awesome.min.css" />   
<!-- <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/14.2.6/js/dx.chartjs.js"></script> -->
<!-- <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/14.2.6/js/angular.js"></script> -->
<!--     <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/14.2.6/js/angular-sanitize.js"></script> -->
    <script type="text/javascript" src="/ctvproject/scripts/external/dx/js/dx.all.js"></script>
<link rel="shortcut icon" href="/ctvproject/img/favicon.png" type="image/x-icon" sizes="64x64">
</head>

<spring:url value="/" var="urlHome" />

<spring:url value="/users" var="urlListUser" />
<spring:url value="/users/add" var="urlAddUser" />

<spring:url value="/projects" var="urlListProject" />
<spring:url value="/projects/add" var="urlAddProject" />

<spring:url value="/import" var="urlImport" />
<spring:url value="/export" var="urlExport" />

<spring:url value="/analysis/${projectId}" var="urlDisplayAnalysis" />
<spring:url value="/profile" var="urlProfile" />
<spring:url value="/contact" var="urlContact" />

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
		   <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
			<a class="navbar-brand" href="${urlHome}" style="padding: 5px;">
				<!--  <span class="glyphicon glyphicon-home" aria-hidden="true">-->
					<img src="/ctvproject/img/ctv_logo.png" alt="logo" height="40" width="150" style="display: inline-block; ">
				<!--</span>-->
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				
			<li class="dropdown active">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${urlListUser}">List</a></li>
                <li><a href="${urlAddUser}">Add User</a></li>
              </ul>
            </li>
			<li class="dropdown active">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Project <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${urlListProject}">List</a></li>
                <li><a href="${urlAddProject}">Add Project</a></li>
              </ul>
            </li>	
				
				<li class="active"><a href="${urlImport}">Import</a></li>
				<li class="active"><a href="${urlExport}">Export</a></li>
				
				<li class="active"><a href="${urlDisplayAnalysis}">Analysis</a></li>
				
				<li class="active"><a href="${urlContact}"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Contact</a></li>	
				<li class="active"><a href="${urlProfile}"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> My Page</a></li>			
				<li class="not-active"><a href="#" id="proj">         selected project: ${projectId}	</a></li>	
			</ul>
		</div>
	</div>
</nav>