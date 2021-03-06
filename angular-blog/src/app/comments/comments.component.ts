import { Component, Input, OnInit } from '@angular/core';
import {Comment} from '../Comment';
import {CommentService} from '../comment.service'
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  
  comments: Comment[];
  @Input() entryId: number;
  @Input() entryOwner: string;
  
  addButtonDiv:boolean=true;
  submitDiv:boolean=false;

  ownerViewing:boolean = false;

  constructor(
    private commentService: CommentService,
    private tokenServ: TokenStorageService
  ) { }

  ngOnInit(): void {
    
    if(this.entryOwner === this.tokenServ.getUser().userName){
      console.log("Owner is viewing")
      this.ownerViewing = true;
    }else{
      this.ownerViewing = false;
    }

    this.getCommentsforEntry(this.entryId);
  }

  getCommentsforEntry(id: number): void{
    this.commentService.getCommentsForEntry(id).subscribe(comments => this.comments = comments);
  }

  add(content: string): void{
    content = content.trim();
    if(!content){
      return;
    }
    let entryId : number = this.entryId;
    let comment: Comment = {entryId, content}
    this.commentService.addComment(comment)
      .subscribe(newComment => {
        if(newComment == null){
          console.log("got null from service in addEntry");
        }
        this.comments.push(newComment);
      });
  }

  delete(comment: Comment): void{
    this.comments = this.comments.filter(c=> c !== comment);
    this.commentService.deleteComment(comment).subscribe();
  }

  showAddCommentDiv(): void {
    this.addButtonDiv = false;
    this.submitDiv = true;
  }

  hideAddCommentDiv(): void{
    this.addButtonDiv = true;
    this.submitDiv = false;
  }

}
