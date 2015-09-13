<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Collection" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

        
        
<spring:url value="/scripts/css/analysis.css" var="analysisCss" />
<spring:url value="/scripts/js/analysis.js" var="analysisJs" />
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


	 <script>
var columns = [
<c:forEach items="${components}" var="component" varStatus="status">  
    {component: '${component.code}',
    parentId: '${component.columnheaders.id}',
    columnheaderCode: '${component.columnheaders.code}'}
    
    <c:if test="${!status.last}">    
      ,    
    </c:if>  
    </c:forEach>  
];	 

var columnheaders = [
<c:forEach items="${columnheaders}" var="columnheaderlevel" varStatus="status">  
    {
    	columnheaders : [ 
    	             <c:forEach items="${columnheaderlevel}" var="columnheader" varStatus="provinceStatus">  
    	             {code: '${columnheader.code}',
    	             parentId: '${columnheader.columnheaders.id}',
    	             id: '${columnheader.id}'}
    	                <c:if test="${!provinceStatus.last}">    
    	                  ,    
    	                </c:if>   
    	             </c:forEach>  
    	         ]}
    
    
    <c:if test="${!status.last}">    
      ,    
    </c:if>  
    </c:forEach>  
];	 

</script> 
	
	<br />
 <script>

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
		    columns: 	${columns},
		    rowAlternationEnabled: true,
		    onContentReady : 	ddd
		});
	});
	
 
 var ddd = function () {
	 var dataGridInstance = $("#gridContainer").dxDataGrid("instance"), 
     columnCount = dataGridInstance.columnCount(), 
     currentColumns = [],     
     headers = [], 
     i,j,column;
//   ----------------------------------------------------------------------------GET VISIBLE GRID COLUMNS-----------------------------------------------------------------------------------   
 	 for(i = 0; i < columnCount; i++) 
 	 { 
	     if(dataGridInstance.columnOption(i, "visible"))
	     {
	    	 currentColumns.push(dataGridInstance.columnOption(i)/*.dataField*/);
	     }
     }
//   ----------------------------------------------------------------------------GET ORDER OF COLUMNS----------------------------------------------------------------------------------- 	 
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
 	
//  ----------------------------------DISPLAY FIRST LEVEL NEXT TO COMPONENTS-----------------------AND SAVE COLUMNS OBJECTS IN CORRECT ORDER---------------------------------------------
var columnNewOrder= [];
var tr1 = '<tr id="headerId0" class="dx-row dx-column-lines" >';  
 	for(i = 0; i < columnCount; i++) 
	{
 		var result  = $.grep(columns, function(e){ return e.component == currentColumns[i].dataField; })
 		tr1 += '       <td class="dx-datagrid-action" colspan="1">' + result[0].columnheaderCode + '</td>' ;
 		var level1  = $.grep(columnheaders[0].columnheaders, function(e){ return e.id == result[0].parentId; })
 		columnNewOrder.push(level1[0]);
 		
	}

 	tr1 += '</tr>';
 	headers.push(tr1);

     
 //  ----------------------------------------------------------------------------DISPLAY ALL LEVELS-----------------------------------------------------------------------
 var cols= [];    
 if (columnheaders.length > 1)
     for(i = 1; i < columnheaders.length; i++) 
 	{
    	 tr1 = '<tr id="headerId' + i + '" class="dx-row dx-column-lines" >'; 
    	 levels= [];
    	 
    	 for(j = 0; j < columnCount; j++) 
    		{ 
    		    var foundel = $.grep(columnheaders[i].columnheaders, function(e){ return e.id == columnNewOrder[j].parentId; });
    		    tr1 += '       <td class="dx-datagrid-action" colspan="1">' + foundel[0].code + '</td>' ;
    		    levels.push(foundel[0]);
    		};
    		columnNewOrder = levels;
    	 tr1 += '</tr>';
    	 headers.push(tr1);
//     	 var tr = $("#gridContainer").find('.dx-header-row')[0];

//          ele = document.getElementById("headerId" + i);
//          if (tr && !ele) $(tr1).insertBefore(tr.parentElement);
 	}
     for(i = columnheaders.length - 1; i >= 0; i--) 
  	{
     
     var tr = $("#gridContainer").find('.dx-header-row')[0];
     var ele = document.getElementById("headerId" + i);
     if (tr && !ele) $(headers[i]).insertBefore(tr.parentElement);
 	
  	}
 
 
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