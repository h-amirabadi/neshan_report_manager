function doAddUpdateAccount() {
    var account = {
        firstName: $("#tbFirstName").val(),
        lastName: $("#tbLastName").val(),
        eMail: $("#tbEmail").val(),
        pw: $("#tbPassword").val()
    }

    $.ajax({
        url: "/api/v1/account/add",
        cache: false,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(account),
        complete: function (xhr, st) {
            if (xhr.status === 201) {
                clearForm();
                refreshAccounts();
            } else {
                alert("Error code:" + xhr.status);
            }
        }
    });

    return false;
}

function deleteAccount(id) {

    $.ajax({
        url: "/api/v1/account/delete/" + id,
        cache: false,
        type: "DELETE",
        dataType: 'json',
        contentType: 'application/json',
        complete: function (xhr, st) {
            if (xhr.status === 200) {
                clearForm();
                refreshAccounts();
            } else {
                alert("Error code:" + xhr.status);
            }
        }
    });

    return false;
}

function refreshAccounts() {
    $.ajax({
        url: "/api/v1/account/findAll",
        cache: false,
        type: "GET",
        dataType: 'json',
        contentType: 'application/json',
        complete: function (xhr, st) {
            if (xhr.status === 200) {
                // $("#tblAccountBody").h
                var json = JSON.parse(xhr.responseText);
                $.ajax({
                    url: "resources/views/table-accounts-body.jsp",
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    scriptCharset: "utf-8",
                    data: JSON.stringify(json),
                    complete: function (xhrBody, st) {
                        $("#tblAccounts tbody").html(xhrBody.responseText);
                    }
                });

            } else {
                alert("Error code:" + xhr.status);
            }
        }
    });

    return false;
}


function clearForm(){
    var frm = $("#frmAccount");
    frm.trigger("reset");
}