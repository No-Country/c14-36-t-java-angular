export const environment = {
  production: true,
  apiLogin: "http://181.15.143.132:9698/v1/api/register/auth/",
  apiRegister: "http://181.15.143.132:9698/v1/api/register/",
  api2:"http://181.15.143.132:9698/v1/api/customers?page=0&size=4&order=1&field=id",
  apiGetUser:"http://181.15.143.132:9698/v1/api/customers/",
  apiGetAllUser:"http://181.15.143.132:9698/v1/api/customers",
  apiGetAccount:"http://181.15.143.132:9698/v1/api/accounts/",
  apiGetCard:"http://181.15.143.132:9698/v1/api/cards/",
  apiNewTransfer:"http://181.15.143.132:9698/v1/api/customers/transactions/new",
  apiFilterUser:"http://181.15.143.132:9698/v1/api/customers/search/",
  apiGetAllPayments:"/assets/temporaryServices.json",
  apiNewPayment:"http://181.15.143.132:9698/v1/api/customers/transactions/bill_pay",
  apiGetPayment:"http://181.15.143.132:9698/v1/api/customers/transactions/bills_type"
}
