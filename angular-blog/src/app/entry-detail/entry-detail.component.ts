import { Component, OnInit, Input } from '@angular/core';
import {Entry} from '../Entry';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {EntryService} from '../entry.service';
import {CommentService} from '../comment.service';
import {CommentsComponent} from '../comments/comments.component'
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-entry-detail',
  templateUrl: './entry-detail.component.html',
  styleUrls: ['./entry-detail.component.css']
})

//class used for showing full entry, comment section component attached in html
export class EntryDetailComponent implements OnInit {

  entry: Entry;
  id: number;


  constructor(
    private route: ActivatedRoute,
    private entryService: EntryService,
    private location: Location,
    private tokenServ: TokenStorageService
  ) { }

  ngOnInit(): void {
    this.getEntry();
  }

  getEntry(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.id = id;
    this.entryService.getEntry(id)
      .subscribe(entry => {
        this.entry = entry
      });
  }

  
}
