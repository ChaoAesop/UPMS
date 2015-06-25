<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
<style type="text/css">
body{
background-image: url("images/images.jpg");
}
</style>
<script type="text/javascript">
function load() {
    <%
    session.invalidate();
    %>
    		
  }
  window.onload = load;
</script>


</head>
<body>
 <h1>Error Page</h1>
  <!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
    </c:forEach>
    -->
    <p>Application has encountered an error. </p>
    <form:form action="">
      	<c:url value="/" var="url"/>
 <a href="<c:out value='${url}'/>">Home</a>
    </form:form>
</body>
</html>