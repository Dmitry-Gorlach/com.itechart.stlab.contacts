
"use strict"
    function checkBoxValidation() {
        console.log(document.sendEmail.selected_contacts)
        var selectedContacts = Array.apply(null, document.sendEmail.selected_contacts).filter(function (el) {
            return el.checked === true
        }).map(function (el) {
            return el.value
        });

        if (selectedContacts.length === 0) {
            alert("Please Select contact to delete");
        } else {
            alert("Click OK to delete selected contacts");

        }

        var formData = new FormData();
        formData.append("selected_contacts", selectedContacts);
        var request = new XMLHttpRequest();
        request.open("POST",
            "http://localhost:8080/controller?command=delete_contact");
        request.send(formData);

    }

var messageModalWindow = document.getElementById('message-modal-window');
var messageTextField = document.getElementById('message-info-field');
var messageOkButton = document.getElementById('message-ok-button');
var messageWindow = document.getElementById('message-window');
var inputMessages = document.getElementById('input-messages');


    function deleteRow(contactId) {
        try {
            var table = document.getElementById(contactId);
            var rowCount = table.rows.length;

            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true === chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        }catch(e) {
            alert(e);
        }
    }

//    update phone number table
    
var modal = document.getElementById('myModal');
var editPhoneModal = document.getElementById('editPhoneModal');
var btn = document.getElementById('add-phone-button');
var editBtn = document.getElementById('edit-phone-button');
var editBtnClose = document.getElementById('editBtnClose');
var span = document.getElementsByClassName("close")[0];
var editPhoneClose = document.getElementById('editPhoneClose');
var close = document.getElementById('btnClose');
var save = document.getElementById('btnSave');
var editPhoneSave = document.getElementById('editBtn');





function getNewId() {
    var id=0;
    id+= 1;
    return id + "n";
}

function savePhone() {

    var table = document.getElementById('numberId');
    var row = table.insertRow(-1);
    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);
    var cell4 = row.insertCell(4);

    var idPhoneNumberInput = document.createElement("input");
    idPhoneNumberInput.setAttribute("type", "checkbox");
    idPhoneNumberInput.value = getNewId();
    document.getElementById("contact-form").appendChild(idPhoneNumberInput);

    var countryCode = document.getElementById("country_code");
    var countryCodeInput = document.createElement("input");
    countryCodeInput.setAttribute("type", "hidden");
    countryCodeInput.name = "country_code";
    countryCodeInput.pattern = "[+]\d{3}";
    countryCodeInput.value = countryCode.value;
    document.getElementById("contact-form").appendChild(countryCodeInput);

    var phoneCode = document.getElementById("phone_code");
    var phoneCodeInput = document.createElement("input");
    phoneCodeInput.setAttribute("type", "hidden");
    phoneCodeInput.name = "phone_code";
    phoneCodeInput.value = phoneCode.value;
    document.getElementById("contact-form").appendChild(phoneCodeInput);

    var number = document.getElementById("number");
    var numberInput = document.createElement("input");
    numberInput.setAttribute("type", "hidden");
    numberInput.name = "number";
    numberInput.value = number.value;
    document.getElementById("contact-form").appendChild(numberInput);

    var typePhone = document.getElementById("type_phone");
    var typePhoneInput = document.createElement("input");
    typePhoneInput.setAttribute("type", "hidden");
    typePhoneInput.name = "type_phone";
    typePhoneInput.value = typePhone.value;
    document.getElementById("contact-form").appendChild(typePhoneInput);

    var comment = document.getElementById("comment");
    var commentInput = document.createElement("input");
    commentInput.setAttribute("type", "hidden");
    commentInput.name = "comment";
    commentInput.value = comment.value;
    document.getElementById("contact-form").appendChild(commentInput);

    cell0.append(idPhoneNumberInput);
    cell1.append(countryCode.value + " " + phoneCode.value + " " + number.value);
    cell2.append(typePhone.value);
    cell3.append(comment.value);
    cell4.innerHTML = "";

    countryCode.value = "";
    phoneCode.value = "";
    number.value = "";
    typePhone.value = "";
    comment.value = "";

}



