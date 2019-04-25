<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.03.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="locale" value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Locale"/>
<c:set var="contactImagePath" value="${pageContext.request.contextPath}/jsp/images/${not empty contact.get().getPhoto() ? contact.get().getPhoto() : 'avatar.jpg'}" />
<!DOCTYPE html>
<html lang="${locale}">
<head>

    <c:import url="/jsp/common/header.jsp"/>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

    <!-- Brand -->
    <a class="navbar-brand" href="<c:url value="/jsp/contact-list.jsp"/>"><fmt:message key="locale.title.main"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/jsp/add_contact/add_contact.jsp"/>"><fmt:message key="locale.title.contacts"/></a>
            </li>
        </ul>
    </div>
    </div>
</nav>

<div>
<form autocomplete="on" method="post" action="${root}/controller?command=update_contact&id=${contact.get().getId()}"
      id="contact-form" name="form2" enctype="multipart/form-data">

    <div id="input-message-window" class="modal">
        <div class="modal-content">
            <div id="input-messages">

            </div>
            <input type="button" id="message-ok-button" value="Ок" class="apply-button">
        </div>
    </div>
    <article class="edit-photo">
        <header><h3>Фото</h3></header>
        <div class="centered" id="contact-photo-select-area">

            <img id="contact-photo-image" src="${contactImagePath}">
            <input class="hidden" type="file" id="uploaded-contact-photo" name="uploaded-contact-photo" >
            <input type="hidden" id="image-filename">
            <input class="hidden" type="text" id="default-photo-src" value="<c:url value="${pageContext.request.contextPath}/jsp/images/avatar.jpg" />" style="display: none">
        </div>
        <div id="select-photo-modal" class="modal">
            <div id="photo-select-form" class="modal-content">
                <header>
                    <h4>Выбор фото</h4>
                </header>
                <input class="centered block" type="file" id="photo-file-input" name="photo-item" accept="image/jpeg,image/png,image/gif">
                <div id="apply-buttons">
                    <input class="centered block apply-button" type="button" id="save-photo-button" value="Сохранить">
                    <input class="centered block apply-button remove" type="button" id="remove-photo-button" value="Убрать фото">
                    <input class="centered block apply-button" type="button" id="cancel-photo-select-button" value="Отменить">
                </div>
            </div>
        </div>
    </article>

    <div class="form-group">
        <table class="table table-hover">
            <thead>
            <tr style="background-color: lightgrey">
                <th scope="col">ID: ${contact.get().getId()}</th>
                <th scope="col"><fmt:message key="contact.data"/></th>
                <th scope="col"><fmt:message key="contact.edit.data"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><fmt:message key="contact.firstname"/></td>
                <td>${contact.get().getFirstName()}</td>
                <td><input type="text" name="first_name" class="form-control" required
                           pattern="[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}"
                           value="${not empty contact ? contact.get().getFirstName() : ''}">
                    <small id="firstNameHelp" class="form-text text-muted"><fmt:message key="add.contact.firstname.small"/>
                    </small>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="contact.lastname"/></td>
                <td>${contact.get().getLastname()}</td>
                <td><input type="text" name="last_name" class="form-control" required
                           pattern="[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}"
                           value="${not empty contact ? contact.get().getLastname() : ''}">
                    <small id="lastnameHelp" class="form-text text-muted"><fmt:message key="add.contact.firstname.small"/>
                    </small>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="contact.surname"/></td>
                <td>${contact.get().getSurname()}</td>
                <td><input type="text" name="surname" class="form-control" required
                           pattern="[A-Z]{1}[a-z]{1,19}|[А-ЯЁ]{1}[а-яё]{1,19}"
                           value="${not empty contact ? contact.get().getSurname() : ''}">
                    <small id="surnameHelp" class="form-text text-muted"><fmt:message key="add.contact.firstname.small"/>
                    </small>
                </td>
            </tr>
            <td><fmt:message key="contact.birthday"/></td>
            <td>${contact.get().getBirthday()}</td>
            <td><input type="date" name="birthday" class="form-control" required
                           value="${not empty contact ? contact.get().getBirthday() : ''}" min="1900-01-01" max="2100-01-01">
                    <small id="birhdaydHelp" class="text-danger">
                        ${messageAddContactError}
                    </small>
                </td>
            <tr>
                <td><fmt:message key="contact.gender"/></td>
                <td>${contact.get().getGender()}</td>
                <td>
                    <select class="form-control" name="gender" id="gender" required>
                    <option name="empty">${not empty contact ? contact.get().getGender() : ''}</option>
                    <option name="MALE" value="MALE">MALE</option>
                    <option name ="FEMALE" value="FEMALE">FEMALE</option>
                </select>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="contact.nationality"/></td>
                <td>${contact.get().getNationality()}</td>
                <td><input type="text" name="nationality" class="form-control"
                           value="${not empty contact ? contact.get().getNationality() : ''}">
                </td>
            </tr>
            <tr>
                <td><fmt:message key="contact.marital.status"/></td>
                <td>${contact.get().getMaritalStatus()}</td>
                <td>
                    <select class="form-control" name="marital_status" id="marital_status" required>
                    <option name="empty">${not empty contact ? contact.get().getMaritalStatus() : ''}</option>
                    <option name="SINGLE" value="SINGLE"><fmt:message key="contact.marital.status.single"/> </option>
                    <option name ="MARRIED" value="MARRIED"><fmt:message key="contact.marital.status.married"/> </option>
                    <option name ="DIVORCE" value="DIVORCE"><fmt:message key="contact.marital.status.divorce"/> </option>
                </select>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="contact.company"/></td>
                <td>${contact.get().getCompany()}</td>
                <td><input type="text" name="company" class="form-control"
                           value="${not empty contact ? contact.get().getCompany() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.website"/></td>
                <td>${contact.get().getWebSite()}</td>
                <td><input type="text" name="website" class="form-control"
                           value="${not empty contact ? contact.get().getWebSite() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.email"/></td>
                <td>${contact.get().getEmail()}</td>
                <td><input type="text" name="email" class="form-control"
                           value="${not empty contact ? contact.get().getEmail() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.county"/></td>
                <td>${contact.get().getCountry()}</td>
                <td><input type="text" name="country" class="form-control"
                           value="${not empty contact ? contact.get().getCountry() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.city"/></td>
                <td>${contact.get().getCity()}</td>
                <td><input type="text" name="city" class="form-control"
                           value="${not empty contact ? contact.get().getCity() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.street"/></td>
                <td>${contact.get().getStreet()}</td>
                <td><input type="text" name="street" class="form-control"
                           value="${not empty contact ? contact.get().getStreet() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.house"/></td>
                <td>${contact.get().getHouse()}</td>
                <td><input type="text" name="house" class="form-control"
                           value="${not empty contact ? contact.get().getHouse() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.flat"/></td>
                <td>${contact.get().getFlat()}</td>
                <td><input type="text" name="flat" class="form-control"
                           value="${not empty contact ? contact.get().getFlat() : ''}"></td>
            </tr>
            <tr>
                <td><fmt:message key="contact.postcode"/></td>
                <td>${contact.get().getPostcode()}</td>
                <td><input type="text" name="postcode" class="form-control"
                           value="${not empty contact ? contact.get().getPostcode() : ''}"></td>
            </tr>
            </tbody>
        </table>
    </div>


    <header><h3 class="centered">Контактные телефоны</h3></header>
    <thead class="thead-light text-uppercase text-md-center">
        <p align="right">
        <input class="action-button remove" type="button" id="remove-phone-button"
               value="<fmt:message key="locale.delete"/>"
               onclick="checkBoxPhoneValidation(); deleteNumberRow('numberId')">
        <input class="action-button edit" type="button" id="edit-phone-button" value="Редактировать">
        <input class="action-button add" type="button" id="add-phone-button" value="Добавить">
        <table class="table table-hover table-bordered table-responsive-md" id="numberId">

        <tr style="background-color: lightgrey">
            <th class="control-col">Выбрать</th>
            <th scope="col"><fmt:message key="contact.phonenumber"/> </th>
            <th scope="col"><fmt:message key="phonenumber.type"/></th>
            <th scope="col"><fmt:message key="phonenumber.comment"/></th>
            <th class="control-col"></th>
        </tr>
        </thead>
        <tbody class="text-left">

        <c:if test="${not empty phoneNumbers}">
        <c:forEach var="number" items="${phoneNumbers}">
            <tr>
                <td id="id"><input type="checkbox" name="selected_phone" value="${number.id}"/></td>
                <td scope="row">
                    <c:if test="${number.countryCode != null}"><c:out value="${number.countryCode}"/> </c:if>
                    <c:if test="${number.phoneCode != null}"> <c:out value="${number.phoneCode}" /> </c:if>
                    <c:if test="${number.number != null}"> <c:out value="${number.number}" /> </c:if>
                </td>
                <td scope="row"><c:if test="${number.type != null}"> <c:out value="${number.type}" /></c:if></td>
                <td scope="row"><c:if test="${number.comment != null}"> <c:out value="${number.comment}" /></c:if></td>
                <td></td>
            </tr>
        </c:forEach>
        </c:if>
        <c:if test="${empty phoneNumbers}">

            <tr>
                <td></td>
                <td>${contact.get().getFirstName()} ${contact.get().getLastname()} doesn't have any numbers.</td>
                <td>empty type</td>
                <td>no comments</td>
                <td></td>
            </tr>
        </c:if>

        </tbody>
    </table>

        <header><h3 class="centered">Список присоединений</h3></header>

    <p align="right">
        <input class="action-button remove" type="button" id="remove-attachment-button"
               value="<fmt:message key="locale.delete"/>"
               onclick="deleteAttachment(); deleteNumberRow('attachmentId')">
        <input class="action-button edit" type="button" id="edit-attachment-button" value="Редактировать">
        <input class="action-button add" type="button" id="add-attachment-button" value="Добавить">
        <input class="hidden" type="file" id="uploaded-attachment" name="uploaded-attachment" >
    <table class="table table-hover table-bordered table-responsive-md" id="attachmentId">
        <thead class="thead-light text-uppercase text-md-center">
        <tr style="background-color: lightgrey">
            <th class="control-col">Выбрать</th>
            <th scope="col"><fmt:message key="contact.attachment.name"/> </th>
            <th scope="col"><fmt:message key="attachment.upload.date"/></th>
            <th scope="col"><fmt:message key="attachment.comment"/></th>
            <th class="control-col"></th>
        </tr>
        </thead>
        <tbody class="text-left">
