import { Component, OnInit } from '@angular/core';
import {Entry} from '../Entry';
import {EntryService} from '../entry.service';
import {TokenStorageService} from '../token-storage.service';

@Component({
  selector: 'app-allentries',
  templateUrl: './allentries.component.html',
  styleUrls: ['./allentries.component.css']
})
export class AllentriesComponent implements OnInit {

  entries: Entry[];
  selectedEntry: Entry;
  isListEmtpy: boolean;

  constructor(private entryService: EntryService, private tokenServ: TokenStorageService) { }

  ngOnInit(): void {
    this.getEntries();
  }

  getEntries(): void {
    this.entryService.getEntries()
      .subscribe(entries => {
        this.entries = entries
        if(entries.length === 0){
          this.isListEmtpy = true;
        }
      });
  }

}
