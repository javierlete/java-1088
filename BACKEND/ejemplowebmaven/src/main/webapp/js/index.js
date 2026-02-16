'use strict';

const URL_BASE = 'http://localhost:8080/ejemplowebmaven/api/v2';
const URL_VIDEOS = URL_BASE + '/videos/';
const URL_COMENTARIOS = URL_BASE + '/comentarios/';

window.addEventListener('DOMContentLoaded', async () => {
    menuSecundario();

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
	
	const usuario = getUsuarioAutenticado();

    if (usuario) {
        const tarjeta = document.createElement('div');

        tarjeta.className = 'col';

        tarjeta.innerHTML = `
				<div class="col">
					<form class="card h-100" action="javascript:guardar()">
						<div class="ratio ratio-16x9">
							<img
								src="imgs/plus-circle-fill.svg"
								class="card-img-top p-3" alt="...">
						</div>
						<input name="imagen" class="form-control" placeholder="URL imagen">
						<div class="card-body">
							<h5 class="card-title">
								<input name="titulo" class="form-control" placeholder="Título">
							</h5>
							<p class="card-text">
								<textarea name="descripcion" class="form-control"
									placeholder="Descripción"></textarea>
							</p>
							<input name="video" class="form-control" placeholder="URL video">
						</div>
						<div class="card-footer">
							<small
								class="text-body-secondary d-flex justify-content-between align-items-baseline">
								<span>${usuario.nombre}</span> 
								<span class="card-text" style="z-index: 2">
									<button class="btn btn-outline-primary">
										<i class="bi bi-floppy2-fill"></i>
									</button>
								</span>
							</small>
						</div>
					</form>
				</div>`;

        tarjetas.appendChild(tarjeta);
    }

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
					<small class="text-body-secondary d-flex justify-content-between align-items-baseline">
						${video.fecha} ${video.usuarioNombre}
						${usuario?.id === video.usuarioId ? `<span class="card-text">
							<a class="btn btn-outline-danger" href="javascript:borrar(${video.id})">
								<i class="bi bi-trash-fill"></i>
							</a>
						</span>` : ''}
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

    const liPadre = document.getElementById('c' + id);

    const ulHijo = liPadre.querySelector('ul');

    if (ulHijo) {
        liPadre.removeChild(ulHijo);

        return;
    }

    const respuestaFetch = await fetch(URL_COMENTARIOS + id + '/respuestas');
    const respuestas = await respuestaFetch.json();

    const ul = document.createElement('ul');

    ul.className = 'list-group mt-3 w-100';

    rellenarComentarios(respuestas, ul);

    liPadre.appendChild(ul);
}

async function login() {
    const form = document.querySelector('#login form');

    const usuario = {
        email: form.email.value,
        password: form.password.value
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

    localStorage.setItem('usuario', JSON.stringify(usuarioRespuesta));

    menuSecundario();

    videos();
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

function menuSecundario() {
    const ul = document.querySelector('#navbarSupportedContent ul:last-child');

    console.log(ul);

    ul.innerHTML = '';

    const li = document.createElement('li');

    if (autenticado()) {
        const usuario = getUsuarioAutenticado();

        li.className = 'navbar-text';

        li.innerText = usuario.nombre + ' ' + usuario.rol.nombre;

        ul.appendChild(li);

        const liLogout = document.createElement('li');

        liLogout.className = 'nav-item';

        liLogout.innerHTML = `<a class="nav-link" href="javascript:logout()">Cerrar sesión</a>`;

        ul.appendChild(liLogout);
    } else {
        li.className = 'nav-item';

        li.innerHTML = `<a class="nav-link" href="javascript:mostrar('login')">Iniciar sesión</a>`;

        ul.appendChild(li);
    }
}

function logout() {
    localStorage.clear();

    menuSecundario();

    videos();
}

async function guardar() {
    console.log('guardar');

    const form = document.querySelector('#listado form');

    const video = {
        titulo: form.titulo.value,
        imagenUrl: form.imagen.value,
        descripcion: form.descripcion.value,
        videoUrl: form.video.value,
        usuario: getUsuarioAutenticado(),
    }

    console.log(video);

    const respuesta = await fetch(URL_VIDEOS, {
        method: 'POST',
        body: JSON.stringify(video),
        headers: {
            'Content-type': 'application/json'
        },
    });

    const videoGuardado = await respuesta.json();

    console.log(videoGuardado);

    videos();
}

function getUsuarioAutenticado() {
    return JSON.parse(localStorage.getItem('usuario'));
}

function autenticado() {
    return !!getUsuarioAutenticado();
}

async function borrar(id) {
	const respuesta = await fetch(URL_VIDEOS + id, { method: 'DELETE' });
	
	console.log(respuesta);
	
	videos();
}