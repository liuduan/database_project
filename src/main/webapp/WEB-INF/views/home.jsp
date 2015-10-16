<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/header.jsp" />
<link rel="stylesheet" type="text/css" href="/database_project/scripts/external/font-awesome/css/font-awesome.min.css" />
<body>
<spring:url value="/profile" var="urlProfile" />
<spring:url value="/import" var="urlImport" />
<spring:url value="/export" var="urlExport" />
<spring:url value="/analysis/${projectId}" var="urlDisplayAnalysis" />


    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Welcome</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <img class="img-responsive" src="http://placehold.it/1200x300" alt="">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Services</h2>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
                        <span class="fa-stack fa-5x">
                              <i class="fa fa-circle fa-stack-2x text-primary"></i>
                              <i class="fa fa-user fa-stack-1x fa-inverse"></i>
                        </span>
                    </div>
                    <div class="panel-body">
                        <h4>My Page</h4>
                        <p>Information about your projects.</p>
                        <a href="${urlProfile}" class="btn btn-primary">GoTo</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
                        <span class="fa-stack fa-5x">
                              <i class="fa fa-circle fa-stack-2x text-primary"></i>
                              <i class="fa fa-download fa-stack-1x fa-inverse"></i>
                        </span>
                    </div>
                    <div class="panel-body">
                        <h4>Import</h4>
                        <p>Import data from csv file.</p>
                        <a href="${urlImport}" class="btn btn-primary">Goto</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
                        <span class="fa-stack fa-5x">
                              <i class="fa fa-circle fa-stack-2x text-primary"></i>
                              <i class="fa fa-upload fa-stack-1x fa-inverse"></i>
                        </span>
                    </div>
                    <div class="panel-body">
                        <h4>Export</h4>
                        <p>Export data to csv file.</p>
                        <a href="${urlExport}" class="btn btn-primary">GoTo</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
                        <span class="fa-stack fa-5x">
                              <i class="fa fa-circle fa-stack-2x text-primary"></i>
                              <i class="fa fa-bar-chart fa-stack-1x fa-inverse"></i>
                        </span>
                    </div>
                    <div class="panel-body">
                        <h4>Analysis</h4>
                        <p>Analysis all data in current project.</p>
                        <a href="${urlDisplayAnalysis}" class="btn btn-primary">GoTo</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>