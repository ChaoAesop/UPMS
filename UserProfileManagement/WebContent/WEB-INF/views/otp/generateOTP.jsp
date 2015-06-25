<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <jsp:include page="../layout/header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile Management - Generate OTP</title>
<link  rel="stylesheet" type="text/css" href="css/styles.css">
<style type="text/css">
body {
	background: activecaption;
	background-image: url("images/images.jpg");
}

div {	
	float: left;
	padding-top:20px;
	width:70%;
	margin-left: 70px;
	border-style : double;
	height: 123px;
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

<div class="box">
<center><b>Generate OTP</b></center>
<table align="center" > 
<form:form commandName="generateOTP" action="backFromGenerateOTP" name="generateOTPForm" >
<tr><td>
<form:input path="userId"/>
</td>
</tr>

<tr><td>
<form:input path="OTPString"/>
</td>
</tr>
<tr>
<td><a href="" onclick="document.forms['generateOTPForm'].submit(); return false;">Go Back</a> </td>
</tr>
</form:form>
</table>
 </div>
 
</body>
</html>