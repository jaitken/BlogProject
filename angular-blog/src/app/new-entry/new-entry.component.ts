import { Component, OnInit } from '@angular/core';
import {Entry} from '../Entry';
import {EntryService} from '../entry.service';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-new-entry',
  templateUrl: './new-entry.component.html',
  styleUrls: ['./new-entry.component.css']
})
export class NewEntryComponent implements OnInit {

  constructor(private entryService: EntryService,
              private tokenServ: TokenStorageService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
  }

  add(title: string, content: string): void{
    title = title.trim();
    content = content.trim();
    let username: string = this.tokenServ.getUser().userName;
    if(!title || !content){
      return;
    }

    let entry : Entry = {username, title, content};

    this.entryService.addEntry(entry)
    .subscribe(newEntry => {
      if(newEntry == null){
        console.log("got null from service in addEntry");
      }
    });

  }

  goBack(): void {
    this.location.back();
  }

}
