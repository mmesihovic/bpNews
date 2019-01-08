import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class DownloadServiceDelila {
  private api = 'http://localhost:8090/delila/api/users/';
  constructor(private http: Http) {}

  uploadFile(item, firstname, lastname, email): Observable<Response> {
    var formData = new FormData();
    var wrap = new FormData();
    formData.append("firstName", firstname);
    formData.append("lastName", lastname);
    formData.append("email", email);

    var blob = new Blob([item]);
    formData.append("file", blob);
    formData.append("fileName", item.file.name );



    return this.http.post(this.api+"upload", formData );
  }

  getUserFiles(firstname, lastname): Observable<Response> {
    return this.http.get(this.api+"getFiles?firstName="+firstname+"&lastName="+lastname);
  }

  downloadFile(filename, firstname, lastname): Observable<Response> {
    var formData = new FormData();
    var wrap = new FormData();
    let params = new HttpParams();
    params.set('fileName', filename );
    params.set('firstName', firstname);
    //params.set('lastName", lastname);

    return this.http.get(this.api+"download?fileName="+filename+"&firstName="+firstname+"&lastName="+lastname);

    //return this.http.get(this.api+"download", { params: params });
  }
}
