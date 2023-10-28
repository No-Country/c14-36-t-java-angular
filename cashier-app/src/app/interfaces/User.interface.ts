// export interface User {
//     id?: any|undefined;
//     nombre: string|undefined;
//     apellido: string;
//     dni: string;
//     telefono: string;
//     email: string;
//     fechaNac: string;
//     direccion: string;
//     password: string;
//     password2: string;
// }

export interface User {
    id?: string;
    name: string;
    lastName: string;
    email: string;
    dni: string;
    phone: string;
    password: string;
    address: string;
    birthDate: Date;
}