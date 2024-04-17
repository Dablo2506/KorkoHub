import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { AnnouncementsComponent } from './components/announcements/announcements.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { AddedTutorAdsComponent } from './components/added-tutor-ads/added-tutor-ads.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'korkohub',
    pathMatch: 'full'
  },
  {
    path: 'korkohub',
    component: MainPageComponent
  },
  {
    path: 'korkohub/profil',
    component: ProfileComponent
  },
  {
    path: 'korkohub/login',
    component: LoginComponent
  },
  {
    path: 'korkohub/ogloszenia',
    component: AnnouncementsComponent
  },
  {
    path: 'korkohub/rezerwacje',
    component: ReservationsComponent
  },
  {
    path: 'korkohub/dodane_ogloszenia',
    component: AddedTutorAdsComponent
  },
  {
    path:'**',
    redirectTo: 'korkohub',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
