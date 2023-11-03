import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserData } from 'src/app/interfaces/userData.inteface';
import { UserService } from 'src/app/services/user.service';

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
  loading = false;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private userServ:UserService) {

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

    const userUpdate= {
      identificacion: this.infoUser.value.name,
      email: this.infoUser.value.email,
    };
    this.loading = true;

    this.isEditing = false; // Desactiva el modo de edición
  }

  cancelButton() {
    // Restaura los valores originales en el formulario
    this.infoUser.reset(this.infoUser.value); // Esto restablece los campos al estado original
    this.patchValueform();
    this.isEditing = false; // Desactiva el modo de edición
  }

}