function checkBoxPhoneValidation() {
    console.log(document.form2.selected_phone)
    var selectedPhones = Array.apply(null, document.form2.selected_phone).filter(function (el) {
        return el.checked === true
    }).map(function (el) {
        return el.value
    });

    if (selectedPhones.length === 0) {
        alert("Please Select phone to delete");
    } else {
        alert("Click OK to delete selected phones");
    }
    var formData = new FormData();
    formData.append("selected_phones", selectedPhones);
    var request = new XMLHttpRequest();
    request.open("POST",
        "http://localhost:8080/controller?command=delete_phone");
    request.send(formData);
}

function deleteAttachment() {
    var selectedAttachments = Array.apply(null,
        document.form2.selected_attachment).filter(function (el) {
        return el.checked === true
    }).map(function (el) {
        return el.value
    });

    if (selectedAttachments.length === 0) {
        alert("Please Select attachment to delete");
    } else {
        alert("Click OK to delete selected attachment");
    }
    var formData = new FormData();
    formData.append("selected_attachments", selectedAttachments);
    var request = new XMLHttpRequest();
    request.open("POST",
        "http://localhost:8080/controller?command=delete_attachment");
    request.send(formData);
}


function deleteNumberRow(numberId) {
    try {
        var table = document.getElementById(numberId);
        var rowCount = table.rows.length;

        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true === chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
    }catch(e) {
        alert(e);
    }
}



function getCheckedItems(checkName) {
    var checkBoxes = document.getElementsByName(checkName);
    var checked = [];
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            checked.push(checkBoxes[i].value);
        }
    }
    return checked;
}

function editNumberRow() {
    try {
        var table = document.getElementById('numberId');
        var rowCount = table.rows.length;

        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];

                if(chkbox.checked === true){

                var selectedPhoneInput = document.createElement("input");
                selectedPhoneInput.setAttribute("type", "hidden");
                selectedPhoneInput.name = "selected-phone";
                selectedPhoneInput.value = chkbox.value;
                document.getElementById("contact-form").appendChild(selectedPhoneInput);

                var countryCode = document.getElementById("edit_country_code");
                var countryCodeInput = document.createElement("input");
                countryCodeInput.setAttribute("type", "hidden");
                countryCodeInput.name = "country_code";
                countryCodeInput.pattern = "[+]\d{3}";
                countryCodeInput.value = countryCode.value;
                document.getElementById("contact-form").appendChild(countryCodeInput);

                var phoneCode = document.getElementById("edit_phone_code");
                var phoneCodeInput = document.createElement("input");
                phoneCodeInput.setAttribute("type", "hidden");
                phoneCodeInput.name = "phone_code";
                phoneCodeInput.value = phoneCode.value;
                document.getElementById("contact-form").appendChild(phoneCodeInput);

                var number = document.getElementById("edit_number");
                var numberInput = document.createElement("input");
                numberInput.setAttribute("type", "hidden");
                numberInput.name = "number";
                numberInput.value = number.value;
                document.getElementById("contact-form").appendChild(numberInput);

                var typePhone = document.getElementById("edit_type_phone");
                var typePhoneInput = document.createElement("input");
                typePhoneInput.setAttribute("type", "hidden");
                typePhoneInput.name = "type_phone";
                typePhoneInput.value = typePhone.value;
                document.getElementById("contact-form").appendChild(typePhoneInput);

                var comment = document.getElementById("edit_comment");
                var commentInput = document.createElement("input");
                commentInput.setAttribute("type", "hidden");
                commentInput.name = "comment";
                commentInput.value = comment.value;
                document.getElementById("contact-form").appendChild(commentInput)

                var x = document.getElementById("numberId").rows[i].cells;
                x[1].innerHTML =countryCode.value + " " + phoneCode.value + " " + number.value;
                x[2].innerHTML =typePhone.value;
                x[3].innerHTML =comment.value;
                x[4].innerHTML = "";
            }
        }
    }catch(e) {
        alert(e);
    }
}

