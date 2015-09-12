<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Web ToxPi</title>

<link href="/ctvproject/scripts/external/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="/ctvproject/scripts/css/hello.css" rel="stylesheet" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/globalize/0.1.1/globalize.min.js"></script>
<script type="text/javascript" src="http://cdn3.devexpress.com/jslib/15.1.6/js/dx.webappjs.js"></script>

<link rel="stylesheet" type="text/css" href="http://cdn3.devexpress.com/jslib/15.1.6/css/dx.common.css" />
<link rel="stylesheet" type="text/css" href="http://cdn3.devexpress.com/jslib/15.1.6/css/dx.dark.css" />
        
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlAddUser" />
<spring:url value="/projects/add" var="urlAddProject" />
<spring:url value="/analysis/${projectId}" var="urlDisplayAnalysis" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Home</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddUser}">Import</a></li>
				<li class="active"><a href="${urlAddProject}">Export</a></li>
				<li class="active"><a href="${urlDisplayAnalysis}">Analysis</a></li>
				<li class="active"><a href="${urlAddProject}">My Page</a></li>			
				<li class="not-active"><a href="#" id="proj">         selected project: ${projectId}	</a></li>	
			</ul>
		</div>
	</div>
</nav>