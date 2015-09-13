<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Collection" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

        
        
<spring:url value="/resources/css/analysis.css" var="analysisCss" />
<spring:url value="/resources/js/analysis.js" var="analysisJs" />
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

<div id="MyEdit">
    This text will change
</div>
 <div id="MyEdit1">
    this fuckin text is CHANGING RIGHT NOW
</div>
	 <script>
var countries = [
<c:forEach items="${components}" var="component" varStatus="status">  
    {component: '${component.code}',
    columnheader: '${component.columnheaders.id}',
    columnheaderCode: '${component.columnheaders.code}'}
    
    <c:if test="${!status.last}">    
      ,    
    </c:if>  
    </c:forEach>  
];	 
document.getElementById("MyEdit1").innerHTML=countries;
</script> 
	<h1>Analysis:</h1>
	
	
  <c:forEach var="listValue1" items="${components}">
	<li>${listValue1.columnheaders.id}</li>
	</c:forEach>
	

	<br />
 <script>
//  var currentColumns;
//  currentColumns = $("#gridContainer").dxDataGrid('instance').columns;
//  document.getElementById("MyEdit").innerHTML=currentColumns;
//  $("#MyEdit").html(columnHeaders.val());
 $(function () {
	 
		$("#gridContainer").dxDataGrid({
			allowColumnReordering: true,
		    allowColumnResizing: true,
		    columnAutoWidth: true,
// 		    columnChooser: {
// 		        enabled: true
// 		    },
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
	 var dataGridInstance = $("#gridContainer").dxDataGrid("instance"), 
     columnCount = dataGridInstance.columnCount(), 
     currentColumns = [],
     currentColumnCodes = [], 
     i,j,column;
     
 	 for(i = 0; i < columnCount; i++) 
 	 { 
	     if(dataGridInstance.columnOption(i, "visible"))
	     {
	    	 currentColumns.push(dataGridInstance.columnOption(i)/*.dataField*/);
	     }
     }
 	 
 	for(j = 0; j < columnCount; j++) 
 	for(i = 0; i < columnCount-1; i++) 
	{ 
	     if(currentColumns[i].visibleIndex > currentColumns[i+1].visibleIndex)
	     {
	    	 column = currentColumns[i];
	    	 currentColumns[i] = currentColumns[i+1];
	    	 currentColumns[i+1] = column;	    	 
	     }
    }
 	for(i = 0; i < columnCount; i++) 
	{ 
 		currentColumnCodes.push(currentColumns[i].dataField);
    }
 	
//  	currentColumnCodes.push(${components}[0].columnheaders.id);

var tr1 = '<tr id="headerId" class="dx-row dx-column-lines" >';  
 	for(i = 0; i < columnCount; i++) 
	{
 		var result  = $.grep(countries, function(e){ return e.component == currentColumns[i].dataField; })
 		tr1 += '       <td class="dx-datagrid-action" colspan="1">' + result[0].columnheaderCode + '</td>' ;
 		
	}
 	tr1 += '</tr>'
//  		${listValue1.columnheaders.id} find by currentColumns[i].dataField
var tr = $("#gridContainer").find('.dx-header-row')[0];
     var ele = document.getElementById("headerId");
     if (tr && !ele) $(tr1).insertBefore(tr.parentElement);
 	
 	
 document.getElementById("MyEdit").innerHTML=currentColumnCodes;
 
 
//      var tr1 = '<tr id="headerId" class="dx-row dx-column-lines" >';        
//      tr1 += '       <td class="dx-datagrid-action" colspan="1">11</td>';
//      tr1 += '       <td class="dx-datagrid-action" colspan="1">Some text header</td>';
//      tr1 += '</tr>'

//      var tr = $("#gridContainer").find('.dx-header-row')[0];
//      var ele = document.getElementById("headerId");
//      if (tr && !ele) $(tr1).insertBefore(tr.parentElement);
     
     

}
  setTimeout(function () {
// 	  ddd();
}, 100); 
 </script> 
	

</div>
<h1>THIS IS A TEST GRID:</h1>
<div id="gridContainer"></div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>