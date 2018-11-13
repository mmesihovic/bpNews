import { Injectable,Output, EventEmitter } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()

export class MovieService {

    @Output() viewMovies: EventEmitter<any[]> = new EventEmitter();

    private api = 'http://localhost:3000/api/';
    constructor(private http: Http) {}

    getMovieById(id: number): Observable<Response> {
        return this.http.get(this.api + "movie/" + id);
    }
}