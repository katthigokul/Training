import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private stompClient: any;
  private socketUri = 'http://13.235.105.89:8046/web-socket';
  private senderEndPoint = '/app/notification';
  private recieverEndPoint = '/queue/notification';

  private messageSubject = new Subject<any>();

  constructor() {}

  /**
   * Connects websocket service
   */
  connect(emailId: string) {
    console.log('connect');
    const socket = new SockJs(this.socketUri);
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, frame => {
      this.stompClient.subscribe('/user/' + emailId + this.recieverEndPoint, (notification) => {
        console.log(notification);
        this.messageSubject.next(notification.body); // Forward recieved message to Observable
      });
      console.log('search');
    });
  }

  /**
   * Disconnects websocket service
   */
  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }

  /**
   * Sends message to websocket server
   * @param message JSON object to be sent to websocket
   */
  sendNotification(notification: {}) {
    this.stompClient.send(this.senderEndPoint, {}, notification);
  }

  public getMessage(): Observable<any> {
    return this.messageSubject.asObservable();
  }
}

