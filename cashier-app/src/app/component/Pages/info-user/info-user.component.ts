import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserData } from 'src/app/interfaces/userData.inteface';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.scss']
})
export class InfoUserComponent implements OnInit{
  userData: any;
  isEditing: boolean = false;
  infoUser: FormGroup;
  profileImg: File | any;

  constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute) {
    this.infoUser = this.formBuilder.group({
      name: ['',], 
      numberCard: [''],
      email: [''],
      dni: [''],
    })
  
  }

  ngOnInit(): void {
    // Acceder a los datos de usuario desde history.state
    this.userData = history.state.userData;
    
    // Verificar si se recibieron los datos
    if (this.userData) {
      console.log('Datos de usuario recibidos:', this.userData);
      this.patchValueform()
    } else {
      console.log('No se recibieron datos de usuario.');
    }
    // console.log(typeof(this.profileImg), " &" , this.profileImg);
    this.profileImg = "../../../../assets/user.jpg";
    /*setTimeout(() => {
      // Después de 3 segundos, asigna la ruta de la imagen a profileImg
      this.profileImg = "../../../../assets/tokito.jpg";
    }, 3000); // 3000 milisegundos (3 segundos)*/
  }

  patchValueform():void{
    this.infoUser.patchValue({
      name: this.userData.name,
      numberCard: this.userData.id,
      email: this.userData.sub,
      dni: this.userData.dni,
    });
  }
  editButton(): void {
    this.isEditing = !this.isEditing;
    
  }

   

  saveButton() {
    console.log(this.infoUser);
    /*
    const userRegister: Usuario = {
      identificacion: this.infoUser.value.name,
      email: this.infoUser.value.email,
    };

    this.loading = true;
    
    this.usuarioService.saveUser(userRegister).subscribe((data) => {
      console.log(data);
      this.toastR.success("El usuario "+ userRegister.email +" fue registrado con éxito, por favor revise la bandeja de entrada de su correo y confirme", "Usuario registrado");
      this.router.navigate(['/login']);
      this.loading = false;
    }, error => {
      this.loading = false;
      console.log(error);
      this.toastR.error(error.error.message, "Error registrar usuario!");
      this.registrationForm.reset();
    });*/
    
    this.isEditing = false; // Desactiva el modo de edición
  }

  cancelButton() {
    // Restaura los valores originales en el formulario
    this.infoUser.reset(this.infoUser.value); // Esto restablece los campos al estado original
    this.patchValueform();
    this.isEditing = false; // Desactiva el modo de edición
  }

}


