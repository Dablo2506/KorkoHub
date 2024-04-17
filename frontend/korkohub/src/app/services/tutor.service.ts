import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tutor } from '../components/announcement/tutorInterface';
import { of, switchMap } from 'rxjs';
import { TutorAd } from '../components/announcement/tutorAd.interface';
import { User } from '../components/profile/user.interface';
@Injectable({
    providedIn: 'root'
  })
export class TutorService { 
    
    constructor(private http: HttpClient) { }
    
    getTutors$(){
        return this.http.get<Tutor[]>('http://localhost:8080/api/v1/tutor');
      }
    
      getTutor$(id: number){
        return this.http.get<User>(`http://localhost:8080/api/v1/tutor/getTutor?id=${id}`);
      }

      updateUser(user: any) {
        const raw = new URLSearchParams({...user, id: user.userID.toString(), balance: user.balance.toString()})   
        this.http.get('http://localhost:8080/api/v1/users/editUser?' + raw.toString()).subscribe(()=>{
          console.log("userUpdated")
        })
      }
      //wywala blad nw czemu (400)
      updateTutor(user: any) {
        const raw = new URLSearchParams({...user, id: user.tutorID.toString(), balance: user.balance.toString()})
        this.http.get('http://localhost:8080/api/v1/tutor/editTutor?' + raw.toString()).subscribe(()=>{
          console.log("tutorUpdated")
        })
      }

      deleteUser(userID: number) {
        this.http.delete('http://localhost:8080/api/v1/users/' + userID).subscribe(()=> {
          console.log("deletedUser")
        })
      }
      deleteTutor(tutorID: number) {
        this.http.delete('http://localhost:8080/api/v1/tutor/' + tutorID).subscribe(()=> {
          console.log("deletedTutor")
        })
      }
   
}