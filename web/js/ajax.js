var addNewDeviceBtn;
var viewBtn = document.getElementById("viewBtn");
var deleteZeroBtn = document.getElementById("deleteZeroBtn");
var initBtn = document.getElementById("initBtn");
var popularBtn = document.getElementById("popularBtn");

var tableView = document.getElementById("tableView");
var tablePop = document.getElementById("tablePop");
var popTableBody = document.getElementById("popTableBody");
var tableBody = document.getElementById("viewTableBody");
var responsesHTML = document.getElementById("responsesTab");


//-----------

viewBtn.addEventListener("click", function () {
    viewBtn.disabled = true;
    deleteZeroBtn.disabled = true;
    initBtn.disabled = true;
    popularBtn.disabled = true;

    var myRequest = new XMLHttpRequest();

    myRequest.onload = function () {
        var myData = JSON.parse(myRequest.responseText);
        renderTableHTML(myData);
        deleteZeroBtn.classList.remove("hide-me");
        initBtn.classList.remove("hide-me");
        popularBtn.classList.remove("hide-me");
        deleteZeroBtn.disabled = false;
        initBtn.disabled = false;
        viewBtn.disabled = false;
        popularBtn.disabled = false;
    };

    myRequest.onerror = function () {
        console.log("Connection error");
    };
    myRequest.open('POST', '/loadData');
    myRequest.send();
});

popularBtn.addEventListener("click", function () {
    popularBtn.disabled = true;
    viewBtn.disabled = true;
    deleteZeroBtn.disabled = true;
    initBtn.disabled = true;

    var myRequest = new XMLHttpRequest();

    myRequest.onload = function () {
        var myData = JSON.parse(myRequest.responseText);
        renderPopTableHTML(myData);
        deleteZeroBtn.classList.remove("hide-me");
        initBtn.classList.remove("hide-me");
        responsesHTML.classList.add("hide-me");
        deleteZeroBtn.disabled = false;
        initBtn.disabled = false;
        viewBtn.disabled = false;
        popularBtn.disabled = false;
    };

    myRequest.onerror = function () {
        console.log("Connection error");
    };
    myRequest.open('POST', '/loadPop', true);
    myRequest.send();
    viewBtn.click();
});

deleteZeroBtn.addEventListener("click", function () {
    popularBtn.disabled = true;
    viewBtn.disabled = true;
    deleteZeroBtn.disabled = true;
    viewBtn.disabled = true;
    initBtn.disabled = true;

    var myRequest = new XMLHttpRequest();

    myRequest.onload = function () {
        var myData = JSON.parse(myRequest.responseText);
        renderResponsesHTML(myData);
        responsesHTML.classList.remove("hide-me");
        initBtn.disabled = false;
        viewBtn.disabled = false;
        popularBtn.disabled = false;
        deleteZeroBtn.disabled = false;
        viewBtn.click();
    };

    myRequest.onerror = function () {
        console.log("Connection error");
    };
    myRequest.open('POST', '/deleteZero');
    myRequest.send();
});

initBtn.addEventListener("click", function () {
    initBtn.disabled = true;
    viewBtn.disabled = true;
    deleteZeroBtn.disabled = true;
    popularBtn.disabled = true;

    var myRequest = new XMLHttpRequest();

    myRequest.onload = function () {
        var myData = JSON.parse(myRequest.responseText);
        renderResponsesHTML(myData);
        responsesHTML.classList.remove("hide-me");
        viewBtn.disabled = false;
        deleteZeroBtn.disabled = false;
        initBtn.disabled = false;
        popularBtn.disabled = false;
        viewBtn.click();
    };
    myRequest.onerror = function () {
        console.log("Connection error");
    };
    myRequest.open('POST', '/initDB', true);
    myRequest.send();
});


function renderResponsesHTML(myData) {
    responsesHTML.innerHTML = "";
    responsesHTML.insertAdjacentHTML('beforeend', myData);
    responsesHTML.classList.remove("hide-me")

}

