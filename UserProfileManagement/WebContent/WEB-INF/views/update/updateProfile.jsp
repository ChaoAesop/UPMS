<%@page import="com.userprofile.vo.ProfileVO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../layout/header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body {
	background: activecaption;
	background-image: url("images/images.jpg");
}

div {	
	float: left;
	padding-top:20px;
	width:70%;
	margin-left: 20px;
	height: 360px;
	border-style : double;
	background: #fff;
	-moz-border-radius: 10px;
	-khtml-border-radius: 10px;
	-webkit-border-radius: 20px;
	-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-khtml-box-shadow: 0px 0px 8px rgba(0, 0, 0, 3);
	-webkit-box-shadow: 5px 0px 8px rgba(0, 0, 0, 2);
}
</style>
</head>
<body>
<center>
<h3>Update Your Profile</h3></center>




<form:form action=""  method="get" commandName="profileVO" name="updateForm">
<div id="profileDiv" class="box">
	<table align="center">
			<tr>
				<td>User ID :</td>
				<td><form:input path="userID" /></td>
			</tr>
			<tr>
				<td>Email ID :</td>
				<td><form:input path="emailID" /></td>
			</tr>
			<tr>
			<th>Address</th>
			</tr>
			<tr>
				<td>Address Number :</td>
				<td><form:select path="selectedAddress" onchange="onChangeSelectedAddress();">
					  <c:forEach items="${addressMap}" var="entry">
					     <form:option value="${entry.key}">
					       ${entry.key}</form:option>
					    </c:forEach>
				</form:select>
				</td>
			</tr>
			<tr>
				<td>House Number :</td>
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
				<td>State :</td>
				<td><form:select path="selectedState">
					<form:options items="${statesList}" itemValue="stateName"
								itemLabel="stateName" />
				</form:select></td>
			</tr>
			<tr>
				<td>Country :</td>
				<td><form:input path="country" readonly="true"/></td>
			</tr>
			<tr>
				<td ><input type="button" value="Add New Address" onclick="addNewAddress();"></td>
				<td ><input type="button" value="Delete Address" onclick="deleteSelectedAddress();"></td>
			</tr>
			<tr>
				<td ><input type="button" value="Update" onclick="updateAddress();"></td>
				<td ><input type="button" value="Logout" onclick="logoutUser();"></td>
			</tr>
		</table>
</div>
<table>
	 <spring:hasBindErrors  name="profileVO">
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

<script type="text/javascript">
function addNewAddress(){
	document.forms['updateForm'].action ="addNewAddress";
	document.forms['updateForm'].submit();
}

function updateAddress(){
	document.forms['updateForm'].method="get";
	document.forms['updateForm'].action ="updateChangedAddress";
	document.forms['updateForm'].submit(); 
}

function deleteSelectedAddress(){
	document.forms['updateForm'].method="get";
	document.forms['updateForm'].action ="deleteSelectedAddress";
	document.forms['updateForm'].submit(); 
}

function logoutUser(){
	document.forms['updateForm'].method="post";
	document.forms['updateForm'].action ="logout";
	document.forms['updateForm'].submit(); 
}

</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">

function onChangeSelectedAddress(){
	<% session.setAttribute("addressMap", request.getAttribute("addressMap")); %>
	var selectedAddress = document.getElementById("selectedAddress").value;
	var userID = document.getElementById("userID").value;
	$.ajax({
		url : 'getChangedAddress',
		method : 'post',
		ContentType : 'json',
		data : {
			'userID' : userID,
			'selectedAddress' : selectedAddress
		},

		success : function(responseData) {
		        var o=JSON.parse(responseData);
		        document.getElementById("houseNumber").value =o.house_number ;
		        document.getElementById("street").value =o.street ;
		        document.getElementById("city").value =o.city ;
		        document.getElementById("country").value =o.country;
		        document.getElementById("selectedState").value =o.state;
		}
	});
}

</script>
</body>
</html>