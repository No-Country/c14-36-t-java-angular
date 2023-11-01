import { AbstractControl } from '@angular/forms';

export function textValidator(controller: AbstractControl) {
  const specialCharacter = /[^a-zA-Z0-9á-ýÁ-Ý\s]/g;
  const spaces = /(\s{2,})/g;

  const value = controller.value as string;

  if (value.length < 3) {
    return { minLength: true, message: 'Mínimo 3 carácteres.' };
  }
  if (specialCharacter.test(value)) {
    return {
      invalidChar: true,
      message: 'No se permite carácteres especiales.',
    };
  }
  if (/\d/g.test(value)) {
    return { hasNumber: true, message: 'No se permiten números.' };
  }
  if (spaces.test(value)) {
    return { hasSpaces: true, message: 'Espacios excesivos' };
  }
  if (value.length > 85) {
    return { maxLength: true, message: 'Máximo 85 carácteres.' };
  }

  return null
};
export function textValidatorToFilter(controller:AbstractControl){
  const specialCharacter = /[^a-zA-Z0-9á-ýÁ-Ý\s]/g;
  const spaces = /(\s{2,})/g

  const value = controller.value as string;

  if(value.length <4){
    return {minLength:true, message:'Mínimo 4 carácteres.'}
  }
  if(specialCharacter.test(value)){
    return {invalidChar:true, message:'No se permite carácteres especiales.'}
  }
  if(/\d/g.test(value)){
    return {hasNumber:true, message: 'No se permiten números.'}
  }
  if(spaces.test(value)){
    return {hasSpaces:true, message: 'Espacios excesivos'}
  }
  if(value.length > 50){
    return {maxLength:true, message:'Máximo 50 carácteres.'}
  }
  return null
};

export function dniValidator(controller: AbstractControl) {
  const value = controller.value as string;

  if (value.length < 8) {
    return { minLength: true, message: 'Mínimo 8 carácteres.' };
  }
  if (/[^\d]/.test(value)) {
    return {
      invalidChar: true,
      message: 'No se permite letras ni caracteres.',
    };
  }
  if (value.length > 8) {
    return { maxLength: true, message: 'Máximo 8 carácteres.' };
  }
  return null;
}

export function phoneValidator(controller: AbstractControl) {
  const value = controller.value as string;

  if (value.length < 8) {
    return { minLength: true, message: 'Mínimo 8 dígitos.' };
  }
  if (/[^\d]/.test(value)) {
    return {
      invalidChar: true,
      message: 'No se permite letras ni carácteres.',
    };
  }
  if (value.length > 12) {
    return { maxLength: true, message: 'Máximo 12 carácteres.' };
  }
  return null;
}

export function emailValidator(controller:AbstractControl){
  const emailPattern = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/
  const value = controller.value as string;

  if (!emailPattern.test(value)) {
    return { emailInvalid: true, message: 'Formato inválido.' };
  }
  return null;
}

export function birthValidator(controller: AbstractControl) {
  const value = controller.value as string;
  const birthDate = new Date(value);
  const today = new Date();
  const diffTime = today.getTime() - birthDate.getTime();
  const diffTimeYears = diffTime / (1000 * 3600 * 24 * 365);

  if (diffTimeYears < 18) {
    return { minor: true, message: 'Solo mayores de 18 años.' };
  }
  return null;
}

export function locateValidator(controller: AbstractControl) {
  const value = controller.value as string;
  const locatePattern = /[\.,+=\[\]{}]/;
  if (value.length < 5) {
    return { minLength: true, message: 'Mínimo 5 carácteres.' };
  }
  if (locatePattern.test(value)) {
    return { invalidChar: true, message: 'No se permite + . , [] {} \\' };
  }
  return null;
}

// export function passValidator(controller:AbstractControl){
//   const value = controller.value as string;
//   const passPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-_+|!@#$%^&*\.])[A-Za-z\d-_+|¡!@#$%^&*\.]+$/g;
//   if(value.length < 9){
//     return {minLength:true, message:'Mínimo 9 carácteres.'}
//   }
//   if(/^[^\d]+$/.test(value)){
//     return {notNumber:true, message:'Minimo 1 número.'}
//   }
//   if(/^[^A-Z]+$/.test(value)){
//     return {notUpper:true, message:'Minimo 1 mayúscula.'}
//   }
//   if(/^[^a-z]+$/.test(value)){
//     return {notLower:true, message:'Minimo 1 minuscula.'}
//   }
//   if(/^[^-_+|¡!@#$%^&*\.]+$/.test(value)){
//     return {notChar:true, message:'Minimo 1 cáracter -_+|¡!@#$%^&*. .'}
//   }
//   if(!passPattern.test(value)){
//     return {invalid:true, message:'Formato inválido.'}
//   }
//   if(value.length > 20){
//     return {maxLength:true, message:'Máximo 20 carácteres.'}
//   }
//   return null
// };
export function passValidator(controller: AbstractControl) {
  const value = controller.value as string;
  const passPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]+$/g;
  if (value.length < 9) {
    return { minLength: true, message: 'Mínimo 9 carácteres.' };
  }
  if (/^[^\d]+$/.test(value)) {
    return { notNumber: true, message: 'Minimo 1 número.' };
  }
  if (/^[^A-Z]+$/.test(value)) {
    return { notUpper: true, message: 'Minimo 1 mayúscula.' };
  }
  if (/^[^a-z]+$/.test(value)) {
    return { notLower: true, message: 'Minimo 1 minuscula.' };
  }
  if (!passPattern.test(value)) {
    return { invalid: true, message: 'Formato inválido.' };
  }
  if (value.length > 20) {
    return { maxLength: true, message: 'Máximo 20 carácteres.' };
  }
  return null;
}

export function repeatPassValidator(controller: AbstractControl) {
  const pass: string = controller.root.get('password')?.value;
  const repeatPass: string = controller.value;

  if (pass !== repeatPass) {
    return { noMatch: true, message: 'Las contraseñas deben coincidir.' };
  }
  return null;
}
