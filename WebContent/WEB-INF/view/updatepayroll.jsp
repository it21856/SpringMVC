<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<link data-require="bootstrap-css@*" data-semver="3.0.0" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	 <script data-require="angular.js@*" data-semver="1.2.0-rc3-nonmin" src="http://code.angularjs.org/1.2.0-rc.3/angular.js"></script>
	    
	<title>Payroll Check</title>
</head>

<body>
	<h1>Update Section</h1>

<form:form action="updatesal" method="post">
<c:if test="${not empty lists}">
	<table border="1" cellpadding="5">
		<caption>
			<h2>Details List</h2>
		</caption>
		<tr>
			<th>Username</th>
			<th>Days</th>
			<th>Type</th>
			<th>Status</th>
			<th>Salary</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${lists}" var="upsal">
			<tr>
				<td><c:out value="${upsal.getUser().getUsername()}" /></td>
				<td><c:out value="${upsal.getDays()}" /></td>
				<td><c:out value="${upsal.getType()}" /></td>
				<td><c:out value="${upsal.getState()}" /></td>
				<td><c:out value="${upsal.getUser().getUserDetails().getSalary()}" /></td>
				<td><button class="locateuser" style="font-size:16px"><i class="glyphicon glyphicon-pencil" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'"></i></button></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<input type="hidden" name="username" id="username">
<input type="hidden" name="sal" id="sal">
</form:form>
<c:if test="${empty lists}">
	<p>No requests found</p>
</c:if>
</body>

<script type="text/javascript">

$(".locateuser").click(function() {
	var currentRow = $(this).closest("tr")[0]; 
    var cells = currentRow.cells;
    var uname= cells[0].textContent;
    $("#username").val(uname);
    var entry1 = prompt("Please enter the updated salary:", "Enter here");
	
    
    $("#sal").val(entry1);
	
});

</script>
</html>