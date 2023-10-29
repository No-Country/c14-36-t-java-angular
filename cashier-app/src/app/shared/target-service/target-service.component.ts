import { Component, EventEmitter, Input, Output } from '@angular/core';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { transactionView } from 'src/app/interfaces/transactionView.interface';

@Component({
  selector: 'app-target-service',
  templateUrl: './target-service.component.html',
  styleUrls: ['./target-service.component.scss'],
  animations:[fadeAnimation, enterLateral]
})
export class TargetServiceComponent {
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
