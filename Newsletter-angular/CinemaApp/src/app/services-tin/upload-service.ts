import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import {HttpHeaders} from "@angular/common/http";
import {Log} from "@angular/core/testing/src/logger";

@Injectable()
export class UploadServiceTin {
    private api = 'http://localhost:8090/tin/api/users/';
    constructor(private http: Http) {}

    uploadFile(item, fileName, firstname, lastname, email): Observable<Response> {

        var blob = new Blob([item]);
        console.log(blob);
        console.log("Item: ", item);
        var formData = new FormData();
        formData.append("firstName", firstname);
        formData.append("lastName", lastname);
        formData.append("email", email);
        formData.append("file", blob);
        formData.append("fileName", fileName);
        console.log("FORMDATA");
        console.log(formData.get("file"));


        return this.http.post(this.api+"upload", formData);
    }

    getMoviesForCinema(id:number):Observable<Response>{
        return this.http.get(this.api+ "cinema-movies/"+id);
    }
}
