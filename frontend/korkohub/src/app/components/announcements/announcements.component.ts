import { Component, inject } from '@angular/core';
import { TutorService } from '../../services/tutor.service';
import { Tutor } from '../announcement/tutorInterface';
import { Observable, Subject, takeUntil } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';
import { TutorAd } from '../announcement/tutorAd.interface';
import { TutorAdService } from '../../services/tutorAd.service';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrl: './announcements.component.scss'
})
export class AnnouncementsComponent {

  isLoggedTuttor:boolean=false;
  isLogged:boolean=false;
  buttonFunction:string = "Zapisz się";
  

  tutorAds!: Observable<TutorAd[]>

  private readonly destroy$ = new Subject();

  constructor(public dialog: MatDialog,
    private tutorAdService:TutorAdService,
    private authService:AuthService) 
    {
 
      this.tutorAds = this.tutorAdService.CurrentTutorAds
      this.authService.currentLoggedUser.pipe(takeUntil(this.destroy$)).subscribe(res => {
        if(res.tutorID > 0){
          this.isLoggedTuttor = true;
        }else{
          this.isLoggedTuttor = false;
        }
      });

      this.authService.isUserLogged.pipe(
        takeUntil(this.destroy$)
        ).subscribe(isLoggedIn => {
        this.isLogged = isLoggedIn;
      });
    } 

    
  openDialog(): void { 
   let dialogRef = this.dialog.open(DialogComponent, {
    height: '500px',
    width: '600px',
    });

  } 



  ngOnDestroy() {
    this.destroy$.complete();
  }


}
