import { Component, OnInit } from '@angular/core';
import {Entry} from '../Entry';
import {ENTRIES} from '../mock-entries';
import {EntryService} from '../entry.service';

@Component({
  selector: 'app-entries',
  templateUrl: './entries.component.html',
  styleUrls: ['./entries.component.css']
})
export class EntriesComponent implements OnInit {
  
  entries: Entry[];
  selectedEntry: Entry;

  constructor(private entryService: EntryService) { }

  ngOnInit(): void {
    this.getEntries();
  }

  getEntries(): void {
    this.entryService.getEntries()
      .subscribe(entries => this.entries = entries)
  }

  add(title: string, content: string ): void {
    title = title.trim();
    content = content.trim();
    if(!title || !content){
      return;
    }

    let entry : Entry = {title, content};
    
    this.entryService.addEntry(entry)
        .subscribe(newEntry => {
          if(newEntry == null){
            console.log("got null from service in addEntry");
          }
          this.entries.unshift(newEntry);
        });
  }

  delete(entry: Entry): void {
    this.entries = this.entries.filter(e=> e !== entry);
    this.entryService.deleteEntry(entry).subscribe();
  }

}
