<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="../layout/header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registration Page</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<style type="text/css">
body {
	background: activecaption;
	background-image: url("images/images.jpg");
}

div {	
	float: left;
	padding-top:20px;
	width:70%;
	margin-left: 35px;
	border-style : double;
	height: 360px;
	background: #fff;
	-moz-border-radius: 10px;
	-khtml-border-radius: 10px;
	-webkit-border-radius: 20px;
	-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-khtml-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-webkit-box-shadow: 5px 0px 20px rgba(0, 0, 0, 3);
}


</style>
</head>
<body>
	<center>
		<h3 >User Registration</h3>
	</center>

	

		<form:form action="register" commandName="registration" method="post"
			name="registerForm">
			<div id="userDetailsDiv" class="box">
			<table align="center">
				<tr>
					<td>User ID :</td>
					<td><form:input path="userId" /></td>
					
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:password path="password" /></td>
					
				</tr>
				<tr>
					<td>User Name:</td>
					<td><form:input path="userName" /></td>
				</tr>

				<tr>
					<td>Email ID:</td>
					<td><form:input path="emailID" /></td>
				</tr>
				<tr>
					<th>Address</th>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td>House No:</td>
					<td><form:input path="houseNumber" /></td>
				</tr>
				<tr>
					<td>Street :</td>
					<td><form:input path="street" /></td>
				</tr>
				<tr>
					<td>City :</td>
					<td><form:input path="city" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><form:select path="state">
							<form:options items="${statesList}" itemValue="stateName"
								itemLabel="stateName" />
						</form:select></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><input type="text" name="country" value="IN" readonly="readonly" >  </td>
				</tr>

				<tr>
					<td><input type="submit" value="Submit"></td>

					<td><input type="reset" value="Reset"
						onclick="document.forms['registerForm'].reset();"></td>
				</tr>

			</table>
			</div>
	<table>
	 <spring:hasBindErrors  name="registration">
			<c:if test="${errors.errorCount gt 0}">
				<center><h4>The error list :</h4>
				<font color="red"> <c:forEach items="${errors.allErrors}"
						var="error">
						<spring:message code="${error.code}"
							arguments="${error.arguments}" text="${error.defaultMessage}" />
						<br />
					</c:forEach>
				</font></center>
			</c:if>
		</spring:hasBindErrors>
		</table>
	</form:form>
	
	
</body>
</html>