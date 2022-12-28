<head>
	<link data-require="bootstrap-css@*" data-semver="3.0.0" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
     <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
</head>
<body>
<h2>User Add Form</h2>
<form:form action="add" method="post">
<div class="row" id="usernamerow">
        <label for="username">Username : </label>
<input type="text" id=username name="username" placeholder="What's the name?" required="required" pattern="[A-Za-z0-9]{1,20}"/>
</div>
<div class="row" id="passwordrow">
<label for="Password">Password : </label>
<input type="password" id=password name="password" placeholder="What's the password?" required="required" pattern="[A-Za-z0-9]{1,20}"/>
</div>
<div id="enableddiv">
<p>Enabled?</p>
<input type="radio" id="Yes" name="enabled" value="1" checked/>
  <label for="Yes">Yes</label><br>
<input type="radio" id="No" name="enabled" value="0" />
	<label for="No">No</label><br>
</div>
<div id="authoritiesdiv">
<ul class="authoritieslist">
    <li><input type="checkbox" name="roles" value="ROLE_PAYROLL" />Payroll Department</li>
    <li><input type="checkbox" name="roles"  value="ROLE_MANAGER" />Manager</li>
    <li><input type="checkbox" name="roles"  value="ROLE_SUPERVISOR"/>Supervisor </li>
    <li><input type="checkbox" name="roles"  value="ROLE_USER" checked/>User </li>
</ul>
</div>
 <input type="submit" id="checkBtn" value="Enter" />
</form:form>
</body>

<script type="text/javascript">
$(document).ready(function () {
    $('#checkBtn').click(function() {
      checked = $("input[type=checkbox]:checked").length;

      if(!checked) {
        alert("You must check at least one checkbox.");
        return false;
      }

    });
});

</script>