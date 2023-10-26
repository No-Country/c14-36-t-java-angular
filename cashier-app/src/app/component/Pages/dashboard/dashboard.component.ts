import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DashboardService } from 'src/app/services/dashboard.service';
import Swal from 'sweetalert2'

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
  cvu: Number = 123456;
  constructor(private formBuilder: FormBuilder, private dashboardService: DashboardService){
    this.generalInfo = this.formBuilder.group({
      
    });
  }
  
  showCvu():void{
    this.dashboardService.showCvu();
  }



}
