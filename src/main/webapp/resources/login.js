function doLogin() {
    var frmLogin = $("#frmLogin");
    var frmD = frmLogin.serializeJSON();
    $.ajax({
        url: "/api/v1/account/login?" + $.param(frmD),
        cache: false,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        // data: JSON.stringify(frmD),
        complete: function (xhr, st) {
            if (xhr.status === 200) {
                // var json = jQuery.parseJSON(xhr.responseText);
                window.location = "home";
            } else if (xhr.status === 202) {
                window.location = "login";
                alert("Wrong Pass");
            } else {
                alert("Authentication failed. Error code:" + xhr.status);
            }
        }
    });

    return false;
}