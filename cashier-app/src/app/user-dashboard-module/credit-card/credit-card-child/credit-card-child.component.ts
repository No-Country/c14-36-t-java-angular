import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ICard } from 'src/app/interfaces/account.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-credit-card-child',
  templateUrl: './credit-card-child.component.html',
  styleUrls: ['./credit-card-child.component.scss']
})
export class CreditCardChildComponent {
  @Output() emitShowStatus = new EventEmitter<boolean>();
  @Input() show!:boolean;
  @Input() cardData!:ICard;

  windowClose(){
    this.emitShowStatus.emit(false);
  }
  copyToClipboard(){
    navigator.clipboard.writeText(this.cardData.cardNumber)
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Nro. de tarjeta copiado.',
      showConfirmButton: false,
      timer: 1500,
      background: '#153230',
      color: '#fff'
    })
  }
}
