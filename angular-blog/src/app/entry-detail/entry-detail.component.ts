import { Component, OnInit, Input } from '@angular/core';
import {Entry} from '../Entry';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {EntryService} from '../entry.service';
import {CommentService} from '../comment.service';
import {CommentsComponent} from '../comments/comments.component'

@Component({
  selector: 'app-entry-detail',
  templateUrl: './entry-detail.component.html',
  styleUrls: ['./entry-detail.component.css']
})
export class EntryDetailComponent implements OnInit {
  //@Input() entry: Entry;
  entry: Entry;
  id: number;

  constructor(
    private route: ActivatedRoute,
    private entryService: EntryService,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getEntry();
  }

  getEntry(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.id = id;
    this.entryService.getEntry(id)
      .subscribe(entry => this.entry = entry)
  }

  
}