<label>
        <c:if test="${not empty attachments}">
            <c:forEach var="attachment" items="${attachments}">
                <tr>
                    <td id="idAttachment"><input type="checkbox" id="selected_attachment" name="selected_attachment" value="${attachment.id}"/></td>
                    <td scope="row"><c:if test="${attachment.nameAttachment != null}"><c:out value="${attachment.nameAttachment}"/> </c:if></td>
                    <td scope="row"><c:if test="${attachment.dateUpload != null}"> <c:out value="${attachment.dateUpload}" /> </c:if></td>
                    <td scope="row"><c:if test="${attachment.comment != null}"> <c:out value="${attachment.comment}" /> </c:if></td>
                    <td></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty attachments}">
</label>
            <tr>
                <td></td>
                <td>${contact.get().getFirstName()} ${contact.get().getLastname()} doesn't have any attachments.</td>
                <td>empty date</td>
                <td>no comments</td>
                <td></td>
            </tr>
        </c:if>

        </tbody>
    </table>


    <div class="form-group">
        <button class="btn btn-outline-danger my-2 my-sm-0" type="submit"><fmt:message key="contact.btn.contact.save"/></button>
    </div>

<%--        Select file to upload: <input type="file" name="fileName" size="60" /><br />--%>
<%--        <br /> <input type="submit" value="Upload" />--%>
</form>
</div>


