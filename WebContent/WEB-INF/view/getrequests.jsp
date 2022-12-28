<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<link data-require="bootstrap-css@*" data-semver="3.0.0" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	 <script data-require="angular.js@*" data-semver="1.2.0-rc3-nonmin" src="http://code.angularjs.org/1.2.0-rc.3/angular.js"></script>
	 <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<title>Dayoffs information</title>
</head>

<body>
	<h1>Dayoffs Section</h1>
</body>
<br>
<div id="filterdiv">
<sec:authorize access="hasRole('MANAGER')">
<select id="mySelector">
  <option value="All" selected="selected">All</option>
  <option value="Accepted" >Accepted</option>
  <option value="Declined">Declined</option>
  <option value="Pending">Pending</option>
</select>
</sec:authorize>
</div>

<form:form action="status" method="post">
<c:if test="${not empty reqlist}">
	<table id="table" border="1" cellpadding="5">
		<caption>
			<h2>List of Dayoffs</h2>
		</caption>
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Days</th>
			<th>Type</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Status</th>
			<sec:authorize access="hasRole('SUPERVISOR')">
			<th>Actions</th>
			</sec:authorize>
		</tr>
		<c:forEach items="${reqlist}" var="dayoffs">
		<fmt:formatDate value="${dayoffs.getStartdate()}" pattern="dd/MM/yyyy" var="startdate"/>
		<fmt:formatDate value="${dayoffs.getEnddate()}" pattern="dd/MM/yyyy" var="enddate"/>
			<tr>
				<td><c:out value="${dayoffs.getId()}" /></td>
				<td><c:out value="${dayoffs.getUser().getUsername()}" /></td>
				<td><c:out value="${dayoffs.getDays()}" /></td>
				<td><c:out value="${dayoffs.getType()}" /></td>
				<td><c:out value="${startdate}" /></td>
				<td><c:out value="${enddate}" /></td>
				<td name="Status"><c:out value="${dayoffs.getState()}" /></td>
				<sec:authorize access="hasRole('SUPERVISOR')">
					<td name="buttons">
					<button  name="accept" class="accept" style="font-size:16px"><span class="glyphicon glyphicon-ok" onmouseover="this.style.color='blue'" onmouseout="this.style.color='black'"></span></button>
					<button  name="decline" class="decline" style="font-size:16px" ><span class="glyphicon glyphicon-remove" onmouseover="this.style.color='blue'" onmouseout="this.style.color='black'"></span></button>
					</td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</c:if>
<input type="hidden" name="id" id="id" >
</form:form>
<c:if test="${empty reqlist}">
	<p>No requests found</p>
</c:if>

<script type="text/javascript">

$(".accept").click(function() {
	var currentRow = $(this).closest("tr")[0]; 
    var cells = currentRow.cells;
    var id= cells[0].textContent;
    $("#id").val(id);
	
});

$(".decline").click(function() {
	var currentRow = $(this).closest("tr")[0]; 
    var cells = currentRow.cells;
    var id= cells[0].textContent;
    $("#id").val(id);
    
});

$(document).ready(function($) {
	  $('#mySelector').change(function() {
	    $('table').show();
	    var selection = $(this).val();
	    var dataset = $('#table tbody').find('tr');
	    // show all rows first
	    dataset.show();
	    // filter the rows that should be hidden
	    if(selection!="All"){
	    dataset.filter(function(index, item) {
	      return $(item).find('td[name ="Status"]').text().indexOf(selection) === -1;
	    }).hide();
	    }

	  });
	});

</script>
</html>