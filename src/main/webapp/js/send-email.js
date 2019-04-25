function sendEmailSelectedContact() {
    var selectedContacts = Array.apply(null, document.sendEmail.selected_contacts).filter(function (el) {
        return el.checked === true
    }).map(function (el) {
        return el.value
    });

    if (selectedContacts.length === 0) {
        alert("Please select some contact to send email.");
        return false;
    }

    try {
        var table = document.getElementById('contactId');
        var rowCount = table.rows.length;

        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];

            if(chkbox.checked === true ){
                var selectedContactInput = document.createElement("input");
                selectedContactInput.setAttribute("type", "hidden");
                selectedContactInput.name = "selected_contacts";
                selectedContactInput.value = selectedContacts[i];
                document.getElementById("sendEmailForm").appendChild(selectedContactInput);
            } else {
                return false;
            }
        }
    }catch(e) {
        alert(e);
    }
}