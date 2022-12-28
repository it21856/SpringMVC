<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<link data-require="bootstrap-css@*" data-semver="3.0.0" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<script data-require="angular.js@*" data-semver="1.2.0-rc3-nonmin" src="http://code.angularjs.org/1.2.0-rc.3/angular.js"></script>
	<title>Check Users</title>
</head>

<body>
	<h1>Assign salary to new users below</h1>
</body>
<form:form action="checksal" method="post">
<c:if test="${not empty lists}">
	<table border="1" cellpadding="5">
		<caption>
			<h2>List of new Users</h2>
		</caption>
		<tr>
			<th>Username</th>
			<th>Salary</th>
			<th>Actions</th>
			
		</tr>
		<c:forEach items="${lists}" var="details">
			<tr>
				<td><c:out value="${details.getUsername()}" /></td>
				<td><c:out value="${details.getSalary()}" /></td>
				<td>
				<button  name="edit" class="edit" style="font-size:16px"><span class="glyphicon glyphicon-pencil" onmouseover="this.style.color='blue'" onmouseout="this.style.color='black'"></span></button>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<input type="hidden" name="username" id="username" >
<input type="hidden" name="entry" id="entry" >
</form:form>
<c:if test="${empty lists}">
	<p><h4>No new users found</h4></p>
</c:if>

<script type="text/javascript">

$(".edit").click(function() {
	var currentRow = $(this).closest("tr")[0]; 
    var cells = currentRow.cells;
    var uname= cells[0].textContent;
    $("#username").val(uname);
    var entry1 = prompt("Please enter the users salary:", "Enter here");
    
    $("#entry").val(entry1);
	
});


</script>
</html>