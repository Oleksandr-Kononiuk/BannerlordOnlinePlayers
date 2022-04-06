const clansAjaxUrl = "clans/";
const clansAjaxAll = "clans/all"

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: clansAjaxAll,
    updateTable: function () {
        $.get(clansAjaxAll, updateTableByData);
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    var army = 0;
                    //loop through all the row details(members) to count output clan army
                    for (var member in row.members) {
                        var m = row.members[member];
                        army = army + m.army;
                    }
                    return army;
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    return '<a href=todo>Members</a>';
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    return '<a href=todo>War List</a>';
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    return '<a href=todo>Alliance List</a>';
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
                "asc"
            ]
        ]
    });
});

function getClanArmy(data) {
    return $.ajax({
        type: "GET",
        url: "/ajax/" + data.name + "/army"
    })
        .done(function (responseData, status, xhr) {
            console.log(status);
        })
        .fail(function (xhr, status, err) {
            console.log(status, err);
        });
}