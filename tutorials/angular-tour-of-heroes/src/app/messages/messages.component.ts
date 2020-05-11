import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  // Message service must be public because we're binding it in a template.
  // Angular can only bind public components.
  constructor(public messageService: MessageService) {
  }

  ngOnInit(): void {
  }
}
