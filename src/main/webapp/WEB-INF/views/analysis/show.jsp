<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Collection" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

        <script type="text/javascript" src="/database_project/scripts/external/dx/js/dx.webappjs.js"></script>
<!--  <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/15.1.6/js/dx.webappjs.js"></script> -->
<link rel="stylesheet" type="text/css" href="/database_project/scripts/external/dx/css/dx.common.css" />
<link rel="stylesheet" type="text/css" href="/database_project/scripts/external/dx/css/dx.dark.css" />
<link rel="stylesheet" type="text/css" href="/database_project/scripts/external/dx/css/dx.spa.css" />
<!-- <link rel="stylesheet" type="text/css" href="/database_project/scripts/external/font-awesome/css/font-awesome.min.css" />    -->
<!-- <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/14.2.6/js/dx.chartjs.js"></script> -->
<!-- <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/14.2.6/js/angular.js"></script> -->
<!--     <script type="text/javascript" src="http://cdn3.devexpress.com/jslib/14.2.6/js/angular-sanitize.js"></script> -->
    <script type="text/javascript" src="/database_project/scripts/external/dx/js/dx.all.js"></script>
        
<spring:url value="/scripts/css/analysis.css" var="analysisCss" />
<spring:url value="/scripts/js/analysis.js" var="analysisJs" />
<script src="${analysisJs}"></script>
<link href="${analysisCss}" rel="stylesheet" />


	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>


	 <script>
	 
	 var resultGridData, resultGridHeader,
		currentOrders = [],     
	     currentComponents = []; 
	 function exportcsv()
	 {
	 // $.ajax({
//	  	   url: '/database_project/export/byparams',
//	  	   data: { "projectid" : 1, "orderid[]" : currentOrders , "componentid[]" : currentComponents },
//	  	   async: false,
//	  	   type: 'GET'
//	  	});
	 	
	 var b = function(callback)
	 {
	 $.ajax({
	 	   url: '/database_project/export/byparams',
	 	   data: { "projectid" : 1, "orderid[]" : currentOrders , "componentid[]" : currentComponents },
	 	   async: false,
	 	   success: callback,	    	
	 	   type: 'GET'
	 	});
	 };


	 b(function(response) 
	 {
	 	resultGridData22 = response;	
	 });
	 };
	 
var	 gridRefreshed = false;
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
// 		    columnAutoWidth: true,
// 		    columnChooser: {
// 		        enabled: true
// 		    },
// 		    columnFixing: { 
// 		        enabled: true
// 		    }, 
		    dataSource: ${results},
