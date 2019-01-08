import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {  FileUploader, FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { UploadServiceDelila } from 'src/app/services-delila/upload-service';

@Component({
  selector: 'app-newsletter-upload-delila',
  templateUrl: './newsletter-upload.component.html',
  styleUrls: ['./newsletter-upload.component.css']
})
export class NewsletterUploadComponentDelila implements OnInit {
  uploadForm: FormGroup;
  names: Array<String> = new Array<String>();
  
  constructor(private formBuilder: FormBuilder,
              private uploadService: UploadServiceDelila) { }

  ngOnInit() {
    this.uploadForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName:  ['', Validators.required],
      email:  ['', Validators.required]
    });

    var name= this.uploadForm.controls.firstName.value;
  }

  public uploadFiles(e) {
      let input = e.target;
      console.log(e.target);
      console.log(e.target.files);

      Array.prototype.forEach.call(e.target.files, (file) => {
        this.names = new Array<String>();
        this.names.push(file.name);
      });

      var name= this.uploadForm.controls.firstName.value;
      var last= this.uploadForm.controls.lastName.value;
      var email= this.uploadForm.controls.email.value;

      Array.prototype.forEach.call(e.target.files, (file) => {
        var reader = new FileReader();
        reader.readAsArrayBuffer(file);
        reader.onloadend = ( () => {
          console.log(reader.result);
          this.uploadService.uploadFile(reader.result, file.name, name, last, email).subscribe((nesto) =>{ console.log((nesto))});
        });
      });


  };


  onSubmit(){

  }

}
