<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.04.2019
  Time: 19:25
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
    <title><fmt:message key="locale.title.main"/></title>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <!-- Brand -->
    <a class="navbar-brand" href="<c:url value="/index.jsp"/>"><fmt:message key = "locale.title.main"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/jsp/search.jsp"/>"><fmt:message key="locale.title.search"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/jsp/add_contact/add_contact.jsp"/>"><fmt:message key="locale.add.contact"/></a>
            </li>
        </ul>
</nav>
<h2 class="page-name">Отправка почты</h2>
<div class="contact-form">
    <form method="post" action="/controller" name="email_form" accept-charset="ISO-8859-1">
        <input type="hidden" name="command" value="send_email">
        <header><h5>Получатели:</h5></header>
        <c:if test="${empty contacts}">
            <p class="infoMessage">Не выбрано ни одного получалеля.</p>
            <p class="infoMessage">${infoMessage}</p>
        </c:if>
        <c:forEach var="contact" items="${contacts}">
            <label class="input-label">
                    ${contact.get().getFirstName()} ${contact.get().getLastname()} ${contact.get().getSurname()}
                <input class="input-field" type="text" disabled value="${contact.get().getEmail()}">
            </label></br>
            <input type="hidden" value="${contact.get().getEmail()}" name="emails">
            <input type="hidden" value="${contact.get().getId()}" name="contactId">
        </c:forEach>

        <div class="field">
            <div class="field-label">
                <label for="subject">Тема </label>
            </div>
            <div class="field-input" >
                <input type="text" name="subject" id="subject" required title="Не может быть пустым"  style="width: 30%">
                <div class="field-message">
                    Can't be empty
                </div>
            </div>
        </div>

        <div class="field">
            <div class="field-label">
                <label>Шаблон</label>
            </div>
            <div class="field-input">
                <select name="template" id="template">
                    <option name="none" value="none">Выберите шаблон</option>
                    <option name="Hello" value="Hello">Приветствие</option>
                    <option name="Invitation" value="Invitation">Приглашение</option>
                </select>
            </div>
        </div>

        <div class="field">
            <div class="field-label">
                <label for="message">Сообщение </label>
            </div>
            <div class="field-input" >
                <textarea name="message" id="message" rows="10" placeholder="Введите сообщение" style="width: 30%">${stringTemplate}</textarea>
            </div>
            <input class="centered block apply-button" type="submit" id="send-email-button" value="Отправить">
        </div>
    </form>
</div>


</body>

</html>
