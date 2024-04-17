import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../components/profile/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  getUser$(id: number): Observable<User>{
    return this.http.get<User>(`http://localhost:8080/api/v1/users/getUser?id=${id}`);
  }
}
