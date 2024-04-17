import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Observable, Subject, of, takeUntil, tap } from 'rxjs';
import { TutorAdService } from '../../services/tutorAd.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit, OnDestroy {
  buttonLoginLogoutText!: string;
  isLogged!: boolean;
  isButtonDisabled: boolean = true;
  private readonly destroy$ = new Subject();
  userId!:number;
  tutorId!:number;
 

  constructor(private router: Router,
              private authService: AuthService,
              private tutorAdService:TutorAdService
    ){}

  ngOnInit(): void {
    this.authService.isUserLogged.pipe(
      takeUntil(this.destroy$)
      ).subscribe(isLoggedIn => {
      this.isLogged = isLoggedIn;
      isLoggedIn
          ? (this.buttonLoginLogoutText = 'wyloguj', this.isButtonDisabled = false)
          : (this.buttonLoginLogoutText = 'login/rejestracja', this.isButtonDisabled = true);
    });

    this.authService.currentLoggedUser.pipe(
      takeUntil(this.destroy$)
      ).subscribe(user => {
      this.userId = user.userID;
      this.tutorId= user.tutorID;
    });



  }

  onLogo(){
    this.router.navigateByUrl('korkohub');
  }

  onProfile(): void {
    this.authService.refreshUser(this.userId,this.tutorId).pipe(takeUntil(this.destroy$)).subscribe()
    this.router.navigateByUrl('korkohub/profil');
  }
  onAnnouncements(): void {
    this.authService.refreshUser(this.userId,this.tutorId).pipe(takeUntil(this.destroy$)).subscribe()
    this.tutorAdService.getTutorAds$().subscribe()
    this.router.navigateByUrl('korkohub/ogloszenia')
  }

  onLoginLogout(): void {
    this.isLogged ? this.authService.logout() : this.router.navigateByUrl('korkohub/login');;
  }

  ngOnDestroy(): void {
    this.destroy$.next(undefined);
    this.destroy$.complete();
  }
  
}
