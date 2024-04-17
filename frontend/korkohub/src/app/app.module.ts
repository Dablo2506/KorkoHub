import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ProfileComponent } from './components/profile/profile.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MainPageComponent } from './components/main-page/main-page.component';
import { MatButtonModule } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchInputComponent } from './components/search-input/search-input.component';
import { PageLogoComponent } from './components/page-logo/page-logo.component';
import { HttpClientModule } from '@angular/common/http';
import { AnnouncementsComponent } from './components/announcements/announcements.component';
import { AnnouncementComponent } from './components/announcement/announcement.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatCommonModule } from '@angular/material/core';
import { MatDialogContent } from '@angular/material/dialog';
import { MatDialogActions } from '@angular/material/dialog';
import { DialogComponent } from './components/dialog/dialog.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import {MAT_RADIO_DEFAULT_OPTIONS, MatRadioModule} from '@angular/material/radio';
import { AddedTutorAdsComponent } from './components/added-tutor-ads/added-tutor-ads.component';





@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProfileComponent,
    FooterComponent,
    LoginComponent,
    MainPageComponent,
    SearchInputComponent,
    PageLogoComponent,
    AnnouncementsComponent,
    AnnouncementComponent,
    DialogComponent,
    ReservationsComponent,
    AddedTutorAdsComponent

  ],
  imports: [
    HttpClientModule,
    MatRadioModule,
    MatCommonModule,
    MatDialogContent,
    MatDialogActions,
    MatFormFieldModule,
    MatDialogModule,
    MatButtonModule,
    FormsModule,
    MatInputModule,
    MatGridListModule,
    MatCardModule,
    MatIconModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    provideAnimationsAsync()

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
