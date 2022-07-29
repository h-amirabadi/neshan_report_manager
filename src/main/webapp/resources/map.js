function initMap(mapElement) {

    var map = L.map(mapElement).setView([35.714798, 51.373401], 18);

    map.invalidateSize();
    L.tileLayer('https://api.maptiler.com/maps/streets/256/{z}/{x}/{y}.png?key=WAQUM65moiHkCrnh2kYE', {
        attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>',
    }).addTo(map);

    /* var greenIcon = L.icon({
         iconUrl: '/resources/3rdP/leaflet/images/marker-icon.png',
         // shadowUrl: 'leaf-shadow.png',

         iconSize:     [38, 95], // size of the icon
         shadowSize:   [50, 64], // size of the shadow
         iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
         shadowAnchor: [4, 62],  // the same for the shadow
         popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
     });*/

    addActiveReportsToMap(map);
    // mapInvalidateMapSize(map);
}


function mapInvalidateMapSize(map) {
    if (map.getSize().x == 0) {
        setTimeout(function () {
            map.invalidateSize();
            mapInvalidateMapSize(map);
        }, 200);
    }
}


function addActiveReportsToMap(map) {
    $.ajax({
        url: "/api/v1/report/active-reports",
        cache: false,
        type: "GET",
        dataType: 'json',
        contentType: 'application/json',
        // data: JSON.stringify(frmD),
        complete: function (xhr, st) {
            if (xhr.status === 200) {
                clog(xhr.responseText);
                var json = jQuery.parseJSON(xhr.responseText);
                $.each(json, function (i, report) {
                    var m = L.marker([report.x, report.y],).addTo(map)/*.bindPopup('A pretty CSS3 popup.<br> Easily customizable.')*/;
                    m.on('click', function (e) {
                        reloadReportPane(report.id);
                    })
                });
            } else {
                alert("Cannot get active reports. Error code:" + xhr.status);
            }
        }
    });

    return false;
}


function reloadReportPane(reportId) {
    var divTools = $("#divTools");
    divTools.html("");
    divTools.load("resources/views/tools-body.jsp?rid=" + reportId, function () {
        divTools.fadeIn();
    })
}

function closeReportPane() {
    var divTools = $("#divTools");
    divTools.fadeOut(function () {
        divTools.html("");
    });
}


function likeReport(){
    var like = {
        likeStatus: "true",
        account: {
            id: $("#hdAccountId").val()
        },
        report: {
            id: $("#hdReportId").val()
        }
    }

    $.ajax({
        url: "/api/v1/report/like",
        cache: false,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: like,
        complete: function (xhr, st) {
                clog(xhr.responseText);
            if (xhr.status === 200) {
                clog(xhr.responseText);
                reloadReportPane(like.report.id);
            } else {
                alert("Error code:" + xhr.status);
            }
        }
    });

    return false;
}

function dislikeReport(){
    var dislike = {
        likeStatus: false,
        account: {
            id: $("#hdAccountId").val()
        },
        report: {
            id: $("#hdReportId").val()
        }
    }

    $.ajax({
        url: "/api/v1/report/dislike",
        cache: false,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: dislike,
        complete: function (xhr, st) {
            if (xhr.status === 200) {
                clog(xhr.responseText);
                reloadReportPane(dislike.report.id);
            } else {
                alert("Error code:" + xhr.status);
            }
        }
    });

    return false;
}