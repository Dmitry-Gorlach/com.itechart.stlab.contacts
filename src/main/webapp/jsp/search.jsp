<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.03.2019
  Time: 17:44
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

    <!-- Brand -->
    <a class="navbar-brand" href="<c:url value="/index.jsp"/>"><fmt:message key="locale.title.main"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/index.jsp"/>"><fmt:message key="locale.title.contacts"/></a>
            </li>
        </ul>


            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col">
            <br><h4 class="text-left"><fmt:message key="locale.title.search"/></h4>

                <c:forEach var="contact" items="${contacts}" >
                <tbody class="text-left">
                    <tr>
                        <td><input type="checkbox" name="selected_contacts" value="${contact.id}"/></td>
                        <td scope="row"><a href="<c:url value="/jsp/contact.jsp?id=${contact.id}" />">${contact.firstName} ${contact.lastname} ${contact.surname}</td>
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
                        <td><a href="<c:url value="/jsp/contact.jsp?id=${contact.id}" />" class="fa fa-pencil fa-lg"></a></td>



                    </tr>
                </c:forEach>

                </tbody>
                <form method="get" action="${root}/controller">
                    <input type="hidden" name="command" value="get_contact_by_firstname">
                    <input type="text" class="form-control" id="first_name" name="first_name"
                           placeholder="<fmt:message key="contact.firstname"/>">
                    <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.name"/>
                    </button>
                </form>
            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_lastname">
                <input type="text" class="form-control" id="last_name" name="last_name"
                       placeholder="<fmt:message key="contact.lastname"/>">
                <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.lastname"/>
                </button>
            </form>
            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_surname">
                <input type="text" class="form-control" id="surname" name="surname"
                       placeholder="<fmt:message key="contact.surname"/>">
                <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.surname"/>
                </button>
            </form>

            </form>

            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_gender">
                <div class="form-group">
                    <select class="form-control" name="gender" id="gender" >
                        <option name="empty"></option>
                        <option name="MALE" value="MALE">MALE</option>
                        <option name ="FEMALE" value="FEMALE">FEMALE</option>
                    </select>
                    <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.gender"/>
                    </button>
                </div>
            </form>
            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_marital_status">
                <div class="form-group">
                    <select class="form-control" name="marital_status" id="marital_status" >
                        <option name="empty"></option>
                        <option name ="SINGLE" value="SINGLE"><fmt:message key="contact.marital.status.single"/> </option>
                        <option name ="MARRIED" value="MARRIED"><fmt:message key="contact.marital.status.married"/> </option>
                        <option name ="DIVORCE" value="DIVORCE"><fmt:message key="contact.marital.status.divorce"/> </option>
                    </select>
                    <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.marital.status"/>
                    </button>
                </div>
            </form>
            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_nationality">
                <input type="text" class="form-control" id="nationality" name="nationality"
                       placeholder="<fmt:message key="contact.nationality"/>">
                <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.nationality"/>
                </button>
            </form>
            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_birthday_asc">
                <input type="date" class="form-control" id="birthday_asc" name="birthday">
                <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.birthday.asc"/>
                </button>
            </form>
            <form method="get" action="${root}/controller">
                <input type="hidden" name="command" value="get_contact_by_birthday_desc">
                <input type="date" class="form-control" id="birthday_desc" name="birthday">
                <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="btn.search.birthday.desc"/>
                </button>
            </form>
            </table>

        </div>
    </div>
</div>



</body>
</html>