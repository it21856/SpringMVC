<html>
<head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link data-require="bootstrap-css@*" data-semver="3.0.0" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h2>Edit user: ${user.getUsername()}</h2>
	<form:form action="${user.getUsername()}" method="post">
		<div id="passworddiv">
			<p>Change password?</p>
			<input type="text" id="password" name="password"size="75"
				value="${user.getPassword()}"
				placeholder="Change ${user.getUsername()} password?" />
				
		</div>
		<div id="enableddiv">
			<p>Enabled?</p>
			<input type="radio" id="Yes" name="enabled" value="1" checked /> <label
				for="Yes">Yes</label><br> <input type="radio" id="No"
				name="enabled" value="0" /> <label for="No">No</label><br>
		</div>
		<div id="authoritiesdiv">
			<p>Authorities</p>
			<ul class="authoritieslist">
				<c:choose>
					<c:when test="${user.containsAuthority('ROLE_PAYROLL')}">
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_PAYROLL" checked />Payroll Department</li>
					</c:when>
					<c:otherwise>
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_PAYROLL" />Payroll Department</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${user.containsAuthority('ROLE_MANAGER')}">
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_MANAGER" checked />Manager</li>
					</c:when>
					<c:otherwise>
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_MANAGER" />Manager</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${user.containsAuthority('ROLE_SUPERVISOR')}">
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_SUPERVISOR" checked />Supervisor</li>
					</c:when>
					<c:otherwise>
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_SUPERVISOR" />Supervisor</li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${user.containsAuthority('ROLE_USER')}">
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_USER" checked />User</li>
					</c:when>
					<c:otherwise>
						<li><input type="checkbox" id="role1" name="roles"
							value="ROLE_USER" />User</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<br>
		<input type="submit" value="Update" id="update" />
	</form:form>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#update').click(function() {
			checked = $("input[type=checkbox]:checked").length;

			if (!checked) {
				alert("You must check at least one checkbox.");
				return false;
			}

		});
	});
</script>
</html>
