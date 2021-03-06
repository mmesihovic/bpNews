import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CarouselModule } from 'ngx-bootstrap';
import { AgmCoreModule } from '@agm/core';
import { HttpModule } from '@angular/http';
import { CinemaService} from './services/cinema-service';
import { MovieService} from './services/moive-service';
import { NewsletterDownloadComponent } from './newsletter-download/newsletter-download.component';
import { NewsletterUploadComponent } from './newsletter-upload/newsletter-upload.component';
import { FileSelectDirective } from 'ng2-file-upload';
import { UploadService } from 'src/app/services/upload-service';
import { DownloadService } from 'src/app/services/download-service';
import {NewsletterDownloadComponentDelila} from "./newsletter-download-delila/newsletter-download.component";
import {NewsletterUploadComponentDelila} from "./newsletter-upload-delila/newsletter-upload.component";
import {NewsletterDownloadComponentTin} from "./newsletter-download-tin/newsletter-download.component";
import {NewsletterUploadComponentTin} from "./newsletter-upload-tin/newsletter-upload.component";

const appRoutes: Routes = [
  { path: '', component: NewsletterDownloadComponent},
  { path: 'upload', component: NewsletterUploadComponent}

];
@NgModule({
  declarations: [
    AppComponent,
    NewsletterDownloadComponent,
    NewsletterUploadComponent,
    FileSelectDirective,
    NewsletterDownloadComponentDelila,
    NewsletterUploadComponentDelila,
    NewsletterDownloadComponentTin,
    NewsletterUploadComponentTin
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    ),
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    CarouselModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCBTdcPVvEKEGtShWFMIS-3FLA-1BAszQE'
    })
  ],
  providers: [
    UploadService,
    DownloadService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
