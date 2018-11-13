import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class CinemaService {
    private api = 'http://localhost:3000/api/';
    constructor(private http: Http) {}

    getCinemas(): Observable<Response> {
        return this.http.get(this.api + "cinemas");
    }

    getMoviesForCinema(id:number):Observable<Response>{
        return this.http.get(this.api+ "cinema-movies/"+id);
    }
}