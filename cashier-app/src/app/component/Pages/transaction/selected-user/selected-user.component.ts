import { Component, EventEmitter, Input, Output } from '@angular/core';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { transactionView } from 'src/app/interfaces/transactionView.interface';

@Component({
  selector: 'app-selected-user',
  templateUrl: './selected-user.component.html',
  styleUrls: ['./selected-user.component.scss'],
  animations:[enterLateral,fadeAnimation],
})
export class SelectedUserComponent {
  @Input() viewStatus!:transactionView;
  @Output() updateViews = new EventEmitter<transactionView>();
  updateAlertStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alerts:true,
      contact:true,
    };
    this.updateViews.emit(newStatus);
  }
}
