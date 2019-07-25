var inputFilter = {};
inputFilter['GET'] = [];
inputFilter['POST'] = ["cityName", "states"];
inputFilter['UPDATE'] = ["cityName", "updateName"];
inputFilter['DELETE'] = ["cityName"];
inputFilter['FIND'] = ["cityName"];

var method;

window.onload = function() {
    method = 'GET';
    enable(method);
};

function enable(inputList) {
    document.getElementById('cityForm').setAttribute("action", "/" + method);
    var fields = document.getElementsByName("inputField");
    var allowed = inputFilter[inputList];
    for (var i = 0; i < fields.length; i++) {
        includeElement(fields[i], allowed.includes(fields[i].id));
    }
}

function includeElement(element, included) {

    var injectedElements = element.getElementsByTagName('input');
    if (injectedElements && injectedElements.length) {
        var component, i = 0;
        while (component = injectedElements[i++]) {
            component.disabled = !included;
        }
    }
    element.disabled = !included
}

function formAction() {
    document.newForm.action = method;
}

function submitForm() {
    document.myForm.submit();
}

function filter() {
    method = document.querySelector('input[name="action"]:checked').value;
    enable(method);
}