<div id="myModal" class="modal" >
    <div class="modal-content">
        <span class="close">&times;</span>
        <h3>Добавление телефона</h3>
        <br>
            <input type="text" name="country_code" id="country_code" maxlength="3" placeholder="Код страны"
                   required pattern="[0-9]{3}">
        <small>Введите код страны в формате xxx, где вместо x должна быть цифра</small>
            <input type="text" name="phone_code" id="phone_code" maxlength="2" placeholder="Код оператора"
                   required pattern="[0-9]{2}">
        <small>Введите код телефона в формате xx, где вместо x должна быть цифра</small>
            <input type="tel" name="number" id="number" placeholder="Телефонный номер"
                   required pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}">
        <small>Введите телефон в формате xxx-xx-xx, где вместо x должна быть цифра</small>
            <select name="type_phone" id="type_phone" required>
                <option name="empty">Тип номера</option>
                <option name="MOBILE" value="MOBILE">MOBILE</option>
                <option name ="HOME" value="HOME">HOME</option>
            </select></br>
            <input type="text" name="comment" id="comment" placeholder="Комментарий"></br>
            <input id="btnSave" class="save" type="submit" value="Сохранить" onclick="savePhone();">
            <input id="btnClose" class="close" type="submit" value="Отменить">
    </div>
