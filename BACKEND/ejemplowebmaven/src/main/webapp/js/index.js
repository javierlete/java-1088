'use strict';

const URL = 'http://localhost:8080/ejemplowebmaven/api/v2/videos/';

window.addEventListener('DOMContentLoaded', async () => {
    await videos();
});

async function videos() {
    const respuesta = await fetch(URL);
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
    const respuesta = await fetch(URL + id);
    const video = await respuesta.json();

    console.log(video);

    document.getElementById('titulo').innerText = video.titulo;
    document.getElementById('reproductor').src = video.videoUrl;
    document.getElementById('usuario').innerText = video.usuarioNombre;
    document.getElementById('fecha').innerText = video.fecha;
    document.getElementById('descripcion').innerText = video.descripcion;

    mostrar('video');
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