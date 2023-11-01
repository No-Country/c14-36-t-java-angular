export interface IAccount {
  idAccount: string;
  cvu: string;
  openAccountDate: string;
  totalAccount: number;
  status: boolean;
}
export interface ICard {
  idCard: string;
  cardNumber: string;
  cardName: string;
  expirationDate: string;
  securityCode: string;
}