</div>

<div id="editPhoneModal" class="modal" >
    <div class="modal-content">
        <span id="editPhoneClose" class="close">&times;</span>
        <h3>Редактирование телефона</h3>
        <br>
        <input type="text" name="country_code" id="edit_country_code" maxlength="3" placeholder="Код страны"
               required pattern="[0-9]{3}">
        <small>Введите код страны в формате xxx, где вместо x должна быть цифра</small>
        <input type="text" name="phone_code" id="edit_phone_code" maxlength="2" placeholder="Код оператора"
               required pattern="[0-9]{2}">
        <small>Введите код телефона в формате xx, где вместо x должна быть цифра</small>
        <input type="tel" name="number" id="edit_number" placeholder="Телефонный номер"
               required pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}">
        <small>Введите телефон в формате xxx-xx-xx, где вместо x должна быть цифра</small>
        <select name="type_phone" id="edit_type_phone" required>
            <option name="empty"></option>
            <option name="MOBILE" value="MOBILE">MOBILE</option>
            <option name ="HOME" value="HOME">HOME</option>
        </select></br>
        <input type="text" name="comment" id="edit_comment" placeholder="Комментарий"></br>
        <input id="editBtn" class="save" type="submit" value="Сохранить" onclick=" editNumberRow();">
        <input id="editBtnClose" class="close" type="submit" value="Отменить">
    </div>
</div>

<div id="attachmentModal" class="modal" >
    <div class="modal-content" id="modalAttachment">
        <span class="attachmentClose">&times;</span>
        <h3>Загрузка присоединения</h3>
        <br>
        <input type="file" name="uploadAttachment" id="uploadAttachment" size="60" >
        <input type="text" name="comment" id="commentAttachment" placeholder="Комментарий"></br>
        <input id="btnSaveAttachment" class="save" type="submit" value="Загрузить" onclick="addAttachment();">
        <input id="btnCloseAttachment" class="close" type="submit" value="Отменить">
    </div>
</div>

<div id="editAttachmentModal" class="modal" >
    <div class="modal-content">
        <span id="editAttachmentClose" class="close">&times;</span>
        <h3>Редактирование присоединения</h3>
        <br>
        <input type="text" name="name_attachment" id="edit_attachment_code" placeholder="Имя файла"
               required>
        <input type="text" name="comment" id="edit_attachment_comment" placeholder="Комментарий"></br>
        <input id="editBtnSaveAttachment" class="save" type="submit" value="Сохранить" onclick="editNumberRow()">
        <input id="editBtnCloseAttachment" class="close" type="submit" value="Отменить">
    </div>
</div>

</form>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="/js/contact.js"></script>
</body>

</html>
