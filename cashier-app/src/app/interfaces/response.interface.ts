import { IUserProfile } from './User.interface';
import { IAccount, ICard } from './account.interface';

export interface ILoginRes {
  data: {
    id: string;
    message: string;
    token: string;
    timeStamp: string;
  };
  url: string;
}
export interface IRegistRes {
  data: {
    message: string;
    timeStamp: string;
  };
}
export interface IGetUserRes {
  success: boolean;
  message: string;
  data: IUserProfile;
  timeStamp: string;
}

export interface IGetAllUserRes {
  data: {
    content: IUserProfile[];
    empty: boolean;
    first: boolean;
    last: boolean;
    number: number;
    numberOfElements: number;
    pageabble: {
      offset: number;
      pageNumber: number;
      pageSize: number;
      paged: boolean;
      unpaged: boolean;
      size: number;
    };
  };
  message: string;
}

export interface ItransactionRes {
  id: string;
  origin: string;
  destination: string;
  amount: number;
  dateEmit: string;
  type: string;
  state: string;
  reason: string;
}
export interface IGetAccountRes {
  success: boolean;
  message: string;
  data: IAccount;
  timeStamp: string;
}
export interface IGetPayment {
  entidad: string;
  dataExpired: string;
  idFactura: string;
  monto: number;
  img: string;
}
export interface IBillRes {
  id: string;
  dateEmit: string
  bill_type: string;
  bill_num: string;
  amount: number;
  voucher_num: string;
  state: string;
  type: string;
}
export interface IcardRes {
  success: boolean;
  message: string;
  data: ICard;
  timeStamp: string;
}
