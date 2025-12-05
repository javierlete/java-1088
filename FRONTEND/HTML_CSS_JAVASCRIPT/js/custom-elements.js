class Titulo extends HTMLElement {
    connectedCallback() {
        let texto = this.getAttribute('texto');

        texto = texto || 'TÍTULO POR DEFECTO';

        this.innerHTML = `<h1>${texto}<h1>`;
    }
}

customElements.define("jl-titulo", Titulo);
