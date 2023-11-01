import { IUserProfile, User } from './User.interface';

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
  data: IUserProfile[];
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
