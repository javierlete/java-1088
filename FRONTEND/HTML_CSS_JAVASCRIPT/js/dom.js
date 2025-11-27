globalThis.addEventListener('DOMContentLoaded', () => {
    const h1 = document.querySelector('h1');

    h1.addEventListener('click', () => {
        h1.innerText = 'Ya no mires la consola, que esto es DOM';
    });

    console.log(h1.innerText);
});
