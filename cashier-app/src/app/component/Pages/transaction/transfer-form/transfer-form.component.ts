import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { textValidator } from 'src/app/CustomValidator/customValidator';
import { enterLateral, fadeAnimation, scaling } from 'src/app/animations/animation';
import { IUserTarget, User } from 'src/app/interfaces/User.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-transfer-form',
  templateUrl: './transfer-form.component.html',
  styleUrls: ['./transfer-form.component.scss'],
  animations: [enterLateral, fadeAnimation, scaling],
})
export class TransferFormComponent {
  @Input() viewStatus!: transactionView;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Output() idUserTarget = new EventEmitter<IUserTarget>();
  searchDataForm!: FormGroup;
  searchCvuForm!: FormGroup;
  userResults: User[] = [];
  loading = false;

  constructor(private fb: FormBuilder, private userServ: UserService) {
    this.initDataForm();
    this.initCvuForm();
  }
  /* -------------------------actualizar vistas */
  updateFilterStatus(filterData: boolean) {
    const newStatus: transactionView = {
      ...this.viewStatus,
      filterData,
      filterCVU: !filterData,
      alerts: false,
      contact: false,
    };
    this.updateViews.emit(newStatus);
  }
  updateContactStatus() {
    const newStatus = {
      ...this.viewStatus,
      contact: true,
      alerts: false,
    };
    this.updateViews.emit(newStatus);
  }
  updateResultFormStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      formResult:true,
      alerts:false,
      contact:false
    }
    this.updateViews.emit(newStatus);
  }
/* ------------------------------------------------------crear formularios */
  initCvuForm() {
    this.searchCvuForm = this.fb.group({cvu: [''],});
  }
  initDataForm() {
    this.searchDataForm = this.fb.group({
      data: ['', [Validators.required, textValidator]],
    });
  }
  /* ----------------------------------------------------- envio de formularios */
  searchDataSubmit() {
    this.updateResultFormStatus();
    this.loading=true;
    const { data } = this.searchDataForm.value;
    this.userServ.getAllUsers().subscribe({
      next: (res) => {
        this.userResults = res.data.filter((user) =>
          this.matchName(user, data)
        );
      },
      error(err) {console.log(err);},
      complete:()=>(this.loading = false)
    });
  }
  searchCvuSubmit() {
    this.updateResultFormStatus();
    const { cvu } = this.searchCvuForm.value;
    this.userServ.getAllUsers().subscribe({
      next: (res) => {
        this.userResults = res.data.filter((user) => this.matchName(user, cvu));
        console.log(this.userResults);
      },
      error(err) {console.log(err);},
      complete:()=>(this.loading = false)
    });
  }

  matchName(data: User, searchName: string) {
    const nameLastname = (data.name + ' ' + data.lastName).toLowerCase();
    return nameLastname.includes(searchName.toLowerCase().trim());
  }
  /* -----------------------------ver contacto */
  initTransfer(id:number){
    const userTarget = this.userResults[id] as IUserTarget;
    this.idUserTarget.emit(userTarget);
    this.updateContactStatus();
  }
}
