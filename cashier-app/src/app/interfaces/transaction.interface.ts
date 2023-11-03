export interface ITransactionDTO {
  type: string;
  amount: number;
  origin: string;
  destination: string;
  reason: string;
}
export interface IBillDTO {
  origin: string;
  type: string;
  bill_type: string;
  bill_num: string;
  amount: number;
}
