import { TipoComida } from "./tipo-comida";

export interface Plato {
    id?: number;
    nombre: string;
    precio: number;
    descripcion?: string | null;

    tipoComida: TipoComida;
}
