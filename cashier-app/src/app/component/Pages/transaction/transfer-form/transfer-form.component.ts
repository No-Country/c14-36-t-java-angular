import { Component, EventEmitter, Input, Output } from '@angular/core';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { transactionView } from 'src/app/interfaces/transactionView.interface';

@Component({
  selector: 'app-transfer-form',
  templateUrl: './transfer-form.component.html',
  styleUrls: ['./transfer-form.component.scss'],
  animations:[enterLateral, fadeAnimation]
})
export class TransferFormComponent {
  @Input() viewStatus!: transactionView;
  @Output() updateViews = new EventEmitter<transactionView>();
  updateFilterStatus(filterData:boolean){
    const newStatus:transactionView = {
      ...this.viewStatus,
      filterData,
      filterCVU:!filterData,
      alerts:false,
      contact:false,
    };
    this.updateViews.emit(newStatus);
  }
  updateContactStatus(){
    const newStatus = {
      ...this.viewStatus,
      contact:true,
      alerts:false
    }
    this.updateViews.emit(newStatus);
  }
}
