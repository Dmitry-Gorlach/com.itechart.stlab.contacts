<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.03.2019
  Time: 8:30
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <!-- Brand -->
    <a class="navbar-brand" href="<c:url value="/index.jsp"/>"><fmt:message key="locale.title.main"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
<%--    <div class="collapse navbar-collapse" id="navbarNavDropdown">--%>
<%--        <ul class="navbar-nav">--%>
<%--            <li class="nav-item active">--%>
<%--                <a class="nav-link" href="<c:url value="/jsp/contact-list.jsp"/>"><fmt:message key="locale.title.contacts"/></a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </div>--%>
    </div>
</nav>
<div class="container-fluid">
    <div class="row justify-content-md-center">
        <form class="form-control-sm" method="post" action="${root}/controller">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display='none';"></span>
                ${messageAddContactError}
            </div>
            <input type="hidden" name="command" value="add_contact">
            <div class="form-group">
                <p>Создание контакта: </p>
                <label for="first_name"><fmt:message key="add.contact.firstname"/></label>
                <input type="text" name="first_name" class="form-control" id="first_name"
                       aria-describedby="firstNameHelp"
                       placeholder="<fmt:message key="add.contact.firstname.placeholder"/>"
                       pattern="[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}"
                       required>
                <small id="firstNameHelp" class="form-text text-muted"><fmt:message key="add.contact.firstname.small"/></small>
            </div>
            <div class="form-group">
                <label for="surname">Surname </label>
                <input type="text" name="surname" class="form-control" id="surname"
                       aria-describedby="surnameNameHelp"
                       placeholder="<fmt:message key="add.contact.surname.placeholder"/>"
                       pattern="[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}"
                       required/>
            </div>
            <div class="form-group">
                <label for="last_name">Lastname </label>
                <input type="text" name="last_name" class="form-control" id="last_name"
                       aria-describedby="lastNameHelp"
                       placeholder="<fmt:message key="add.contact.lastname.placeholder"/>"
                       pattern="[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}"/>
            </div>
            <div class="form-group">
                <label for="birthday">Birthday </label>
                <input type="date" name="birthday" class="form-control" id="birthday" />
                <div class="col-sm-9">
                    <small id="birthdayHelp" class="text-danger">
                        ${messageAddContactError}
                    </small>
                </div>
            </div>
            <div class="form-group">
                <label for="gender" >Gender</label>
                <select class="form-control" name="gender" id="gender" required>
                    <option name="empty"></option>
                    <option name="MALE" value="MALE">MALE</option>
                    <option name ="FEMALE" value="FEMALE">FEMALE</option>
                </select>
            </div>
                <div class="form-group">
                    <label for="nationality">Nationality </label>
                    <input type="text" name="nationality" class="form-control" id="nationality"/>
                </div>
            <div class="form-group">
                <label for="gender"><fmt:message key="contact.marital.status"/> </label>
                <select class="form-control" name="marital_status" id="marital_status" required>
                    <option name="empty"></option>
                    <option name="SINGLE" value="SINGLE"><fmt:message key="contact.marital.status.single"/> </option>
                    <option name ="MARRIED" value="MARRIED"><fmt:message key="contact.marital.status.married"/> </option>
                    <option name ="DIVORCED" value="DIVORCED"><fmt:message key="contact.marital.status.divorce"/> </option>
                </select>
            </div>
                <div class="form-group">
                <label for="company">Company </label>
                <input type="text" name="company" class="form-control" id="company"/>
                </div>
                <div class="form-group">
                    <label for="website">Website </label>
                    <input type="text" name="website" class="form-control" id="website"/>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" class="form-control" id="email"
                           aria-describedby="emailHelp"
                           placeholder="<fmt:message key="add.email.placeholder"/>"
                           pattern="[a-z0-9._%+-]{1,20}@[a-z0-9.-]{1,16}\.[a-z]{2,3}$" required>
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="add.email.small"/></small>
                </div>
            <div class="form-group">
                <label for="country">Country</label>
                <input type="text" name="country" class="form-control" id="country"/>
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" name="city" class="form-control" id="city"/>
            </div>
            <div class="form-group">
                <label for="street">Street</label>
                <input type="text" name="street" class="form-control" id="street"/>
            </div>
            <div class="form-group">
                <label for="house">House</label>
                <input type="text" name="house" class="form-control" id="house"/>
            </div>
            <div class="form-group">
                <label for="flat">Flat</label>
                <input type="text" name="flat" class="form-control" id="flat"/>
            </div>
            <div class="form-group">
                <label for="postcode">Postcode</label>
                <input type="text" name="postcode" class="form-control" id="postcode"/>
            </div>

            <div class="row justify-content-md-center">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message
                        key="add.contact.btn"/></button>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="this.form.reset();"><fmt:message
                        key="add.contact.clean"/></button>
        </form>
            </div>
            </div>
        </form>
    </div>
</div>


</div>
</body>
</html>