//		    showRowLines: true,
		    paging: {
		        pageSize: 15
		    },
		    pager: {
		        showPageSizeSelector: true,
		        allowedPageSizes: [10, 15, 20]
		    },
		    columns: 	
		    [


<c:forEach items="${rowTypes}" var="component">  
 {dataField:"${component.code}",/*fixed: true*/}
,
</c:forEach> 

<c:forEach items="${components}" var="component" varStatus="status">  
 "${component.code}"

<c:if test="${!status.last}">    
  ,    
</c:if>  
</c:forEach> 
		    ],
		    rowAlternationEnabled: true,
		    onContentReady : 	ddd
		});
		
		
		
		
		
		$("#componentsGrid").dxDataGrid({
			allowColumnReordering: true,
		    allowColumnResizing: true,
		    columnAutoWidth: true,
		    filterRow: {
		        visible: true,
		        applyFilter: "auto"
		    },
		    searchPanel: {
		        visible: true,
		        width: 240,
		        placeholder: 'Search...'
		    },
		    headerFilter: {
		        visible: true
		    },
		    selection: {
		        mode: 'multiple'
		    },
		    hoverStateEnabled: true,
		    dataSource: ${results},

		    paging: {
		        pageSize: 15
		    },
		    pager: {
		        showPageSizeSelector: true,
		        allowedPageSizes: [10, 15, 20]
		    },
		    columns: 	
		    [

<c:forEach items="${rowTypes}" var="component" varStatus="status">  
 {dataField:"${component.code}", caption:"${component.code}"}
 <c:if test="${!status.last}">    
 ,    
</c:if> 
</c:forEach> 

		    ],
		    rowAlternationEnabled: true,
		    onSelectionChanged: function (selecteditems) {
		    	currentOrders = [];
		    	for(i = 0; i < selecteditems.selectedRowsData.length; i++) 
		    	 { 
		   	     
		    		currentOrders.push(selecteditems.selectedRowsData[i].id);
		   	     
		        }	
		    
		    	getResultsFunction();
		    }
		    
		});
		
		var getResultsFunction = function(){
			var a = function(callback)
	    	{
	    	$.ajax({
	    		   url: '/database_project/results/get',
	    		   data:{"orderid[]" : currentOrders , "componentid[]" : currentComponents},
	    		   async: false,
	    	       success: callback,	    		   
	    		   type: 'GET'
	    		});
	    	};


	    	a(function(response) 
	    	{
	    		resultGridData = response.resultValueList; 
    			resultGridHeader = response.columnCodeList;
	    	});

		    	var dataGridResults = $("#gridResults").dxDataGrid("instance");
		    	dataGridResults.option("dataSource", resultGridData);
		    	dataGridResults.option("columns", resultGridHeader);
		        dataGridResults.columnOption("id", "visible", false);
// 		        dataGridResults.columnOption("value", "allowEditing", true);
		        
		        for(i = 0; i < resultGridHeader.length; i++) 
		    	{ 
		        	if (resultGridHeader[i] != "Value")
		        	dataGridResults.columnOption(resultGridHeader[i], "allowEditing", false);
		    	}
		}
		
		$("#chemicalsGrid").dxDataGrid({
			allowColumnReordering: true,
		    allowColumnResizing: true,
		    columnAutoWidth: true,
		    filterRow: {
		        visible: true,
		        applyFilter: "auto"
		    },
		    searchPanel: {
		        visible: true,
		        width: 240,
		        placeholder: 'Search...'
		    },
		    headerFilter: {
		        visible: true
		    },
		    selection: {
		        mode: 'multiple'
		    },
		    hoverStateEnabled: true,
		    dataSource: ${columnHeaderResults},

		    paging: {
		        pageSize: 15
		    },
		    pager: {
		        showPageSizeSelector: true,
		        allowedPageSizes: [10, 15, 20]
		    },
		    columns: 	
		    [

<c:forEach items="${columnTypes}" var="component" varStatus="status">  
 {dataField:"${component.code}", caption:"${component.code}"}
 <c:if test="${!status.last}">    
 ,    
</c:if> 
</c:forEach> 

		    ],
		    rowAlternationEnabled: true,
		    onSelectionChanged: function (selecteditems) {
		    	currentComponents = [];
		    		  
		    	for(i = 0; i < selecteditems.selectedRowsData.length; i++) 
		    	 { 
		   	     
		    		currentComponents.push(selecteditems.selectedRowsData[i].id);
		   	     
		        }	
		    	
		    	getResultsFunction();
		    },
		    onContentReady : 	function()
		    {
		    	var dataGridComponents = $("#chemicalsGrid").dxDataGrid("instance");
		    	dataGridComponents.columnOption("id", "visible", false);
		    }
		    
		});
		
		$("#gridResults").dxDataGrid({
			allowColumnReordering: true,
		    allowColumnResizing: true,
		    columnAutoWidth: true,
// 		    columnAutoWidth: true,
// 		    columnChooser: {
// 		        enabled: true
// 		    },
// 		    columnFixing: { 
// 		        enabled: true
// 		    }, 
editing: {
	editMode: 'cell',
    editEnabled: true
//         removeEnabled: true,
//         insertEnabled: true
    }, 
// 		    dataSource: resultGridData,
//		    showRowLines: true,
		    paging: {
		        pageSize: 15
		    },
		    pager: {
		        showPageSizeSelector: true,
		        allowedPageSizes: [10, 15, 20]
		    },
// 		    columns: 	
// 		    [
// for(i = 0; i < resultGridHeader.length; i++) 
// { 
// 	eval("resultGridHeader[i]");
// 	if (i!= resultGridHeader.length-1)
// 		{
// 		eval(",");
// 		}
   
// }	
// resultGridHeader
// <c:forEach items="${components}" var="component" varStatus="status">  
//  "${component.code}"

// <c:if test="${!status.last}">    
//   ,    
// </c:if>  
// </c:forEach> 
// 		    ],
		    onRowUpdated: function(e) {
		    	
		  
// 		    	          $.post("/database_project/results/update/byid", //Required URL of the page on server
// 		    	    		  { // Data Sending With Request To Server
// 		    	    	  id:e.key["id"],
// 		    	    	  value:e.key["Value"]
// 		    	    		  },
// 		    	    		  function(response,status){ // Required Callback Function
// 		    	    		  alert("*----Received Data----*\n\nResponse : " + response+"\n\nStatus : " + status);//"response" receives - whatever written in echo of above PHP script.
		    	    		  
// 		    	    		  });
// var header = $("meta[name='_csrf_header']").attr("content");
// var token = $("meta[name='_csrf']").attr("content");
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");
		    	$.ajax({
		            url: '/database_project/results/update/byid',
		            type: 'POST',
		            beforeSend: function(xhr){
		                xhr.setRequestHeader(header, token);
		            },
		            dataType:"json",
		            data: {id:e.key["id"], value:e.key["Value"]} ,
// 		            contentType: 'application/json; charset=utf-8',


		            success: function (response) {
		                //your success code
		            },
		            error: function () {
		                //your error code
		            }
		        });
		    	
		    },
		    rowAlternationEnabled: true
		
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
 		if (result.length==0)
 		{
 			tr1 += '       <td class="dx-datagrid-action" colspan="1"></td>' ;
 			columnNewOrder.push(null);
 		}
 		else
 		{
	 		tr1 += '       <td class="dx-datagrid-action" colspan="1">' + result[0].columnheaderCode + '</td>' ;
	 		var level1  = $.grep(columnheaders[0].columnheaders, function(e){ return e.id == result[0].parentId; })
	 		columnNewOrder.push(level1[0]);
 		}
 		
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
    		 if ( columnNewOrder[j] == null )
    		 {
    			 tr1 += '       <td class="dx-datagrid-action" colspan="1"></td>' ;
     		    levels.push(null);
    		 }
    		 else
    		 {
    		    var foundel = $.grep(columnheaders[i].columnheaders, function(e){ return e.id == columnNewOrder[j].parentId; });
    		    tr1 += '       <td class="dx-datagrid-action" colspan="1">' + foundel[0].code + '</td>' ;
    		    levels.push(foundel[0]);
    		 }
    		};
    		columnNewOrder = levels;
    	 tr1 += '</tr>';
    	 headers.push(tr1);

 	}

     for(i = columnheaders.length - 1; i >= 0; i--) 
  	{
     
     var tr = $("#gridContainer").find('.dx-header-row')[0];
     var ele = document.getElementById("headerId" + i);
     if (tr && !ele) $(headers[i]).insertBefore(tr.parentElement);
  	}
 
     <c:forEach items="${rowTypes}" var="component">  
     dataGridInstance.columnOption("${component.code}","fixed", true);

     </c:forEach> 



}
  setTimeout(function () {

}, 100); 
  
  

  
    
