/* eslint-disable */

'use strict';

var URL = 'http://localhost:3000/productos';

$(function () {
    mostrarTabla();
});

function anyadir() {
    console.log('Añadir');

    $('form')[0].reset();

    mostrarFormulario();
}

function editar(id) {
    console.log('Editar', id);

    $.getJSON(URL + '/' + id, function (producto) {
        console.log(producto);

        $('#id').val(producto.id);
        $('#nombre').val(producto.nombre);
        $('#precio').val(producto.precio);

        mostrarFormulario();
    }).fail(function (jqXHR, error, errorThrown) {
        alert('Ha habido un error al recibir los datos del producto');

        console.error(error);
    });
}

function borrar(id) {
    console.log('Borrar', id);

    if (!confirm('¿Estás seguro de que quieres borrar el producto id ' + id + '?')) {
        return;
    }

    $.ajax(URL + '/' + id, { method: 'DELETE' })
        .done(function (data, textStatus, jqXHR) {
            console.log(textStatus);

            mostrarTabla();
        })
        .fail(function (jqXHR, error, errorThrown) {
            console.error(error);
        });
}

function guardar() {
    var producto = {
        nombre: $('#nombre').val(),
        precio: $('#precio').val(),
    };

    var id = $('#id').val();

    var url, metodo;

    if (id) {
        producto.id = id;

        url = URL + '/' + id;
        metodo = 'PUT';
    } else {
        url = URL;
        metodo = 'POST';
    }

    $.ajax(url, {
        method: metodo,
        headers: { 'content-type': 'application/json' },
        data: JSON.stringify(producto),
    })
        .done(function (data) {
            console.log(data);
            mostrarTabla();
        }).fail(function (jqXHR, error, errorThrown) {
            console.error(error);
        });
}

function mostrarFormulario() {
    $('table').hide();
    $('form').show();
}

function mostrarTabla() {
    recargarTabla();

    $('table').show();
    $('form').hide();
}

function recargarTabla() {
    $.getJSON(URL, function (productos) {
        $('tbody').empty();

        $.each(productos, function () {
            $('<tr>')
                .html(
                    '<th>' + this.id + '</th>' +
                    '<td>' + this.nombre + '</td>' +
                    '<td>' + this.precio + '</td>' +
                    '<td>' +
                    '<a href="javascript:editar(' + this.id + ')">Editar</a>' +
                    '<a href="javascript:borrar(' + this.id + ')">Borrar</a>' +
                    '</td>')
                .appendTo('tbody');
        });
    });
}

/* eslint-enable */
