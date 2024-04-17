import { Component, Inject } from '@angular/core';  
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TutorAdService } from '../../services/tutorAd.service';
import { Observable, Subject, of, switchMap, takeUntil } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TutorAd } from '../announcement/tutorAd.interface';
import { User } from '../profile/user.interface';
import { AuthService } from '../../services/auth.service';



@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.scss'
})
export class DialogComponent {

  private readonly _destroying$ = new Subject<void>();
  description !:string;
  form!: FormGroup;
  tutorAds!: Observable<TutorAd[]> 
  user$!: Observable<User>;
  tutorId!:number;
  subject!:string;
  price!:number;


  constructor(public dialogRef: MatDialogRef<DialogComponent>,
              public tutorAdService:TutorAdService,
              private fb: FormBuilder,
              private authService :AuthService
             ) 
  { 
   
    this.form = this.fb.group({
      description: ['', [Validators.required, Validators.minLength(5)]],
      price: [null, [Validators.required, Validators.min(10), Validators.max(1000)]]
    });

    this.authService.currentLoggedUser.pipe(takeUntil(this._destroying$)).subscribe(res => {
      this.tutorId = res.tutorID
      this.subject = res.subject;

    });

  }

  addNewAd() {
    this.dialogRef.close();
    console.log(this.subject)
     this.tutorAdService.addTutorAd(this.tutorId,this.description, this.subject, this.price).pipe(
        takeUntil(this._destroying$)).subscribe();    
  }

  ngOnDestroy(): void {
    this._destroying$.next();
    this._destroying$.complete();
  }

}
