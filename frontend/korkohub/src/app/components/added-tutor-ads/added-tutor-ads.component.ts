import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { TutorAd } from '../announcement/tutorAd.interface';
import { TutorAdService } from '../../services/tutorAd.service';

@Component({
  selector: 'app-added-tutor-ads',
  templateUrl: './added-tutor-ads.component.html',
  styleUrl: './added-tutor-ads.component.scss'
})
export class AddedTutorAdsComponent {
  tutorAds: Observable<TutorAd[]> = this.tutorAdService.addedTutorAdsByTutor

  buttonFunction:string="Usuń"
 

  constructor(private tutorAdService:TutorAdService,
    ){

      
    }
}
