import { Component } from '@angular/core';
import { enterLateral, fadeAnimation } from '../../../animations/animation';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss'],
  animations: [fadeAnimation, enterLateral ],
})
export class TransactionComponent {
  isShowFilter = false;
  isShowResult = false;
  isShowContact = false;
  showSearchCVU = false;
  showSearchData = true;
  isShowAlert = false;
  isShowActions = false;
  showFilters() {
    this.isShowFilter = !this.isShowFilter;
    this.isShowResult = false;
    this.isShowContact = false;
  }

  showResults(idBox:string) {
    this.isShowResult = true;
    this.isShowContact = false;

    if(idBox==='data'){
      this.showSearchData = !(this.showSearchCVU = false);
    }
    else{
      this.showSearchCVU = !(this.showSearchData = false);
    }
  }

  showContact() {
    this.isShowContact = !this.isShowContact;
  }

  showAlert(){
    this.isShowAlert = !this.isShowAlert;
    this.showActions();
  }
  showActions(){
    this.isShowActions = !this.isShowActions;
  }
}
