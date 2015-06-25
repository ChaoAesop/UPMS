<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile Management</title>
<style type="text/css">
body {
	background: activecaption;
	background-image: url("images/images.jpg");
}

tr {
	padding-left: 20px;
}
.bubble {
	clear: both;
	margin: 0px auto;
	width: 550px;
	height: 320px;
	border-style : groove;
	background: #fff;
	-moz-border-radius: 10px;
	-khtml-border-radius: 10px;
	-webkit-border-radius: 10px;
	-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-khtml-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-webkit-box-shadow: 5px 0px 20px rgba(0, 0, 0, 3);
	position: relative;
}
</style>
<script type="text/javascript">
	function setGenerateOTPUserID() {
		document.forms['loginForm'].action = "generateOTP";
		document.forms['loginForm'].method = "get";
		document.forms['loginForm'].submit();
		//document.getElementById('userId').value = document.getElementById('name').value;
	}

	function submitLoginForm() {
		document.forms['loginForm'].action = "login";
		document.forms['loginForm'].method = "post";
		document.forms['loginForm'].submit();
	}
</script>
</head>
<body >

	<form:form action="login" commandName="loginForm" name="loginForm">
		<table align="center" class="bubble">
			<tr>
				<td>User ID :</td>
				<td><form:input path="name" /></td>
				<td></td>
			</tr>
			<tr>
				<td>OTP :</td>
				<td><form:input path="OTPString" /></td>
			</tr>

			<tr>
				<td>Password :</td>
				<td><form:password path="password" /></td>

			</tr>

			<tr>
				<td><a href="" onclick="setGenerateOTPUserID();return false;">Generate
						OTP -></a></td>
			</tr>
		
			<tr><td></td>
				<td style="width: 8px;"><input type="button" value="Login"
					onclick="submitLoginForm();return false;"></td>

				<td ><input type="button" value="Register"
					onclick="document.forms['registerForm'].submit(); return false;"></td>
			</tr>
		</table>
		<center>
		<table>
			<tbody>
				<tr>
					<td><form:errors path="name" cssStyle="color: #ff0000;" /></td>
				</tr>
				<tr>
					<td><form:errors path="password" cssStyle="color: #ff0000;" /></td>
				</tr>
				<tr>
					<td><form:errors path="OTPString" cssStyle="color: #ff0000;" /></td>
				</tr>
			</tbody>
		</table>
		</center>
	</form:form>

	<form action="showRegisterPage" name="registerForm" method="get">
	</form>

</body>
</html>