<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<link data-require="bootstrap-css@*" data-semver="3.0.0" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script data-require="angular.js@*" data-semver="1.2.0-rc3-nonmin"
	src="http://code.angularjs.org/1.2.0-rc.3/angular.js"></script>
</head>

<body>
	<h1>User Management</h1>
</body>
<c:if test="${not empty lists}">
	<table border="1" cellpadding="5">
		<caption>
			<h2>List of users</h2>
		</caption>
		<tr>
			<th>username</th>
			<th>password</th>
			<th>enabled</th>
			<th>authorities</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${lists}" var="users">
			<tr>
				<td><c:out value="${users.getUsername()}" /></td>
				<td><c:out value="${users.getPassword()}" /></td>
				<td><c:out value="${users.getEnabled()}" /></td>
				<td><c:out value="${users.getAuthorities()}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/admin/edit/${users.getUsername()}">Edit</a>
					&nbsp;&nbsp; <a
					href="${pageContext.request.contextPath}/admin/delete/${users.getUsername()}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${not empty user}">
	<c:set var="user" value="${user}" />
	<table border="1" cellpadding="5">
		<caption>
			<h2>user details</h2>
		</caption>
		<tr>
			<th>username</th>
			<th>password</th>
			<th>enabled</th>
			<th>authorities</th>
		</tr>
		<tr>
			<td><c:out value="${user.getUsername()}" /></td>
			<td><c:out value="${user.getPassword()}" /></td>
			<td><c:out value="${user.getEnabled()}" /></td>
			<td><c:out value="${user.getAuthorities()}" /></td>
			<td><a
				href="${pageContext.request.contextPath}/admin/edit/${user.getUsername()}">Edit</a>
				&nbsp;&nbsp; <a
				href="${pageContext.request.contextPath}/admin/delete/${users.getUsername()}">Delete</a>
			</td>
		</tr>
	</table>
</c:if>

<input type="hidden" name="username" id="username">

<c:if test="${empty lists && empty user}">
	<p>Users not found..</p>
</c:if>

<c:if test="${not empty error}">
		<c:out value="${error}" />
</c:if>


<script type="text/javascript">
	$(".locateuser").click(function() {
		var currentRow = $(this).closest("tr")[0];
		var cells = currentRow.cells;

		var uname = cells[0].textContent;
		$("#username").val(uname);
	});
</script>

</html>
