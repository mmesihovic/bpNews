import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {  FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { UploadService } from 'src/app/services/upload-service';

@Component({
  selector: 'app-newsletter-upload',
  templateUrl: './newsletter-upload.component.html',
  styleUrls: ['./newsletter-upload.component.css']
})
export class NewsletterUploadComponent implements OnInit {
  uploadForm: FormGroup;
  public uploader: FileUploader = new FileUploader(null);
  
  constructor(private formBuilder: FormBuilder,
              private uploadService: UploadService) { }

  ngOnInit() {
    this.uploadForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName:  ['', Validators.required],
      email:  ['', Validators.required]
    });

    var name= this.uploadForm.controls.firstName.value;
  }

  onSubmit(){
   
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      var iriri= item;
      var name= this.uploadForm.controls.firstName.value;
      var last= this.uploadForm.controls.lastName.value;
      var email= this.uploadForm.controls.email.value;
      if(item){
      this.uploadService.uploadFile(item, name, last, email).subscribe(
        data => { console.log(data) // Data which is returned by call
        },
        error => { console.log(error); // Error if any
        });
    }
         console.log('ImageUpload:uploaded:', item, status, response);
         alert('File uploaded successfully');
     };


  }

}
