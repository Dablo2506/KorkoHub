import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, ReplaySubject, Subject, of, switchMap, tap, throwError } from 'rxjs';
import { User } from '../components/profile/user.interface';
import { HttpClient } from '@angular/common/http';
import { SearchService } from './search.service';
import { Tutor } from '../components/announcement/tutorInterface';
import { TutorAdService } from './tutorAd.service';
import { TutorService } from './tutor.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLogged = new BehaviorSubject<boolean>(false);
  private currentUser = new ReplaySubject<User>;

  constructor(private router: Router,
              private http: HttpClient,
              private searchService: SearchService,
              private tutorService :TutorService) {}

  get isUserLogged(){
    return this.isLogged.asObservable();
  }

  get currentLoggedUser(){
    return this.currentUser.asObservable();
  }


  
  login(email: string, password: string) {
    return this.http.get<{ id: number, role: string }>(`http://localhost:8080/api/v1/login?email=${email}&password=${password}`).pipe(
      switchMap(res => {
        if (res.id !== -1) {
          if (res.role === "User") {
            return this.searchService.getUser$(res.id).pipe(
              tap(user => {
                this.currentUser.next(user);
                this.isLogged.next(true);
              })
            );
          } else {
            return this.tutorService.getTutor$(res.id).pipe(
              tap(user => {
                this.currentUser.next(user);
                this.isLogged.next(true);
              })
            );
          }
        } else {
          return of(-1);
        }
      })
    );
  }
  

  register(name: string, surname: string, email: string, password: string, selectedRole: string, subject:string) {
    let params = {};
    let registrationUrl: string;
  
    if (selectedRole === 'user') {
      registrationUrl = 'http://localhost:8080/api/v1/users';
      params ={
       
          "name": name,
          "surname": surname,
          "email": email,
          "password": password,
      };

    } else if (selectedRole === 'tutor') {
      registrationUrl = 'http://localhost:8080/api/v1/tutor';
      params ={
       
        "name": name,
        "surname": surname,
        "email": email,
        "password": password,
        "subject":subject
      };

    } else {
      console.log("blad")
      return of(-1);
    }
  
    return this.http.post<number>(registrationUrl, params).pipe(
      switchMap(res => {
        if (res === -1) {
          return of(-1);
        } else {
          if(selectedRole === "user"){
            return this.searchService.getUser$(res).pipe(
            tap(user => {
              this.currentUser.next(user);
              this.isLogged.next(true);
            })
          );
          }
          else{
            return this.tutorService.getTutor$(res).pipe(
              tap(user => {
                this.currentUser.next(user);
                this.isLogged.next(true);
              })
            );
          }
        }
      })
    );
  }

  refreshUser(userId:number,tutorId:number){
    if(userId > 0)
    {
      return this.searchService.getUser$(userId).pipe(
        tap(user=>{
          this.currentUser.next(user)
        })
      )
    }
    else{
      return this.tutorService.getTutor$(tutorId).pipe(
        tap(user=>{
          this.currentUser.next(user)
        })
      )
    }

  }
  
  

  logout(){
    this.isLogged.next(false);
    this.router.navigateByUrl('korkohub');
  }
}