var photoFileInput = document.getElementById('photo-file-input');
var savePhotoButton = document.getElementById('save-photo-button');
var removePhotoButton = document.getElementById('remove-photo-button');
var contactPhotoImage = document.getElementById('contact-photo-image');
var contactPhotoSelectArea = document.getElementById('contact-photo-select-area');
var selectPhotoModal = document.getElementById('select-photo-modal');
var cancelPhotoSelectButton = document.getElementById('cancel-photo-select-button');
var uploadedContactPhotoFileInput = document.getElementById('uploaded-contact-photo');
var photoSelectForm = document.getElementById('photo-select-form');
var applyButtons = document.getElementById('apply-buttons');
var imageFilename = document.getElementById('image-filename');
var photo = document.getElementById('photo');



function clearFileInput(photoFileInput) {
    var parentNode = photoFileInput.parentNode;
    var clone = photoFileInput.cloneNode(true);
    parentNode.replaceChild(clone, photoFileInput);
    return clone;
}



function createPhotoFileInput() {
    var newPhotoFileInput = document.createElement('input');
    newPhotoFileInput.type = 'file';
    newPhotoFileInput.id = 'photo-file-input';
    newPhotoFileInput.accept = 'image/jpeg,image/png,image/gif';
    newPhotoFileInput.name = 'upload-photo';
    newPhotoFileInput.className = "centered block";
    return newPhotoFileInput;
}


function hideModalForm(form) {
    form.style.display = "none";
}

function showModalForm(form) {
    form.style.display = "block";
}

// attachment

var attachmentModal = document.getElementById('attachmentModal');
var editAttachmentModal = document.getElementById('editAttachmentModal');
var addAttachmentButton = document.getElementById('add-attachment-button');
var editAttachmentButton = document.getElementById('edit-attachment-button');
var editBtnCloseAttachment = document.getElementById('editBtnCloseAttachment');
var attachmentClose = document.getElementsByClassName("attachmentClose")[0];
var editAttachmentClose = document.getElementById('editAttachmentClose');
var btnCloseAttachment = document.getElementById('btnCloseAttachment');
var btnSaveAttachment = document.getElementById('btnSaveAttachment');
var editBtnSaveAttachment = document.getElementById('editBtnSaveAttachment');


    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;

    function createNewInputAttachment() {
        let uploadAttachment = document.createElement('input');
        uploadAttachment.type = 'file';
        uploadAttachment.name = 'uploadAttachment';
        uploadAttachment.id = 'uploadAttachment';
        document.getElementById('modalAttachment').append(uploadAttachment);
        return uploadAttachment;
    }


var modalAttachment = document.getElementById('modalAttachment');
var contactForm = document.getElementById('contact-form');

function createCloneAttachment(inputAttachment) {
    let uploadedAttachmentInput =inputAttachment.cloneNode(false);
    // uploadAttachment.parentNode.removeChild(uploadAttachment);
    uploadedAttachmentInput.name = 'uploadAttachment';
    uploadedAttachmentInput.id = 'uploadAttachment';
    uploadedAttachmentInput.style.display = "none";
    contactForm.appendChild(uploadedAttachmentInput);
    // createNewInputAttachment();
}

function addAttachment() {

    var table = document.getElementById('attachmentId');
    var row = table.insertRow(-1);
    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);
    var cell4 = row.insertCell(4);

    var idAttachmentInput = document.createElement("input");
    idAttachmentInput.setAttribute("type", "checkbox");
    idAttachmentInput.value = getNewId();
    document.getElementById("contact-form").appendChild(idAttachmentInput);


    let uploadAttachment = document.getElementById("uploadAttachment");
    let newUloadAttachment = uploadAttachment.cloneNode(true);
    modalAttachment.replaceChild(newUloadAttachment, uploadAttachment);

    createCloneAttachment(uploadAttachment);

    var filename = uploadAttachment.files[0].name;

    var dateUploadInput = document.createElement("input");
    dateUploadInput.setAttribute("type", "hidden");
    dateUploadInput.name = "date_upload";
    dateUploadInput.value = today;
    document.getElementById("contact-form").appendChild(dateUploadInput);

    var attachmentComment = document.getElementById("commentAttachment");
    var attachmentCommentInput = document.createElement("input");
    attachmentCommentInput.setAttribute("type", "hidden");
    attachmentCommentInput.name = "commentAttachment";
    attachmentCommentInput.value = attachmentComment.value;
    document.getElementById("contact-form").appendChild(attachmentCommentInput);

    cell0.append(idAttachmentInput);
    cell1.append(filename);
    cell2.append(dateUploadInput.value);
    cell3.append(attachmentComment.value);
    cell4.innerHTML = "";

    uploadAttachment.value ="";
    // uploadedAttachmentInput.value = "";
    attachmentComment.value = "";
}
btn.onclick = function() {
    modal.style.display = "block";
};
editBtn.onclick = function(){
    editPhoneModal.style.display = "block";
};

