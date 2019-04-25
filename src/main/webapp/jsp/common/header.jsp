<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<c:set var="locale" value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Locale"/>

<!DOCTYPE html>
<html lang="${locale}">
<head>

 <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myStyle.css"/>



</head>
<body>

</body>
