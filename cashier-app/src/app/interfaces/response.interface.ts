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
export interface IGetAllUser{
  data:IUserProfile[],
  message:string
}

