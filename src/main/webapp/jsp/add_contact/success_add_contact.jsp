<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="locale" value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Locale"/>

<!DOCTYPE html>
<html lang="${locale}">
<head>
    <c:import url="/jsp/common/header.jsp"/>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="<c:url value="/index.jsp"/>"><fmt:message key="locale.title.main"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    </div>
</nav>

<div class="container-fluid">
        </br>
        <p ></p><h3 class="successMessage">${successMessage}</h3></p>
        <p> <a class="navbar-brand" href="<c:url value="/index.jsp"/>"><fmt:message key="locale.return.contacts"/></a></p>
        <p> <a class="navbar-brand" href="<c:url value="/jsp/add_contact/add_contact.jsp"/>"><fmt:message key="locale.add.contact.extra"/></a></p>
</div>

</body>
</html>
