import { Component, OnInit } from '@angular/core';
import { DownloadServiceDelila } from 'src/app/services-delila/download-service';


@Component({
  selector: 'app-newsletter-download',
  templateUrl: './newsletter-download.component.html',
  styleUrls: ['./newsletter-download.component.css']
})
export class NewsletterDownloadComponentDelila implements OnInit {
  public files: Array<string>;
  public filesForDownload: Array<string> = [];
  constructor(public downloadService: DownloadServiceDelila) { }

  ngOnInit() {
    this.downloadService.getUserFiles("sanja", "hrenovica").subscribe( (data : any) =>{
      this.files = data.json();
});
  }

  downloadItem(){
    //console.log(this.filesForDownload);
   for (var i =0; i<this.filesForDownload.length; i++){
     this.downloadService.downloadFile( this.filesForDownload[i], "sanja", "hrenovica" ).subscribe();
   }
  }

  onChange(file:string, isChecked: boolean) {
   // let filesForDownload = Array<String>();
    //this.filesForDownload = [];
    if(isChecked) {
     this.filesForDownload.push(file);
    } else {
      let index = this.filesForDownload.findIndex(x => x == file);
      this.filesForDownload.splice(index,1);
    }
  }

}
