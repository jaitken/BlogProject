import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { MessageService } from './message.service';

import {Entry} from './Entry';
import {Comment} from './Comment';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  
  private commentsUrl = 'http://localhost:8080/api/entryComments';
  private addCommentUrl = 'http://localhost:8080/api/addComment';
  private deleteCommentUrl = 'http://localhost:8080/api/deleteComment';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  
    getCommentsForEntry(id: number): Observable<Comment[]>{
      this.messageService.add(`CommentService: fetched comments with entry id=${id}`);
      const url = `${this.commentsUrl}/${id}`;
      return this.http.get<Comment[]>(url).pipe(
        tap(_ => this.log(`fetched comments for entry id=${id}`)),
        catchError(this.handleError<Comment[]>(`getCommentsForEntry id=${id}`))
      );
    }

    addComment(comment: Comment): Observable<Comment> {
      this.messageService.add(`CommentService: added comment`);
      console.log("entryId: "+comment.entryId);
      console.log(comment.content);
      return this.http.post<Comment>(this.addCommentUrl, comment, this.httpOptions).pipe(
        tap(_ => this.log(`added new entry`)),
        catchError(this.handleError<any>('addEntry'))
      );
    }

    deleteComment(comment: Comment): Observable<Comment>{
      const id = typeof comment === 'number' ? comment : comment.id;
      const url = `${this.deleteCommentUrl}/${id}`;
    
      return this.http.delete<Comment>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted comment id=${id}`)),
      catchError(this.handleError<Comment>('deleteComment'))
    );

    }

    private log(message: string) {
      this.messageService.add(`CommentService: ${message}`);
    }

    private handleError<T>(operation='operation', result?: T){
      return (error: any): Observable<T> =>{
        console.error(error);
        this.log(`${operation} failed: ${error.message}`);
        return of(result as T);
      }
    }
}
