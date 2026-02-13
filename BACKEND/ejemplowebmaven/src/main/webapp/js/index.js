'use strict';

const URL_BASE = 'http://localhost:8080/ejemplowebmaven/api/v2';
const URL_VIDEOS = URL_BASE + '/videos/';
const URL_COMENTARIOS = URL_BASE + '/comentarios/';

window.addEventListener('DOMContentLoaded', async () => {
    await videos();
});

async function videos() {
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

    mostrar('listado');
}

async function video(id) {
    const respuesta = await fetch(URL_VIDEOS + id);
    const video = await respuesta.json();

    console.log(video);

    document.getElementById('titulo').innerText = video.titulo;
    document.getElementById('reproductor').src = video.videoUrl;
    document.getElementById('usuario').innerText = video.usuarioNombre;
    document.getElementById('fecha').innerText = video.fecha;
    document.getElementById('descripcion').innerText = video.descripcion;

    mostrar('video');
	
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
	
	const ul = document.createElement('ul');
	
	ul.className='list-group mt-3 w-100';
	
	rellenarComentarios(respuestas, ul);
		
	liPadre.appendChild(ul);
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