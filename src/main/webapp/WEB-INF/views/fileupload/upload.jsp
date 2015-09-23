<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />


<body>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well well-sm">
					<form method="POST" action="uploadFile"	enctype="multipart/form-data">
						<fieldset>
							<input	type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input	type="hidden" name="projectId" value="${importProjectId}" />
							
							<div class="form-group">
								<label class="col-sm-2 control-label">File to upload:</label>
								<div class="col-sm-10">
									<input type="file" name="file" class="form-control"  placeholder="File to upload" />
								</div>
                        	</div>
                        	<div class="form-group">
								<label class="col-sm-2 control-label">File Name:</label>
								<div class="col-sm-10">
									<input type="text" name="name" class="form-control"  placeholder="File Name" value="FileName.csv">
								</div>
                        	</div>
                        	<br/>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" value="Upload"	class="btn-lg btn-primary pull-right">
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
<jsp:include page="../fragments/footer.jsp" />
</html>