const playerAjaxUrl = "players/";

// https://stackoverflow.com/a/5064235/548473
// const ctx = {
//     ajaxUrl: "players/all",
//     updateTable: function () {
//         $.ajax({
//             type: "GET",
//             url: "players/all"
//             // data: $("#filter").serialize()
//         }).done(updateTableByData);
//     }
// };
const ctx = {
    ajaxUrl: playerAjaxUrl,
    updateTable: function () {
        $.get(playerAjaxUrl.concat("all"), updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "army"
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    if (data.clan === null) {
                        return "";
                    }
                    var clanName = data.clan.name;
                    return '<a href="' + clanName + '">' + clanName + '</a>';
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                   if (row.nameHistory.length === 0) {
                       return "";
                   } else {
                       var names = '<details> <summary></summary>'
                       //loop through all the row details (name history) to build output string
                       for (var name in row.nameHistory) {
                           var n = row.nameHistory[name];
                           names = names + n + '<br>'
                       }
                       return names + '</details>';
                   }
                }
            },
            {
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderDeleteBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
});

function clearFilter() {
    $("#filter")[0].reset();
    $.get(playerAjaxUrl, updateTableByData);
}