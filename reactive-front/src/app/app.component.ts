import {Component, OnInit} from "@angular/core";
import {ReactiveService} from "./services/reactive.service";

@Component({
  selector: 'my-app',
  template: `<h1>Hello {{name}}</h1>`,
  providers: [ReactiveService]
})
export class AppComponent implements OnInit {
  name = 'Angular';

  constructor(private service: ReactiveService) {
  }

  ngOnInit(): void {
    this.service.subscribeToEvents();
  }
}
