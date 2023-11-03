import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/User.interface';
import { UserService } from 'src/app/services/user.service';
import {
  birthValidator,
  dniValidator,
  emailValidator,
  locateValidator,
  passValidator,
  phoneValidator,
  repeatPassValidator,
  textValidator,
} from 'src/app/CustomValidator/customValidator';
import { enterLateral } from 'src/app/animations/animation';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  animations:[enterLateral]
})
export class RegisterComponent implements OnInit {
  eyeStatus = false;
  eyeStatus2 = false;

  repeatPass = false;
  message!:string;
  isShowMessage = false;
  messageStatus = false;

  created = false;
  user!: FormGroup<User | any>;
  // users!: User[];

  constructor(
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {}
  ngOnInit(): void {
    this.user = this.initForm();
    // this.getAllData();
  }
  toLogin() {
    this.router.navigate(['login']);
  }

  onSubmit(): void {
    this.isShowMessage = true;
    setTimeout(()=>(this.isShowMessage = false),5000)
    let userData = this.user.value as User;
    userData = {
      ...userData,
      name:userData.name.trim().toLowerCase(),
      lastName:userData.lastName.trim().toLowerCase(),
      address:userData.address.trim().toLowerCase(),
      email:userData.email.trim()
    }
    this.userService.addNewUser(userData).subscribe({
      next:()=>{
        this.messageStatus = true;
        this.message = 'Registro exitoso, verifique la cuenta desde el correo';
        setTimeout(()=>(this.router.navigate(['login'])),3000)
      },
      error:(err)=>{
        this.messageStatus = false;
        this.message = err.error.detail;
      },
      complete:()=>(console.log("peticion realizada desde el register"))
    });
  }

  initForm(): FormGroup {
    return this.fb.group({
      email: ['', [Validators.required, emailValidator]],
      password: ['', [Validators.required, passValidator]],
      password2: ['', [Validators.required, repeatPassValidator]],
      name: ['', [Validators.required, textValidator]],
      lastName: ['', [Validators.required, textValidator]],
      phone: ['', [Validators.required, phoneValidator]],
      address: ['', [Validators.required, locateValidator]],
      birthDate: ['', [Validators.required, birthValidator]],
      dni: ['', [Validators.required, dniValidator]],
    });
  }
  validateField(fieldName: string) {
    this.user.get(fieldName)?.updateValueAndValidity();
  }

}
