<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.04.2019
  Time: 7:18
  To change this template use File | Settings | File Templates.
--%>
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

<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="<c:url value="/jsp/search.jsp"/>"><fmt:message key="locale.title.search"/></a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="<c:url value="/jsp/add_contact.jsp"/>"><fmt:message key="locale.add.contact"/></a>--%>
<%--            </li>--%>
<%--                  <li class="nav-item">--%>
<%--                    <a class="nav-link" href="<c:url value="${root}/controller?command=send_email&id=${contact.id}"/>"><fmt:message key="locale.edit.contact"/></a>--%>
<%--                  </li>--%>

        </ul>
                <div class="navbar navbar-expand-sm bg-dark navbar-dark">

                </div>

                <div class="navbar navbar-expand-sm bg-dark navbar-dark">

                </div>

</nav>

<p style="color: red">${infoMessage}</p>
</br>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col">
            <table class="table table-hover table-bordered table-responsive-md" id="contactId" >
                <form method="get" action="/controller">
                    <input type="hidden" name="command" value="open_search_contact_page">
                    <button class="btn btn-sm btn-outline-secondary my-2 my-sm-0"><fmt:message key="locale.title.search"/></button>
                </form>
                <form method="get" action="/controller">
                    <input type="hidden" name="command" value="open_add_contact_page">
                    <button class="btn btn-sm btn-outline-success my-2 my-sm-0"><fmt:message key="locale.add.contact"/></button>
                </form>
                <form method="get" action="/controller" name="sendEmail" id="sendEmailForm">
                    <input type="hidden" name="command" value="choose_contact_to_email">
                    <button class="btn btn-sm btn-outline-info my-2 my-sm-0" name="btnSend" type="submit" onsubmit="sendEmailSelectedContact()"><fmt:message key="locale.send.email"/></button>
                    <input type="button" id="remove" value=" <fmt:message key="locale.delete.contact"/>" class="btn btn-sm btn-outline-danger my-2 my-sm-0 "
                           onclick="checkBoxValidation(); deleteRow('contactId')">
                    <thead class="thead-light text-uppercase text-md-center">
                    <tr>
                        <th class="control-col"></th>
                        <th scope="col"><fmt:message key="contact.fullname"/> </th>
                        <th scope="col"><fmt:message key="contact.birthday"/></th>
                        <th scope="col"><fmt:message key="contact.address"/></th>
                        <th scope="col"><fmt:message key="contact.company"/></th>
                        <th class="control-col"></th>
                    </tr>
                    </thead>
                    <tbody class="text-left">


                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><input type="checkbox" id="selected_contacts" name="selected_contacts" value="${contact.id}"/></td>
                            <td scope="row"><a href="<c:url value="${root}/controller?command=get_contact_by_id&id=${contact.id}" />">${contact.firstName} ${contact.lastname} ${contact.surname}</td>
                            <td scope="row">${contact.birthday}</td>
                            <td scope="row">
                                <c:if test="${contact.country != null}"><c:out value="${contact.country}"/>,</c:if>
                                <c:if test="${contact.city != null}"> <c:out value="${contact.city}" />,</c:if>
                                <c:if test="${contact.street != null}"> <c:out value="${contact.street}" />,</c:if>
                                <c:if test="${contact.house != null}"> <c:out value="${contact.house}" /> -</c:if>
                                <c:if test="${contact.flat != null}"> <c:out value="${contact.flat}" />,</c:if>
                                <c:if test="${contact.postcode != null}"> <c:out value="${contact.postcode}" /></c:if>
                            </td>
                            <td scope="row">${contact.company}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>
    </div>
    <h2>${requestScope.message}</h2>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="/js/contact.js"></script>
<script src="/js/send-email.js"></script>

</body>

</html>

