import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { textValidatorToFilter } from 'src/app/CustomValidator/customValidator';
import {
  enterLateral,
  fadeAnimation,
  scaling,
} from 'src/app/animations/animation';
import { IUserProfile, IUserTarget } from 'src/app/interfaces/User.interface';
import { IAccount } from 'src/app/interfaces/account.interface';
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
  @Input() accountData!: IAccount;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Output() idUserTarget = new EventEmitter<IUserTarget>();
  searchDataForm!: FormGroup;
  searchCvuForm!: FormGroup;
  userResults: IUserProfile[] = [];
  loading = false;
  page = 0;

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
      alertSuccess: false,
      alertFail:false,
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
  updateResultFormStatus() {
    const newStatus: transactionView = {
      ...this.viewStatus,
      formResult: true,
      alertSuccess: false,
      alertFail: false,
      contact: false,
    };
    this.updateViews.emit(newStatus);
  }
  /* ------------------------------------------------------crear formularios */
  initCvuForm() {
    this.searchCvuForm = this.fb.group({ cvu: [''] });
  }
  initDataForm() {
    this.searchDataForm = this.fb.group({
      data: ['', [Validators.required, textValidatorToFilter]],
    });
  }
  /* ----------------------------------------------------- envio de formularios */
  searchDataSubmit() {
    this.updateResultFormStatus();
    this.loading = true;
    this.page = 0;
    this.searchWordInPage(this.page);
  }
  /* ----------------------------------------------------------FALTA IMPLEMTENTAR */
  searchCvuSubmit() {
    this.updateResultFormStatus();
    const { cvu } = this.searchCvuForm.value;
    this.userServ.getAllUsers().subscribe({
      next: (res) => {
        this.userResults = res.data.filter((user) => this.matchName(user, cvu));
        console.log(this.userResults);
      },
      error(err) {
        console.log(err);
      },
      complete: () => (this.loading = false),
    });
  }
  /* ----------------------------------paginación */
  forwardPage(isNext: boolean) {
    if (this.userResults === null) return;

    if (isNext && this.userResults.length > 0) {
      this.page++;
      this.searchWordInPage(this.page);
    }
    if (!isNext && this.page > 0) {
      this.page--;
      this.searchWordInPage(this.page);
    }
  }

  matchName(data: IUserProfile, searchName: string) {
    const nameLastname = (data.name + ' ' + data.lastName).toLowerCase();
    return nameLastname.includes(searchName.toLowerCase().trim());
  }
  /* -----------------------------ver contacto */
  initTransfer(id: number) {
    const userTarget = this.userResults[id] as IUserTarget;
    this.idUserTarget.emit(userTarget);
    this.updateContactStatus();
  }
  /*--------------- consultar resultado en pagina "?"*/
  searchWordInPage(page: number) {
    const { data } = this.searchDataForm.value;
    const nameSearch = (data as string).toLocaleLowerCase().trim();

    this.userServ.filterUser(nameSearch, page).subscribe({
      next: (res) => {
        this.userResults = res.data.filter(
          (user) =>
            !(user.idAccount === this.accountData.idAccount ||
            user.idCard === null)
        );
      },
      error(err) {
        console.log(err);
      },
      complete: () => (this.loading = false),
    });
  }
}
