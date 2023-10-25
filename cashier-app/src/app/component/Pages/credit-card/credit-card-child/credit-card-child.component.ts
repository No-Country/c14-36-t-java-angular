import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-credit-card-child',
  templateUrl: './credit-card-child.component.html',
  styleUrls: ['./credit-card-child.component.scss']
})
export class CreditCardChildComponent {
  @Output() emitShowStatus = new EventEmitter<boolean>();
  @Input() show!:boolean;

  windowClose(){
    console.log(this.show);
    this.emitShowStatus.emit(false);
  }
}