span.onclick = function () {
    modal.style.display = "none";
};
editPhoneClose.onclick = function() {
    editPhoneModal.style.display = "none";
};
window.onclick = function (event) {
    if(event.target === modal | event.target === close | event.target === save) {
        modal.style.display = "none";
    }
    else if (event.target === photo){
        showModalForm(selectPhotoModal)
    }
    else if (event.target === selectPhotoModal) {
        hideModalForm(selectPhotoModal);
    }
    else if (event.target === messageWindow) {
        hideModalForm(messageWindow);
    }
    else if (event.target === editPhoneModal | event.target === editBtnClose |event.target === editPhoneSave){
        editPhoneModal.style.display = "none";
    }
    else if(event.target === attachmentModal | event.target === btnCloseAttachment | event.target === btnSaveAttachment) {
        attachmentModal.style.display = "none";
    }
    else if (event.target === editAttachmentModal | event.target === editBtnCloseAttachment |event.target === editBtnSaveAttachment){
        editPhoneModal.style.display = "none";
    }
};
removePhotoButton.onclick = function() {
    var defaultPhotoSrc = document.getElementById('default-photo-src').value;
    uploadedContactPhotoFileInput = clearFileInput(uploadedContactPhotoFileInput);
    imageFilename.value = 'avatar';
    contactPhotoImage.src = defaultPhotoSrc;
    hideModalForm(selectPhotoModal);
};
cancelPhotoSelectButton.onclick = function () {
    hideModalForm(selectPhotoModal);
};
contactPhotoSelectArea.onclick = function () {
    showModalForm(selectPhotoModal);
};

savePhotoButton.onclick = function () {
    var fileReader = new FileReader;
    var imageFile = photoFileInput.files[0];
    var MAX_IMAGE_SIZE_MB = 10;
    var MAX_IMAGE_SIZE = MAX_IMAGE_SIZE_MB * 1024 * 1024;
    fileReader.onload = function () {
        contactPhotoImage.src = this.result;
    };
    if (imageFile) {
        if (imageFile.size > MAX_IMAGE_SIZE) {
            showMessage('Размер фото не может превышать ' + MAX_IMAGE_SIZE_MB + ' МБ');
        }
        else {
            fileReader.readAsDataURL(imageFile);
            uploadedContactPhotoFileInput.parentNode.removeChild(uploadedContactPhotoFileInput);
            photoFileInput.id = 'uploaded-contact-photo';
            photoFileInput.name = 'upload-photo';
            photoFileInput.style.display = 'none';
            contactPhotoSelectArea.appendChild(photoFileInput);
            uploadedContactPhotoFileInput = photoFileInput;
            photoFileInput = createPhotoFileInput();
            photoSelectForm.insertBefore(photoFileInput, applyButtons);
            imageFilename.value = '';
            hideModalForm(selectPhotoModal);
        }
    }
    else {
        inputMessages.innerHTML = '';
        var errorMessage = document.createElement('h3');
        errorMessage.className = 'error-message';
        errorMessage.innerHTML = 'Сначала выберите фото';
        inputMessages.appendChild(errorMessage);
        messageWindow.style.zIndex = 5;
        showModalForm(messageWindow);
        okMessageWindowButton.focus();
    }
};
addAttachmentButton.onclick = function() {
    attachmentModal.style.display = "block";
};

editAttachmentButton.onclick = function(){
    editAttachmentModal.style.display = "block";
};

attachmentClose.onclick = function () {
    attachmentModal.style.display = "none";
};
editAttachmentClose.onclick = function() {
    editAttachmentModal.style.display = "none";
};
editBtnCloseAttachment.onclick = function() {
    editAttachmentModal.style.display = "none";
};