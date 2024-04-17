import { Component, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Subject, takeUntil } from 'rxjs';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnDestroy {
  loginForm!: FormGroup;
  registerForm!: FormGroup;
  isLogin: boolean = true;
  isLoginError: boolean = false;
  isRegisterError: boolean = false;
  selectedRole: string = 'user';  
  defaultRole: string = 'user';
  isTutor:boolean = true;

  private readonly _destroying$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {
    this.createForms();
    this.registerForm.controls['subject'].disable();
  }

  onRoleChange() {
    if (this.selectedRole === 'user') {
      this.registerForm.controls['subject'].disable();
    } else {
      this.registerForm.controls['subject'].enable();
    }
  }

  createForms() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });

    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      subject: ['', [Validators.required]]
    });
  }

  onLogin() {
    this.isLoginError = false;
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).pipe(
        takeUntil(this._destroying$)
      ).subscribe(res => { 
        if(res == -1){
          this.isLoginError = true;
        } else {
          this.router.navigateByUrl("korkohub/profil");
        }
      })
    }
  }

  onRegister() {
    this.isRegisterError = false;
    if (this.registerForm.valid) {
      const { name, surname, email, password , subject} = this.registerForm.value;
      this.authService.register(name, surname, email, password, this.selectedRole, subject).pipe(
        takeUntil(this._destroying$)
      ).subscribe(res => {
        if (res === -1) {
          this.isRegisterError = true;
        } else {
          this.router.navigateByUrl('korkohub/profil');
        }
      });
    }
  }

  onChange() {
    this.isLogin = !this.isLogin;
  }



  ngOnDestroy(): void {
    this._destroying$.next();
    this._destroying$.complete();
  }
}
