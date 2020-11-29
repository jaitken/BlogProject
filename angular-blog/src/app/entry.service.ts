import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { MessageService } from './message.service';
import {Entry} from './Entry';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EntryService {
  
  private entriesUrl = 'http://localhost:8080/api/entries';
  private entryUrl = 'http://localhost:8080/api/entry';
  private addEntryUrl = 'http://localhost:8080/api/addEntry';
  private deleteEntryUrl ='http://localhost:8080/api/deleteEntry'

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getEntries(): Observable<Entry[]>{
    return this.http.get<Entry[]>(this.entriesUrl).pipe(
      tap(_ => this.log('fetched entries')),
      catchError(this.handleError<Entry[]>('getEntries', []))
    );
  }

  getEntry(id: number): Observable<Entry>{
    this.messageService.add(`EntryService: fetched entry id=${id}`);
    const url = `${this.entryUrl}/${id}`;
    return this.http.get<Entry>(url).pipe(
    tap(_ => this.log(`fetched entry id=${id}`)),
    catchError(this.handleError<Entry>(`getEntry id=${id}`))
  );
  }

  addEntry(entry: Entry): Observable<Entry> {
    return this.http.post<Entry>(this.addEntryUrl, entry, this.httpOptions).pipe(
      tap(_ => this.log(`added new entry`)),
      catchError(this.handleError<any>('addEntry'))
    );
  }

  deleteEntry(entry: Entry | number): Observable<Entry>{
    const id = typeof entry === 'number' ? entry : entry.id;
    const url = `${this.deleteEntryUrl}/${id}`;
    
    return this.http.delete<Entry>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted entry id=${id}`)),
      catchError(this.handleError<Entry>('deleteEntry'))
    );
  }

  private log(message: string) {
    this.messageService.add(`EntryService: ${message}`);
  }

  private handleError<T>(operation='operation', result?: T){
    return (error: any): Observable<T> =>{
      console.error(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    }
  }

}
