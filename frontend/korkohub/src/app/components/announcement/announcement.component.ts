import { Component, Input, OnInit, inject, input } from '@angular/core';
import { Tutor } from './tutorInterface';
import { AuthService } from '../../services/auth.service';
import { Observable, Subject, takeUntil, tap } from 'rxjs';
import { TutorService } from '../../services/tutor.service';
import { ChangeDetectorRef } from '@angular/core';
import { TutorAd } from './tutorAd.interface';
import { TutorAdService } from '../../services/tutorAd.service';
import { User } from '../profile/user.interface';
@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrl: './announcement.component.scss'
})
export class AnnouncementComponent implements OnInit {
    @Input("tutorAd") tutorAd: TutorAd | undefined = undefined;
    @Input () buttonFunction !:string;

    tutorAdDetails: any;
    name!:string;
    surname!:string;
    tutorID!:number;
    tutoringAdID!:number;
    user$!: Observable<User>;
    isLoggedUser:boolean = false;
    isLogged:boolean = false;
    userId!: number;


    private readonly destroy$ = new Subject();

    constructor(private cdr: ChangeDetectorRef,
      private tutorAdService:TutorAdService,
      private tutorService:TutorService,
      private authService:AuthService) {}

    ngOnInit(): void {
      this.user$ = this.authService.currentLoggedUser;
      this.onIsLogged();
      this.getUserId()
      this.tutorAdService.getTutorAd$(this.tutorAd?.tutoringAdID ?? 0).subscribe(res => {
        this.tutorAdDetails =res
        this.tutorID = res.tutorID
        this.tutoringAdID = res.tutoringAdID
        this.tutorService.getTutor$(res.tutorID).subscribe(Details =>{
          this.name = Details.name
          this.surname = Details.surname
        })

      })
    }

    getUserId(){
      this.authService.currentLoggedUser.pipe(takeUntil(this.destroy$)).subscribe(user => {
        this.userId = user.userID;
      });
    }

    onIsLogged()
    {
      this.authService.currentLoggedUser.pipe(takeUntil(this.destroy$)).subscribe(res => {
        if(res.userID > 0){
          this.isLoggedUser = true;
        }else if(this.buttonFunction=="Usuń")
        {
          this.isLoggedUser = true;
        }
        else{
          this.isLoggedUser = false;
        }
      });
      this.authService.isUserLogged.pipe(
        takeUntil(this.destroy$)
        ).subscribe(isLoggedIn => {
        this.isLogged = isLoggedIn;
      });
    }

    onSign(){

    if(this.buttonFunction=="Zapisz się"){
      this.tutorAdService.signIn$(this.userId, this.tutoringAdID).subscribe();
    }else if(this.buttonFunction=="Wypisz się"){
      this.tutorAdService.signOut$(this.userId, this.tutoringAdID).subscribe();
    }
    else if(this.buttonFunction=="Usuń"){
      this.tutorAdService.deleteTutorAd(this.tutoringAdID).subscribe()
    }
    
  }

}
