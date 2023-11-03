import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { emailValidator } from 'src/app/CustomValidator/customValidator';
import { enterLateral } from 'src/app/animations/animation';
import { User } from 'src/app/interfaces/User.interface';
import { UserService } from 'src/app/services/user.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [enterLateral]
})
export class LoginComponent implements OnInit {
  user!: FormGroup<User | any>;
  eyeStatus = false;
  message!: string;
  isShowMessage = false;

  constructor(
    private userService: UserService,
    private router: Router,
    private fb: FormBuilder,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.user = this.initForm();
  }

  login() {
    this.isShowMessage = true;
    setTimeout(() => (this.isShowMessage = false), 5000);
    const userData: User = this.user.value;
    this.userService.loginUser(userData).subscribe({
      next: (res) => {
        if (res.data.id) {
          localStorage.setItem('tokenCashier', res.data.token)
          this.tokenService.getTokenDecoded();
          this.router.navigate(['user']);
          return;
        }
        this.message = res.data.message;
      },
      error: (err) => (this.message = err.error.detail),
      complete: () => (console.log("Peticion de login finalizada"))
    });
  }

  toRegist() {
    this.router.navigate(['register']);
  }

  initForm(): FormGroup {
    return this.fb.group({
      email: ['', [Validators.required, emailValidator]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }
  validateField(fieldName: string) {
    this.user.get(fieldName)?.updateValueAndValidity();
  }
}
