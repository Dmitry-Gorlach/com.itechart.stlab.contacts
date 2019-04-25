<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.03.2019
  Time: 14:27
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
  <a class="navbar-brand" href="#"><fmt:message key = "locale.title.main"/></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">

    <div class="navbar navbar-expand-sm bg-dark navbar-dark">
      <form class="form-inline" action="controller" method="POST">
        <input class="form-control mr-sm-2" type="hidden" name="command" value="language" >
        <input class="form-control mr-sm-2" type="hidden" name="locale" value="ru_RU"/>
        <button class="btn btn-link" name="ru"><img src="/jsp/images/ru.gif" style="vertical-align:middle">
          <fmt:message key="button.locale.ru"/>
        </button>
      </form>
    </div>

    <div class="navbar navbar-expand-sm bg-dark navbar-dark">
      <form class="form-inline" action="controller" method="POST">
        <input class="form-control mr-sm-2" type="hidden" name="command" value="language" >
        <input class="form-control mr-sm-2" type="hidden" name="locale" value="en-US"/>
        <button class="btn btn-link" name="en"><img src="/jsp/images/us.gif" style="vertical-align:middle">
          <fmt:message key="button.locale.en"/>
        </button>
      </form>
    </div>
  </div>
</nav>


<div class="container-fluid">
  <div class="row justify-content-center">
    <div class="col">
      <br><h4 class="text-left"><fmt:message key="locale.title.contacts"/></h4>
      <form method="get" action="${root}/controller" name="form1" >
        <input type="hidden" name="command" value="get_all_contacts">
        <button class="btn btn-sm btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="contact.get.all"/>
        </button>
      </form>
    </div>
  </div>
  <h2>${requestScope.message}</h2>
  </br>
  <table>
    <tr>
      <td><span class="infoMessage">${infoMessage}</span></td>
      <td><span class="emailSuccess">${emailSuccess}</span></td>
    </tr>

  </table>
</div>



<script src="/js/contact.js"></script>

</body>

</html>
