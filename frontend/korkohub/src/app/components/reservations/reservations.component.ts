import { Component, Input, TrackByFunction } from '@angular/core';
import { Observable } from 'rxjs';
import { TutorAd } from '../announcement/tutorAd.interface';
import { MatDialog } from '@angular/material/dialog';
import { TutorAdService } from '../../services/tutorAd.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.scss'
})
export class ReservationsComponent {
  trackById: TrackByFunction<any> | undefined;
  buttonFunction:string = "Wypisz się";

tutorAds: Observable<TutorAd[]> = this.tutorAdService.CurrentSignedTutorAds;

constructor(public dialog: MatDialog,
  private tutorAdService:TutorAdService) {} 


}
