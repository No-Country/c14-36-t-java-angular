export const environment = {
    production: false,
    apiLogin: "http://localhost:8080/v1/api/register/auth/",
    apiRegister: "http://localhost:8080/v1/api/register/",
    api2:"http://localhost:8080/v1/api/customers?page=0&size=4&order=1&field=id",
    apiGetUser:"http://localhost:8080/v1/api/customers/",
    apiGetAllUser:"http://localhost:8080/v1/api/customers",
    apiGetAccount:"http://localhost:8080/v1/api/accounts/",
    apiGetCard:"http://localhost:8080/v1/api/cards/",
    apiNewTransfer:"http://localhost:8080/v1/api/customers/transactions/new",
    apiFilterUser:"http://localhost:8080/v1/api/customers/search/",
    apiGetAllPayments:"/assets/temporaryServices.json",
    apiNewPayment:"http://localhost:8080/v1/api/customers/transactions/bill_pay",
    apiGetPayment:"http://localhost:8080/v1/api/customers/transactions/bills_type"
}
