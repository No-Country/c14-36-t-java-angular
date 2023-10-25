import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.scss']
})
export class InfoUserComponent implements OnInit{
  infoUser: FormGroup;
  username: String = "Juanito Alimaña Navaja";
  cardNumber: Number = 12341234123412341234;
  email: String = "juanitoalimaña@gmail.com";
  postalCode: Number = 201020;
  profileImg: File | any;

  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.infoUser = this.formBuilder.group({
      title: ['', [Validators.required]],
    })
  }

  ngOnInit(): void {
    console.log(typeof(this.profileImg), " &" , this.profileImg);
    this.profileImg = "../../../../assets/user.jpg";
    /*setTimeout(() => {
      // Después de 3 segundos, asigna la ruta de la imagen a profileImg
      this.profileImg = "../../../../assets/tokito.jpg";
    }, 3000); // 3000 milisegundos (3 segundos)*/
  }
  
  
  infoUserSave():void{
    console.log("hello");
  }
}


