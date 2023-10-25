import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/User.interface';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  created: boolean = false;
  user!: FormGroup<User|any>;
  users!: User[];

  constructor(
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
  ) {}
  ngOnInit(): void {
    this.user = this.initForm();
    this.getAllData();
  }
  toLogin() {
    this.router.navigate(['login']);
  }

  

  onSubmit(): void {
    const userData: any = this.user.value;
    this.userService.addNewUser(userData).subscribe(
      (response:any) => {
        const message:string = response.data.message;
        if (message == 'Se registró correctamente') {
          this.router.navigate(['/login']);
        }
        console.log(message);
        
        this.created = true;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  getAllData() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
      console.log(this.users)
    })
  }
  initForm(): FormGroup {
    return this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      password2: ['', [Validators.required, Validators.minLength(8)]],
      name: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      phone: ['', [Validators.required, Validators.minLength(7)]],
      address: ['', [Validators.required]],
      birthDate: ['', [Validators.required, Validators.maxLength(4)]],
      dni: ['', [Validators.required, Validators.maxLength(8)]]
    });
  }
}