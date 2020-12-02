import { Component, OnInit } from '@angular/core';
import {Entry} from '../Entry';
import {EntryService} from '../entry.service';
import {TokenStorageService} from '../token-storage.service';

@Component({
  selector: 'app-entries',
  templateUrl: './entries.component.html',
  styleUrls: ['./entries.component.css']
})
export class EntriesComponent implements OnInit {
  
  entries: Entry[];
  selectedEntry: Entry;
  isListEmtpy: boolean;

  constructor(private entryService: EntryService, private tokenServ: TokenStorageService) { }

  ngOnInit(): void {
    this.getEntries();
  }

  getEntries(): void {
    this.entryService.getEntriesForCurrentUser()
      .subscribe(entries => {
        this.entries = entries
        if(entries.length === 0){
          this.isListEmtpy = true;
        }
      });
  }

  add(title: string, content: string ): void {
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
          this.entries.unshift(newEntry);
          this.isListEmtpy = false;
        });
  }

  delete(entry: Entry): void {
    this.entries = this.entries.filter(e=> e !== entry);
    this.entryService.deleteEntry(entry).subscribe();

    if(this.entries.length == 0){
      this.isListEmtpy = true;
    }
  }

}
