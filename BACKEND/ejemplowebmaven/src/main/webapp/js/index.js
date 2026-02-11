'use strict';

const URL = 'http://localhost:8080/ejemplowebmaven/api/v2/videos/';

window.addEventListener('DOMContentLoaded', async () => {
    await videos();
});

async function videos() {
    const respuesta = await fetch(URL);
    const videos = await respuesta.json();

    console.log(videos);

    const dl = document.querySelector('dl');

	dl.innerHTML = '';
	
    for (const video of videos) {
        const dt = document.createElement('dt');
        const dd = document.createElement('dd');

        dt.innerText = video.titulo;

        dd.innerHTML = `
			<img width="100" src="${video.imagenUrl}">
			<a href="javascript:video(${video.id})">Ver video</a>
			<p>${video.usuarioNombre}</p>
			<p>${video.fecha}</p>
		`;

        dl.appendChild(dt);
        dl.appendChild(dd);
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
	
	for(const seccion of secciones) {
		if(seccion.id === id) {
			seccion.style.display = null;
		} else {
			seccion.style.display = 'none';
		}
	}
}