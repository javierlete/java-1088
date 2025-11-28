globalThis.addEventListener('DOMContentLoaded', async () => {
    const respuesta = await fetch('json/opciones.json');
    const datos = await respuesta.json();

    console.log(datos);

    const select = document.querySelector('select');

    for(const opcion of datos) {
        const option = document.createElement('option');
        
        option.value = opcion.id;
        option.innerText = opcion.texto;

        select.appendChild(option);
    }
});