import { Component, OnInit } from '@angular/core';
import { DownloadService } from 'src/app/services/download-service';

@Component({
  selector: 'app-newsletter-download',
  templateUrl: './newsletter-download.component.html',
  styleUrls: ['./newsletter-download.component.css']
})
export class NewsletterDownloadComponent implements OnInit {

  constructor(public downloadService: DownloadService) { }

  ngOnInit() {
  }

  downloadItem(){
    this.downloadService.downloadFile("pineapple", "sanja", "hrenovica").subscribe(data =>{
      const blob = new Blob([data], { type: 'image/jpeg' });
      const url= window.URL.createObjectURL(blob);
      window.open(url);
    });
  }

}
