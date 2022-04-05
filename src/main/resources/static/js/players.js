const playerAjaxUrl = "players/";
const playerAjaxAll = "players/all"

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
    ajaxUrl: playerAjaxAll,
    updateTable: function () {
        $.get(playerAjaxAll, updateTableByData);
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
                        return "-";
                    }
                    var clanName = data.clan.name;
                    return '<a href="' + clanName + '">' + clanName + '</a>';
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