import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class UploadService {
    private api = 'http://localhost:8090/api/users/';
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

    getMoviesForCinema(id:number):Observable<Response>{
        return this.http.get(this.api+ "cinema-movies/"+id);
    }
}
