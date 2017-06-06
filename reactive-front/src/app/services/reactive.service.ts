import {Class, Component, Inject, Injectable, NgZone} from "@angular/core";
import {Http, Response, HttpModule} from "@angular/http";

import "rxjs/add/observable/of";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Injectable()
export class ReactiveService {
  constructor(private http: Http, private zone: NgZone) {
  }

  private eventsUrl = 'localhost:8080/events';

  subscribeToEvents(): void {
    // TODO: convert to Rx.Observable
    // Observable.create((observer) => {
    //   let eventSource = this.createEventSource();
    //   eventSource.onmessage = (event) => {
    //     this.zone.run(() => observer.next(JSON.parse(event.data)));
    //   };
    //   eventSource.onerror = (error) => observer.error(error);
    // })
    //   .subscribe({
    //     next: someValue => console.log('Event data: ' + someValue),
    //     error: someError => console.error('Something wrong occurred: ' + someError)
    //   });
  }

  // TODO: Unresolved type EventSource
  // private createEventSource(): EventSource {
  //   return new EventSource(this.http.get(this.eventsUrl));
  // }
}