//   jQuery(function($) {
// 	  $('#componentsGrid')
// 	    .bind('beforeShow', function() {
// 	      alert('beforeShow');
// 	      var dataGridInstance = $("#componentsGrid").dxDataGrid("instance");
// 	      dataGridInstance.pageSize(20);
// 	      dataGridInstance.refresh();
// 	      dataGridInstance.repaint();
// 	    }) 
// 	    .bind('afterShow', function() {
// 	      alert('afterShow');
// 	      var dataGridInstance = $("#componentsGrid").dxDataGrid("instance");
// 	      dataGridInstance.pageSize(5);
// 	      dataGridInstance.refresh();
// 	      dataGridInstance.repaint();
// 	    })
// 	    .show(1000, function() {
// 	      alert('in show callback');
// 	      var dataGridInstance = $("#componentsGrid").dxDataGrid("instance");
// 	      dataGridInstance.pageSize(10);
// 	      dataGridInstance.refresh();
// 	      dataGridInstance.repaint();
// 	    })
// 	    .show();
// 	});
 </script> 
	<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<!-- <sec:csrfMetaTags/> -->
<div class="tabs widget">
					<ul class="nav nav-tabs widget">
						<li class="active"><a data-toggle="tab" href="#profile-tab"> Full View <span class="menu-active"><i class="fa fa-caret-up"></i></span></a></li>
						<li class=""><a data-toggle="tab" href="#member-tab">	Detailed View <span class="menu-active"><i class="fa fa-caret-up"></i></span></a></li>
					</ul>
					
					<div class="tab-content">
						<div id="profile-tab" class="tab-pane active">

								<div id="gridContainer"></div>
						</div>
					

						<div id="member-tab" class="tab-pane">
							
							<div class = "row">	
								<div id="componentsGrid"></div>
								<div id="chemicalsGrid"></div>
								<div id="gridResults"></div>
							</div>
							<script type="text/javascript" src="/database_project/scripts/js/deletePost.js"></script>
 							<button class="btn btn-primary" onclick="var data = {'orderid[]' : currentOrders , 'componentid[]' : currentComponents }; post('/database_project/export/byparams', data, 'GET', '${_csrf.parameterName}', '${_csrf.token}')" title="Export"><span class="glyphicon glyphicon-export"></span></button>
						</div>
					
					</div>
			</div>	
			
			 <script>
			$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
  var target = $(e.target).attr("href") // activated tab
  if (target =="#member-tab" && !gridRefreshed){
	      var dataGridInstance = $("#componentsGrid").dxDataGrid("instance");
	      dataGridInstance.refresh();
	      
	      dataGridInstance = $("#chemicalsGrid").dxDataGrid("instance");
	      dataGridInstance.refresh();
	      
	      dataGridInstance = $("#gridResults").dxDataGrid("instance");
	      dataGridInstance.refresh();
	      
	      gridRefreshed = true;

  }
});
 </script>
 
 
<jsp:include page="../fragments/footer.jsp" />


</html>