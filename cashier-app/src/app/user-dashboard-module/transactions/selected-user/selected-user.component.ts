import { DatePipe, registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import { Component, EventEmitter, Input, Output} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { IUserTarget } from 'src/app/interfaces/User.interface';
import { IAccount } from 'src/app/interfaces/account.interface';
import { ItransactionRes } from 'src/app/interfaces/response.interface';
import { ITransactionDTO } from 'src/app/interfaces/transaction.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { AccountService } from 'src/app/services/account.service';
import { TokenService } from 'src/app/services/token.service';
import { TransferService } from 'src/app/services/transfer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-selected-user',
  templateUrl: './selected-user.component.html',
  styleUrls: ['./selected-user.component.scss'],
  animations:[enterLateral,fadeAnimation],
})
export class SelectedUserComponent {
  @Input() viewStatus!:transactionView;
  @Input() accountData!:IAccount;
  @Input() userTarget!:IUserTarget;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Output() updateAccountData = new EventEmitter<IAccount>();
  transferForm!:FormGroup;
  userTargetAccount!:IAccount;
  currentDate=new Date();
  voucherData!:ItransactionRes

  constructor(
    private fb:FormBuilder,
    private transferServ:TransferService,
    private accountServ:AccountService,
    private tokenServ:TokenService
  ){
    this.initTransferForm();
  }

  updateAlertStatus(statusResponse:boolean){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alertSuccess:statusResponse,
      alertFail:!statusResponse,
      contact:true,
    };
    this.updateViews.emit(newStatus);
  }
  /*_-------------------------------------------- creacion del form */
  initTransferForm(){
    this.transferForm = this.fb.group({
      reason:['varios',[Validators.required]],
      amount:[0, [Validators.required]],
    })
  }
  /* --------------------------------------------------iniciar transferencia */
  onTransferSubmit(){
    this.registerTransferTime();
    const data = this.transferForm.value as {reason:string, amount:number}
    const idDestination = this.userTarget.idAccount;
    const idOrigin = this.accountData.idAccount;
    const dataDTO:ITransactionDTO = {
      ...data,
      type:"TRANSFER",
      origin:idOrigin,
      destination:idDestination
    }

    this.transferServ.newTransfer(dataDTO).subscribe({
      next:(res)=>{
        this.updateAlertStatus(true);
        this.updateAccountDataToParent();
        this.voucherData = res;
      },
      error(err){
          console.log(err)
          this.updateAlertStatus(false);
        }
      })
          this.updateAlertStatus(true);

  }
  updateAccountDataToParent(){
    this.accountServ.getAccount(this.accountData.idAccount).subscribe({
      next:(res)=>{
        this.updateAccountData.emit(res.data);
      },
      error(err){console.log(err)}
    })
  }
  registerTransferTime(){
    this.currentDate= new Date();
  }

  resetViewStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alertSuccess:false,
      alertFail:false,
      contact:false,
    };
    this.updateViews.emit(newStatus);
  }

  /* btn para desplegar el comprobante */
  showVoucher(){
    registerLocaleData(localeEs);
    // const datePipe = new DatePipe('es');
    // const toCivilianDate = datePipe.transform(this.voucherData.dateEmit, 'yyyy/MM/dd, HH:mm:ss');
    const [yy,MM,dd]=this.voucherData.dateEmit;
    const [,,,hh,mm,ss]=this.voucherData.dateEmit
    const toCivilianDate=`${yy}/${MM}/${dd} ${hh}:${mm}:${ss}`
    const dataUser = this.tokenServ.dataUser
    const content = `
  <p><strong>ID de Transacción:</strong> ${this.voucherData.id}</p>
  <p><strong>Origen:</strong> ${dataUser.name}</p>
  <p><strong>Destino:</strong> ${this.userTarget.name} ${this.userTarget.lastName}</p>
  <p><strong>Monto:</strong> ${this.voucherData.amount}</p>
  <p><strong>Fecha de Emisión:</strong> ${toCivilianDate}</p>
  <p><strong>Estado:</strong> ${this.voucherData.state}</p>
  <p><strong>Razón:</strong> ${this.voucherData.reason}</p>
`;
    Swal.fire({
      icon:'info',
      iconColor:'#1DE290',
      title:'Transferencia',
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
