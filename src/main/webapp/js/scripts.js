function validate() {
    var result = true;
    var name = $('#name').val();
    var desc = $('#description').val();
    if (name == '') {
        result = false;
        alert('Please, enter your Item');
    }
    if (desc == '') {
        result = false;
        alert('Please, enter your Description');
    }
    return result;
}

function fillItems() {
    $.ajax({
        url: "/items",
        method: "POST",
        complete: function (response) {
            var items = JSON.parse(response.responseText);
            $("#dynamic td").parent().remove();
            for (var i = 0; i < items.length; i++) {
                var id = items[i].id;
                var name = items[i].name;
                var desc = items[i].desc;
                var created = items[i].created;
                var status = items[i].done;
                var checked = '';
                if (status == true) {
                    if ($('#showAll').is(':checked')) {
                        checked = 'checked disabled';
                    } else {
                        continue;
                    }
                }
                $('#dynamic tr:last').after(
                    '<tr>' +
                    '<td>' + id + '</td>' +
                    '<td>' + name + '</td>' +
                    '<td>' + desc + '</td>' +
                    '<td>' + created + '</td>' +
                    '<td>' +
                    '<input type="checkbox" ' +
                    'name="done' + id + '" ' +
                    'value ="' + id + '" ' +
                    'onchange="$(done(this.value))" ' + checked +
                    '>' +
                    '</td>' +
                    '</tr>'
                );
            }
        }
    });
}

function createItem() {
    if (validate() == true) {
        var name = $('#name').val();
        var desc = $('#description').val();
        $.ajax({
            url: "/add",
            method: "POST",
            data: {
                item: name,
                desc: desc
            },
            complete: function (response) {
                var item = JSON.parse(response.responseText);
                $('#dynamic tr:last').after(
                    '<tr>' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.name + '</td>' +
                    '<td>' + item.desc + '</td>' +
                    '<td>' + item.created + '</td>' +
                    '<td>' +
                    '<input type="checkbox" ' +
                    'name="done' + id + '" ' +
                    'value ="' + id + '" ' +
                    'onchange="$(done(this.value))">' +
                    '</td>' +
                    '</tr>'
                );
            }
        });
    }
}

function done(id) {
    $.ajax({
        url: "/done",
        method: "POST",
        data: {
            id: id
        },
        complete() {
            fillItems();
        }
    });
}

function addItem() {
    createItem();
    fillItems();
}