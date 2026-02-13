'use strict';

const URL_BASE = 'http://localhost:8080/ejemplowebmaven/api/v2';
const URL_VIDEOS = URL_BASE + '/videos/';
const URL_COMENTARIOS = URL_BASE + '/comentarios/';

window.addEventListener('DOMContentLoaded', async () => {
    const usuario = JSON.parse(localStorage.getItem('usuario'));

    if(usuario) {
		menuSecundario(usuario);
	} else {
		logout();
	}

    await videos();
});

async function videos() {
    // TODO: añadir un placeholder para indicar que está cargando
    mostrar('listado');

    const respuesta = await fetch(URL_VIDEOS);
    const videos = await respuesta.json();

    console.log(videos);

    const tarjetas = document.querySelector('#tarjetas-videos');

    tarjetas.innerHTML = '';

    for (const video of videos) {
        const tarjeta = document.createElement('div');
        tarjeta.className = 'col';

        tarjeta.innerHTML = `
			<div class="card h-100">
				<div class="ratio ratio-16x9">
					<img src="${video.imagenUrl}" class="card-img-top" alt="...">
				</div>
				<div class="card-body">
					<h5 class="card-title">
						${video.titulo}
					</h5>
					<a class="btn btn-primary stretched-link"
						href="javascript:video(${video.id})">Ver video ${video.titulo}</a>
				</div>
				<div class="card-footer" style="z-index: 2">
					<small
						class="text-body-secondary d-flex justify-content-between align-items-baseline">
						${video.fecha} ${video.usuarioNombre}
					</small>
				</div>
			</div>

		`;

        tarjetas.appendChild(tarjeta);
    }

}

async function video(id) {
    mostrar('video');

    const respuesta = await fetch(URL_VIDEOS + id);
    const video = await respuesta.json();

    console.log(video);

    document.getElementById('titulo').innerText = video.titulo;
    document.getElementById('reproductor').src = video.videoUrl;
    document.getElementById('usuario').innerText = video.usuarioNombre;
    document.getElementById('fecha').innerText = video.fecha;
    document.getElementById('descripcion').innerText = video.descripcion;

    const ul = document.getElementById('comentarios');

    const respuestaComentarios = await fetch(URL_VIDEOS + id + '/comentarios')
    const comentarios = await respuestaComentarios.json();

    console.log(comentarios);

    rellenarComentarios(comentarios, ul);
}

function rellenarComentarios(comentarios, ul) {
    for (const c of comentarios) {
        const li = document.createElement('li');

        li.id = 'c' + c.id;
        li.className = 'pt-4 pb-5 px-4 list-group-item d-flex flex-wrap align-items-stretch';

        li.innerHTML = `
			<img width="25" class="rounded-circle"
			src="https://picsum.photos/25/25" alt="">
			<div
				class="flex-grow-1 d-flex justify-content-start align-items-center">
				<div class="ms-2 me-auto">
					<div class="fw-bold">${c.usuarioNombre}</div>
				</div>
				<small class="text-secondary"><javatime:format
						value="${c.fecha}" style="MS" /></small>
			</div>
			<div class="mt-2 w-100 lh-lg">${c.texto}</div>
			<div class="w-100">
				<a class="badge rounded-pill text-bg-secondary link-underline link-underline-opacity-0"
					href="javascript:respuestas(${c.id})">${c.respuestas} respuestas</a>
			</div>
		`;

        ul.appendChild(li);
    }
}

async function respuestas(id) {
    console.log(id);

    const respuestaFetch = await fetch(URL_COMENTARIOS + id + '/respuestas');
    const respuestas = await respuestaFetch.json();

    const liPadre = document.getElementById('c' + id);

    const ulHijo = liPadre.querySelector('ul');

    if (ulHijo) {
        liPadre.removeChild(ulHijo);

        return;
    }

    const ul = document.createElement('ul');

    ul.className = 'list-group mt-3 w-100';

    rellenarComentarios(respuestas, ul);

    liPadre.appendChild(ul);
}

async function login() {
    const usuario = {
        email: document.forms[0].email.value,
        password: document.forms[0].password.value
    };

    console.log('Petición login', usuario);

    const respuesta = await fetch(URL_BASE + '/login', {
        method: 'POST',
        body: JSON.stringify(usuario),
        headers: {
            'Content-type': 'application/json'
        },
    });

    if (respuesta.status === 401) {
        // TODO: Usar una alerta de Bootstrap
        alert('Login incorrecto');
        return;
    }

    const usuarioRespuesta = await respuesta.json();

    console.log('Respuesta login', usuarioRespuesta);

    menuSecundario(usuarioRespuesta);

    localStorage.setItem('usuario', JSON.stringify(usuarioRespuesta));

    mostrar('listado');
}

function mostrar(id) {
    const secciones = document.querySelectorAll('section');

    for (const seccion of secciones) {
        if (seccion.id === id) {
            seccion.style.display = null;
        } else {
            seccion.style.display = 'none';
        }
    }
}

function menuSecundario(usuario) {
    const ul = document.querySelector('#navbarSupportedContent ul:last-child');

    console.log(ul);

    ul.innerHTML = '';

    const li = document.createElement('li');

    li.className = 'navbar-text';

    li.innerText = usuario.nombre + ' ' + usuario.rol.nombre;

    ul.appendChild(li);

    const liLogout = document.createElement('li');

    liLogout.className = 'nav-item';

    liLogout.innerHTML = `<a class="nav-link" href="javascript:logout()">Cerrar sesión</a>`;

    ul.appendChild(liLogout);
}

function logout() {
    localStorage.clear();

    const ul = document.querySelector('#navbarSupportedContent ul:last-child');

    console.log(ul);

    ul.innerHTML = '';

    const li = document.createElement('li');

    li.className = 'nav-item';

    li.innerHTML = `<a class="nav-link" href="javascript:mostrar('login')">Iniciar sesión</a>`;

    ul.appendChild(li);
}