import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { SearchService } from '../../services/search.service';
import { Observable, Subject, takeUntil, tap } from 'rxjs';
import { User } from './user.interface';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { TutorAdService } from '../../services/tutorAd.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TutorService } from '../../services/tutor.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})

export class ProfileComponent implements OnInit, OnDestroy {

  private readonly _destroying$ = new Subject<undefined>;

  user$!: Observable<User>;
  balance!:number ;
  userId!:number ;
  isUserLogged:boolean=false;
  isTutorLogged:boolean=false;

  userForm = new FormGroup({
    userID: new FormControl(0),
    name: new FormControl('',[Validators.required] ),
    surname: new FormControl('',[Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl(''),
    balance: new FormControl({value: 0, disabled: true}, [Validators.required])
  })
  tutorForm = new FormGroup({
    tutorID: new FormControl(0),
    name: new FormControl('',[Validators.required] ),
    surname: new FormControl('',[Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl(''),
    balance: new FormControl({value: 0, disabled: true}, [Validators.required]),
    subject: new FormControl('', [Validators.required])
  })
  tutorId!:number;
  constructor(private searchService: SearchService,
              private authService: AuthService,
              private router:Router,
              private tutorAdService:TutorAdService,
              private tutorService: TutorService
              ) {}

  updateUser() : void {
    console.log("UpdateUser", this.userForm.getRawValue())
    const user = this.userForm.getRawValue() as Omit<User, 'tutorID' | 'subject'>
    this.tutorService.updateUser(user)
  }

  updateTutor() : void {
      const tutor = this.tutorForm.getRawValue() as Omit<User, 'userID'>
      this.tutorService.updateTutor(tutor)
  }

  ngOnInit(): void {
    
    this.user$ = this.authService.currentLoggedUser;

    this.authService.currentLoggedUser.pipe(takeUntil(this._destroying$)).subscribe(res => {
      this.userId = res.userID;
      this.tutorId = res.tutorID;
      if(res.userID>0)
      {
        this.isUserLogged = true
        this.isTutorLogged = false
        this.userForm.setValue({
          ...res, 
          balance: res.balance
        });
      } else {
        this.isTutorLogged =true
        this.isUserLogged = false
        this.tutorForm.setValue({
          ...res, 
          balance: res.balance
        });
      }
    });
  }

  onDeleteUser() {
    const id = this.userForm.getRawValue().userID;
    if(id!==null) {
      this.tutorService.deleteUser(id)
      this.authService.logout();
    }
  }

  onDeleteTutor() {
    const id = this.tutorForm.getRawValue().tutorID;
    if(id!==null) {
      this.tutorService.deleteTutor(id)
      this.authService.logout();
    }
  }

  onSigned(){
    this.tutorAdService.getSignedAds$(this.userId).subscribe()
    this.router.navigateByUrl("korkohub/rezerwacje");
  }

  onProfile(){

    this.router.navigateByUrl("korkohub/profil")
  }

  myAds(){
    this.tutorAdService.getAddedAdsByTutor$(this.tutorId).subscribe()
    this.router.navigateByUrl("korkohub/dodane_ogloszenia")
  }


  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
