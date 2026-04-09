import { Plato } from "./plato";
import { Usuario } from "./usuario";

export interface Pedido {
    id?: number;
    fechaHora: Date;
    usuario: Usuario;
    platos: Plato[];
}
