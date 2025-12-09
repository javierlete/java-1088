class LabelInput extends HTMLElement {
    connectedCallback() {
        const etiqueta = this.getAttribute('etiqueta');
        const id = this.getAttribute('id');
        const nombre = this.getAttribute('nombre') ? this.getAttribute('nombre') : id;
        const tipo = this.getAttribute('tipo');
        const deshabilitado = this.getAttribute('deshabilitado');

        const atributos = `type="${tipo}" id="${id}" name="${nombre}" ${deshabilitado ? 'disabled' : ''}`;

        if (tipo === 'radio' || tipo === 'checkbox') {
            this.innerHTML = `
                <div class="form-check ${deshabilitado ? 'disabled' : ''}">
                    <label class="form-check-label">
                        <input class="form-check-input" ${atributos}>
                        ${etiqueta}
                    </label>
                </div>
            `;
            return;
        }

        this.innerHTML = `
            <div class="row mb-3">
                <label for="${id}" class="col-form-label">${etiqueta}</label>
                <div class="col-sm-10">
                    <input class="form-control" ${atributos}>
                </div>
            </div>
        `;
    }
}

customElements.define('jl-labelinput', LabelInput);

class Boton extends HTMLElement {
    connectedCallback() {
        const texto = this.getAttribute('texto');

        this.innerHTML = `<button class="btn btn-primary">${texto}</button>`;
    }
}

customElements.define('jl-boton', Boton);