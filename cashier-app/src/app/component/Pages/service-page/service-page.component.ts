import { Component } from '@angular/core';
import { fadeAnimation, enterLateral } from 'src/app/animations/animation';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.scss'],
  animations: [fadeAnimation, enterLateral]
})
export class ServicePageComponent {
  isShowFilter = false;
  isShowResult = false;
  isShowContact = false;
  showSearchData = true;
  isShowAlert = false;
  isShowActions = false;
  isShowHistory = false;
  showFilters() {
    this.isShowFilter = !this.isShowFilter;
    this.isShowResult = false;
    this.isShowContact = false;
    this.isShowAlert = false;
  }

  showResults(idBox:string) {
    this.isShowContact = false;
    this.isShowResult = true;
    this.isShowHistory = idBox ==='pay';
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
