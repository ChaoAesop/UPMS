<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"  href="css/styles.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Address</title>
<script type="text/javascript">
function goBackToProfileUpdatePage(){
	document.forms['addNewAddress'].action = "backToProfilePage";
	document.forms['addNewAddress'].submit();
}
</script>
<style type="text/css">
body {
	background: activecaption;
		background-image: url("images/images.jpg");
}

div {
	float: left;
	padding-left: 30px; width : 50%;
	height: 360px;
	background: #fff;
	border-style : double;
	-moz-border-radius: 10px;
	-khtml-border-radius: 10px;
	-webkit-border-radius: 10px;
	-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-khtml-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-webkit-box-shadow: 5px 0px 20px rgba(0, 0, 0, 3);
	width: 50%
}
</style>
</head>
<body>
	<center>
		<h3>Add New Address</h3>
	</center>
	<div >
		<form:form action="addAddressToProfile" commandName="addressForm"
			name="addNewAddress">
			<br>
			<h3>Address</h3>
			<table>
				<tr>
					<td>House No:</td>
					<td><form:input path="houseNo" /></td>
				</tr>
				<tr>
					<td>Street:</td>
					<td><form:input path="street" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><form:input path="city" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><form:select path="selectedState">
							<form:options items="${statesList}" itemValue="stateName"
								itemLabel="stateName" />
						</form:select></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><form:input path="country" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td>
					<input type="submit" value="Submit" /> 
					<input type="reset" value="Clear" /> 
					<a href="" onclick="goBackToProfileUpdatePage();return false;">Go Back</a>
					</td>
				</tr>
			</table>
			<div id="hiddenDiv" style="display: none;">
				<form:hidden path="profileID" />
			</div>
		</form:form>
	</div>

	<div style="width: 30%; margin-left: 20px;" id="errorDiv" class="box">
		<spring:hasBindErrors name="addressForm">
			<c:if test="${errors.errorCount gt 0}">
				<center>
					<h4>The error list :</h4>
					<font color="red"> <c:forEach items="${errors.allErrors}"
							var="error">
							<spring:message code="${error.code}"
								arguments="${error.arguments}" text="${error.defaultMessage}" />
							<br />
						</c:forEach>
					</font>
				</center>
			</c:if>
		</spring:hasBindErrors>
	</div>
</body>
</html>