function renderTableHTML(data) {
    var htmlString = "";
    for (i = 0; i < data.length; i++) {
        htmlString += "<tr id=\"device-tr-" + data[i].id + "\" class='w3-light-grey w3-hover-blue' valign=\"top\">";
        htmlString += "<td id=\"device-td-id-" + data[i].id + "\">" + data[i].id + "</td>";
        htmlString += "<td id=\"device-td-name-" + data[i].id + "\">" + data[i].name + "</td>";
        htmlString += "<td id=\"device-td-count-" + data[i].id + "\">" + data[i].count + "</td>";
        htmlString += "<td id=\"device-td-categories-" + data[i].id + "\">" + data[i].categories + "</td>";
        htmlString += "<td>" + "<a onclick='deleteProduct(" + data[i].id + ")'>DELETE</a> ";
        htmlString += "<a onclick='editProduct(" + data[i].id + ")'>EDIT</a>" + "</td>";
        htmlString += "</tr>";
    }
    // ДА ДА ФОРМА МОЖЕТ БЫТЬ ОТДЕЛЬНО
    htmlString += "<form id='newDeviceForm' method='POST' autocomplete='on' action='/addDevice'></form>\n";
    htmlString += "<tr id=\"newDeviceLine" + i + "\" class=\"w3-light-grey w3-hover-purple\" valign=\"top\">";
    htmlString += "<td>" + "<input id='newId' placeholder =\"ID\" name=\"newDeviceID\" form=\"newDeviceForm\">" + "</td>";
    htmlString += "<td>" + "<input id='newName' placeholder=\"NAME\" name=\"newDeviceName\" form=\"newDeviceForm\">" + "</td>";
    htmlString += "<td>" + "<input id='newCount' placeholder=\"COUNT\" name=\"newDeviceCount\" form=\"newDeviceForm\">" + "</td>";
    htmlString += "<td>" + "<input id='newCategories' placeholder=\"CATEGORIES\" name=\"newDeviceCategories\" form=\"newDeviceForm\">" + "</td>";
    htmlString += "<td>" + "<button id='addNewDeviceBtn' onclick='addProduct()'>ADD</button>";
    htmlString += "<br><br><input class='w3-button w3-blue' form=\"newDeviceForm\" type=\"reset\" value=\"CLEAR\">";
    htmlString += "</td></tr>";

    tableBody.innerHTML = "";
    tableBody.insertAdjacentHTML('beforeend', htmlString);
    tableView.classList.remove("hide-me")
}

function renderPopTableHTML(data) {
    var htmlString = "";
    for (i = 0; i < data.length; i++) {
        htmlString += "<tr class=\"w3-light-grey w3-hover-blue\" valign=\"top\">";
        htmlString += "<td>" + data[i].count + "</td>";
        htmlString += "<td>" + data[i].categories + "</td>";
        htmlString += "</tr>";
    }

    popTableBody.innerHTML = "";
    popTableBody.insertAdjacentHTML('beforeend', htmlString);
    tablePop.classList.remove("hide-me")
}

function deleteProduct(i) {
    $.ajax('/deleteDevice', {
        method: 'post',
        data: {
            id: i
        },
        success: function (data) {
            renderResponsesHTML(data);
            responsesHTML.classList.remove("hide-me");
        }
    });
    setTimeout(viewBtn.click(), 200);
}

function addProduct() {
    $.ajax('/addDevice', {
        method: 'post',
        data: {
            id: document.getElementById("newId").value,
            name: document.getElementById("newName").value,
            count: document.getElementById("newCount").value,
            categories: document.getElementById("newCategories").value
        },
        datatype: 'json',
        success: function (data) {
            setTimeout(renderResponsesHTML(data.answer), 200);
        }
    });
    setTimeout(viewBtn.click(), 200);
}

function editProduct(i) {
    var id = document.getElementById("device-td-id-" + i).innerHTML;
    var name = document.getElementById("device-td-name-" + i).innerHTML;
    var count = document.getElementById("device-td-count-" + i).innerHTML;
    var categories = document.getElementById("device-td-categories-" + i).innerHTML;

    document.getElementById("newId").value = id;
    document.getElementById("newName").value = name;
    document.getElementById("newCount").value = count;
    document.getElementById("newCategories").value = categories;

}