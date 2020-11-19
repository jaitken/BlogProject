import { Component, OnInit } from '@angular/core';
import {Entry} from '../Entry';
import {ENTRIES} from '../mock-entries';

@Component({
  selector: 'app-entries',
  templateUrl: './entries.component.html',
  styleUrls: ['./entries.component.css']
})
export class EntriesComponent implements OnInit {
  entries = ENTRIES;


  constructor() { }

  ngOnInit(): void {
  }

}
