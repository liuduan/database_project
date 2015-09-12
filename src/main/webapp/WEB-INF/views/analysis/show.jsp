<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<spring:url value="/ctvproject/css/analysis.css" var="analysisCss" />
<spring:url value="/ctvproject/js/analysis.js" var="analysisJs" />
<script src="${analysisJs}"></script>
<link href="${analysisCss}" rel="stylesheet" />
<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Analysis:</h1>
	<strong>'${results}'</strong>
	<br />
 <script>
 $(function () {
		$("#gridContainer").dxDataGrid({
			allowColumnReordering: true,
		    allowColumnResizing: true,
		    columnAutoWidth: true,
		    columnChooser: {
		        enabled: true
		    },
		    columnFixing: { 
		        enabled: true
		    }, 
		    dataSource: ${results},
//		    showRowLines: true,
		    paging: {
		        pageSize: 10
		    },
		    pager: {
		        showPageSizeSelector: true,
		        allowedPageSizes: [5, 10, 20]
		    },
//		    columns: '${analysis.columns}',
		    columns: 	${columns},
		    rowAlternationEnabled: true,
		    onContentReady : 	ddd
		});
	});
	
 
 var ddd = function () {
     var tr1 = '<tr id="headerId" class="dx-row dx-column-lines" >';        
     tr1 += '       <td class="dx-datagrid-action" colspan="1">Some text header</td>';
     tr1 += '       <td class="dx-datagrid-action" colspan="1">Some text header</td>';
     tr1 += '</tr>'

     var tr = $("#gridContainer").find('.dx-header-row')[0];
     var ele = document.getElementById("headerId");
     if (tr && !ele) $(tr1).insertBefore(tr.parentElement);
}
  setTimeout(function () {
// 	  ddd();
}, 100); 
 </script> 
	<div class="row">
		<label class="col-sm-2">ID</label>

	</div>

</div>
<h1>THIS IS A TEST GRID:</h1>
<div id="gridContainer"></div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>