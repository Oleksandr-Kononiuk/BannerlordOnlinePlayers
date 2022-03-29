const userAjaxUrl = "/clans/";

// https://stackoverflow.com/a/5064235/548473
// const ctx = {
//     ajaxUrl: userAjaxUrl,
//     updateTable: function () {
//         $.get(userAjaxUrl, updateTableByData);
//     }
// }

const ctx = {
    ajaxUrl: mealAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: mealAjaxUrl + "all",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    }
};

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: userAjaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-clans-enabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

// $(document).ready(function () {
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
                "data": "members"
            },
            {
                "data": "at-war"
            },
            {
                "data": "at-alliance"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).attr("data-clans-enabled", false);
            }
        }
    });
});