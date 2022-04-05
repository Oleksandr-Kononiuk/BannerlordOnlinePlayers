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
                "data": null
            },
            {
                "data": null
            },
            {
                "data": null
            },
            {
                "data": null
            },
            // {
            //     "data": null,
            //     "render": function (data, type, row) {
            //         return "-army-";
            //     }
            // },
            // {
            //     "data": null,
            //     "render": function (data, type, row) {
            //         return "-members-";
            //     }
            // },
            // {
            //     "data": null,
            //     "render": function (data, type, row) {
            //         return "-at-war-";
            //     }
            // },
            // {
            //     "data": null,
            //     "render": function (data, type, row) {
            //         return "-at-alliance-";
            //     }
            // },
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