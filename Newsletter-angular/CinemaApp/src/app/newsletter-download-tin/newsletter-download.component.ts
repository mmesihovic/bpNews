import { Component, OnInit } from '@angular/core';
import { DownloadServiceTin } from 'src/app/services-tin/download-service';


@Component({
  selector: 'app-newsletter-download',
  templateUrl: './newsletter-download.component.html',
  styleUrls: ['./newsletter-download.component.css']
})
export class NewsletterDownloadComponentTin implements OnInit {
  public files: Array<string>;
  public filesForDownload: Array<string> = [];
  constructor(public downloadService: DownloadServiceTin) { }

  ngOnInit() {
    this.downloadService.getUserFiles("sanja", "hrenovica").subscribe( (data : any) =>{
      this.files = data.json();
});
  }

  downloadItem(){
   for (var i =0; i<this.filesForDownload.length; i++){
     this.downloadService.downloadFile( this.filesForDownload[i], "sanja", "hrenovica" ).subscribe();
   }
  }

  onChange(file:string, isChecked: boolean) {
    if(isChecked) {
     this.filesForDownload.push(file);
    } else {
      let index = this.filesForDownload.findIndex(x => x == file);
      this.filesForDownload.splice(index,1);
    }
  }

}
