import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tutor } from '../components/announcement/tutorInterface';
import { BehaviorSubject, ReplaySubject, Subject, map, of, switchMap, tap } from 'rxjs';
import { TutorService } from './tutor.service';
import { SearchService } from './search.service';
import { TutorAd } from '../components/announcement/tutorAd.interface';
@Injectable({
    providedIn: 'root'
  })
export class TutorAdService { 
    
  private tutorAds = new Subject<TutorAd[]>;
  private signedtutorAds = new Subject<TutorAd[]>;
  private balance = new ReplaySubject<number>(0);
  userId!:number;
  tutorId!:number;
  private addedTutorAds = new Subject<TutorAd[]>

  get addedTutorAdsByTutor(){
    return this.addedTutorAds.asObservable();
  }

  get CurrentTutorAds(){
    return this.tutorAds.asObservable();
  }

  get CurrentSignedTutorAds(){
    return this.signedtutorAds.asObservable();
  }

  get currentBalance(){
    return this.balance.asObservable();
  }

    constructor(private http: HttpClient) {
          
         }

      addTutorAd(id: number, description: string, subject :string, price:number) {
        const params = {
          "tutorID": id, 
          "description": description,
          "subject": subject,
          "price": price
        };
        return this.http.post('http://localhost:8080/api/v1/tutoringAd', params).pipe(
          switchMap( () => {
            return this.refreshTutorAds$().pipe(tap(res=>{
              this.tutorAds.next(res)
            }))
          })
        )
          
      }
        
      refreshTutorAds$(){
        return this.http.get<TutorAd[]>('http://localhost:8080/api/v1/tutoringAd').pipe()
      }
    
      getTutorAds$(){
        return this.http.get<TutorAd[]>('http://localhost:8080/api/v1/tutoringAd').pipe(
          tap(res=>{
            this.tutorAds.next(res)}))
      }

      getTutorAd$(id: number){
        return this.http.get<TutorAd>(`http://localhost:8080/api/v1/tutoringAd/getTutoringAd?id=${id}`);
      }

      signIn$(UserId:number, tutoringAdID:number )
      {
        return this.http.get<number>(`http://localhost:8080/api/v1/tutoringAd/createLesson?tutoringAdId=${tutoringAdID}&userId=${UserId}`).pipe(
          switchMap( bal => {
             this.balance.next(bal)
             return this.refreshTutorAds$().pipe(tap(res=>{
              this.tutorAds.next(res)}))
          })
        )
      }

      signOut$(UserId:number, tutoringAdID:number )
      {
        return this.http.get<number>(`http://localhost:8080/api/v1/lesson/delLesson?userId=${UserId}&tutoringAdId=${tutoringAdID}`).pipe(
          switchMap( bal => {
            this.balance.next(bal)
            return this.refreshSignedAds$(this.userId).pipe(tap(res=>{
              this.signedtutorAds.next(res)}))
          })
        )
      }

      getSignedAds$(userId:number)
      {
        this.userId = userId
        return this.http.get<TutorAd[]>(`http://localhost:8080/api/v1/lesson/getTutoringAdByUserId?id=${userId}`).pipe(
          tap(res=>{
            this.signedtutorAds.next(res)}))
      }

      refreshSignedAds$(userId:number)
      {
        return this.http.get<TutorAd[]>(`http://localhost:8080/api/v1/lesson/getTutoringAdByUserId?id=${userId}`)
      }

      getAddedAdsByTutor$(tutorId:number)
      {
        this.tutorId = tutorId
        return this.http.get<TutorAd[]>(`http://localhost:8080/api/v1/tutoringAd/findTutoringAdByTutorId?tutorId=${tutorId}`).pipe(
          tap(res=>{
            this.addedTutorAds.next(res)}))
      }

      deleteTutorAd(tutoringAdId:number){
        return this.http.get(`http://localhost:8080/api/v1/tutoringAd/delTutoringAd?tutoringAdId=${tutoringAdId}`).pipe(
          switchMap( () => {
            return this.getAddedAdsByTutor$(this.tutorId).pipe(tap(res=>{
              this.addedTutorAds.next(res)}))
          })
        )
      }
}