import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  generalInfo: FormGroup;
  monto: string = "60,000";
  fecha: String = "15/02/2027"
  clientes:  String[] = [];
  saldo: String = "00.00";
  constructor(private formBuilder: FormBuilder){
    this.generalInfo = this.formBuilder.group({
      
    });
  }
}
