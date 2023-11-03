import { DatePipe, registerLocaleData } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { IAccount } from 'src/app/interfaces/account.interface';
import { IBillRes, IGetPayment } from 'src/app/interfaces/response.interface';
import { IBillDTO } from 'src/app/interfaces/transaction.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { AccountService } from 'src/app/services/account.service';
import { PaymentService } from 'src/app/services/payment.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';
import localeEs from '@angular/common/locales/es';

@Component({
  selector: 'app-target-service',
  templateUrl: './target-service.component.html',
  styleUrls: ['./target-service.component.scss'],
  animations:[fadeAnimation, enterLateral]
})
export class TargetServiceComponent {
  constructor(
    private paymentServ:PaymentService,
    private accountServ:AccountService,
    private tokenServ:TokenService
  ){}
  @Input() viewStatus!:transactionView;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Output() updateAccountData = new EventEmitter<IAccount>();
  @Input() accountData !: IAccount;
  @Input() targetService !: IGetPayment;
  voucherData!:IBillRes

  updateAlertStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alertFail:true,
      contact:true,
    };
    this.updateViews.emit(newStatus);
  }
  createBill(){
    const bill:IBillDTO={
      amount:this.targetService.monto,
      bill_num:this.targetService.entidad,
      bill_type:"servicio",
      origin:this.accountData.idAccount,
      type:"PAYMENT"
    }
    console.log(bill)
    this.paymentServ.newPayment(bill).subscribe({
      next:(res)=>{
        this.voucherData = res;
        this.updateAlertStatus();
        this.emitUpdateAccountData();
      },
      error:(err)=>{console.log(err)}
    })
  }
  emitUpdateAccountData(){
    const {idAccount} = this.accountData
    this.accountServ.getAccount(idAccount).subscribe({
      next:(res)=>{this.updateAccountData.emit(res.data)},
      error:(err)=>(console.log("error actualizando la cuenta del usuario:",err))
    })
  }
   /* btn para desplegar el comprobante */
   showVoucher(){
    registerLocaleData(localeEs);
    const dataUser = this.tokenServ.dataUser;
    // const datePipe = new DatePipe('es');
    // const toCivilianDate = datePipe.transform(this.voucherData.dateEmit, 'yyyy/MM/dd, HH:mm:ss');
    const [yy,MM,dd]=this.voucherData.dateEmit;
    const [,,,hh,mm,ss]=this.voucherData.dateEmit
    const toCivilianDate=`${yy}/${MM}/${dd} ${hh}:${mm}:${ss}`
    const content = `
  <p><strong>ID de Transacción:</strong> ${this.voucherData.id}</p>
  <p><strong>Origen:</strong> ${dataUser.name}</p>
  <p><strong>Destino:</strong> ${this.targetService.entidad}</p>
  <p><strong>Monto:</strong> ${this.voucherData.amount}</p>
  <p><strong>Fecha de Emisión:</strong> ${toCivilianDate}</p>
  <p><strong>Estado:</strong> ${this.voucherData.state}</p>
  <p><strong>Razón:</strong> ${this.voucherData.bill_num}</p>
`;
    Swal.fire({
      icon:'info',
      iconColor:'#1DE290',
      title:'Pago Servicio',
      html:content,
      confirmButtonText:'Cerrar',
      background:'#153230',
      customClass:{
        htmlContainer:"swal__content",
        confirmButton:"swal__confirmBtn"
      }
    })
  }
}
