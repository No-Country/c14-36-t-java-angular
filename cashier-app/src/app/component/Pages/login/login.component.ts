
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/User.interface';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  created: boolean = false;
  user!: FormGroup<User|any>;
  users!: User[];

  constructor( 
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder
    ){}
  
  ngOnInit(): void {
    this.user = this.initForm();
  }
  onSubmit(): void {
    const userData: User = this.user.value;
    this.userService.loginUser(userData).subscribe(
      (response) => {
        console.log(response);
        this.created = true;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
  toRegist(){
    this.router.navigate(["register"])
  }
  login(){
    /* logica de auth */
    this.router.navigate(['user'])
  }
  initForm(): FormGroup {
    return this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
}

