<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	Request from ${pageContext.errorData.requestURI } is failed
	<br/>
	Servlet name: ${pageContext.errorData.servletName }
	<br/>
	Status code: ${pageContext.errorData.statusCode }
	<br/>
	Exception: ${pageContext.exception }
	<br/>


</body>
</html>