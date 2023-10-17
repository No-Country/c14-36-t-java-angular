import { Component } from '@angular/core';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
<<<<<<< HEAD
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
    const userData: User = this.user.value;
    this.userService.addNewUser(userData).subscribe(
      (response) => {
        console.log(response);
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
=======
export class RegisterComponent {

}
>>>>>>> 5b3ae6751dfd707332e1e84cf7504619795df89e
