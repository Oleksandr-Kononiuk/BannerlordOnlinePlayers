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
                    if (row.members.length === 0) {
                        return "";
                    } else {
                        return createDetails(row.members);
                        // var members = '<details><summary></summary>'
                        // for (var member in row.members) {
                        //     var nameLink = '<a href=todo>' + row.members[member].name + '</a>'
                        //     members = members + nameLink + '<br>'
                        // }
                        // return members + '</details>';
                    }
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    if (row.warList.length === 0) {
                        return "";
                    } else {
                        return createDetails(row.warList);
                        // var warList = '<details><summary></summary>'
                        // for (var clan in row.warList) {
                        //     var clanNameLink = '<a href=todo>' + row.warList[clan].name + '</a>'
                        //     warList = warList + clanNameLink + '<br>'
                        // }
                        // return warList + '</details>';
                    }
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    if (row.allianceList.length === 0) {
                        return "";
                    } else {
                        return createDetails(row.allianceList);
                        // var allianceList = '<details><summary></summary>'
                        // for (var clan in row.allianceList) {
                        //     var clanNameLink = '<a href=todo>' + row.allianceList[clan].name + '</a>'
                        //     allianceList = allianceList + clanNameLink + '<br>'
                        // }
                        // return allianceList + '</details>';
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
                "asc"
            ]
        ]
    });
});

function createDetails(array) {
    var details = '<details><summary></summary>'
    for (var element in array) {
        var elementNameLink = '<a href=todo>' + array[element].name + '</a>'
        details = details + elementNameLink + '<br>'
    }
    return details + '</details>';
}