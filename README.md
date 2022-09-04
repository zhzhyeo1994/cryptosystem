# cryptosystem API endpoints
crypto system assignment

These endpoints allow you to retrieve user's transactions, cryptocurrencies portfolio, get latest best aggregated cryptocurrency price and trade (buy & sell) crypto.

## GET
 [/api/v1/crypto/transactions/{customerId}] <br/>
 [/api/v1/crypto/portfolio/{customerId}] <br/>
 [/api/v1/crypto/token/{customerId}] <br/>
 [/api/v1/crypto/currencies] <br/>
 [/api/v1/crypto/currency/{currencyId}] <br/>
 
 
## POST
[/api/v1/crypto/transactions/trade] <br/> <br/>
___

### GET /api/v1/crypto/transactions/{customerId} (sample: localhost:8080/api/v1/crypto/transaction/C1W1)
Get all transactions history of an user. (Take in a path variable "customerId").  <br>
No data initialization for transaction. To see results, made at least one transaction using the trade API endpoint. 

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `customerId` | required | string  | The customer for which to perform the action.                                                                     |
|                                                                |

**Response sample**

```
[
    {
        "transactionId": "Sun Sep 04 14:33:31 SGT 2022",
        "walletId": "walb1w1",
        "txnId": "txnSun Sep 04 14:33:31 SGT 2022",
        "status": "SUCCESS",
        "totalCost": 16009.0,
        "tokenAmount": 10.0,
        "type": "BUY",
        "paymentBy": "USDT",
        "tradeDate": "2022-09-04T06:33:31.695+00:00",
        "crypto": {
            "currencyId": "ETHUSDT",
            "name": "EHT/USDT",
            "bidPrice": 1548.1,
            "askPrice": 1548.1
        }
    },
    {
        "transactionId": "Sun Sep 04 14:34:24 SGT 2022",
        "walletId": "walb1w1",
        "txnId": "txnSun Sep 04 14:34:24 SGT 2022",
        "status": "SUCCESS",
        "totalCost": 19722.9,
        "tokenAmount": 1.0,
        "type": "BUY",
        "paymentBy": "USDT",
        "tradeDate": "2022-09-04T06:34:24.366+00:00",
        "crypto": {
            "currencyId": "BTCUSDT",
            "name": "BTC/USDT",
            "bidPrice": 19704.0,
            "askPrice": 19704.0
        }
    }
]
```
**Return** <br>
Return list of transactions based on customerId passed in.
___

### GET [/api/v1/crypto/portfolio/{customerId}] (sample: localhost:8080/api/v1/crypto/portfolio/C1W1)
Get all customer's user crypto currencies wallet balance in customer's portfolio. (Take in a path variable "customerId").  <br>
Data is initialized for balance table. There will be one balance with initial value of 50000 USDT 

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `customerId` | required | string  | The customer for which to perform the action.                                                                     |
|                                                                |

**Response sample**

```
{
    "cryptoList": [
        {
            "tokenValue": 14268.099999999999,
            "tokenAmount": 14268.099999999999,
            "symbol": "USDT"
        },
        {
            "tokenValue": 16009.0,
            "tokenAmount": 16009.0,
            "symbol": "ETHUSDT"
        },
        {
            "tokenValue": 19722.9,
            "tokenAmount": 19722.9,
            "symbol": "BTCUSDT"
        }
    ],
    "totalValue": 50000.0
}
```
**Return** <br>
Return list of cryptocurrencies with totalValue of the portfolio based on customerId passed in.
___

### GET [/api/v1/crypto/token/{customerId}?currencyId=C1W1] (sample: localhost:8080/api/v1/crypto/token/C1W1?curencyId=BTCUSDT)

Get cryptocurrency by currencyId and customerId. (Take in a path variable "customerId" and a request params of currencyId).  <br>
Data is initialized for customer and currency table. There will be one customer with customer id of C1W1 and three currency id (USDT, BTCUSDT, ETHUSDT)

**Parameters**

|          Name | Required |  Type   | Description                                                                                                                                                           |
| -------------:|:--------:|:-------:| --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|     `customerId` | required | string  | The customer for which to perform the action.                                                                     |
|     `currencyId` | required | string  | The currency value that customer want to see.

**Response sample**

```
{
    "tokenValue": 19722.9,
    "tokenAmount": 1.0,
    "symbol": "BTCUSDT"
}
```
**Return** <br>
Return cryptocurrency object based customerId and currencyId
___

### GET [/api/v1/crypto/currencies] (sample: localhost:8080/api/v1/crypto/currencies)
Get the best and latest aggregated price of all the cryptocurrencies <br>
Data is initialized for currency table. Able to hit the endpoint straight to retrieve data

**Response sample**

```
[
    {
        "currencyId": "BTCUSDT",
        "name": "BTC/USDT",
        "bidPrice": 19704.77,
        "askPrice": 19704.77
    },
    {
        "currencyId": "ETHUSDT",
        "name": "EHT/USDT",
        "bidPrice": 1546.67,
        "askPrice": 1546.67
    },
    {
        "currencyId": "USDT",
        "name": "Tether",
        "bidPrice": 1.0,
        "askPrice": 1.0
    }
]
```
**Return** <br>
Return list of cryptocurrencies latest and best price.
___


### GET [/api/v1/crypto/currency/{currencyId}] (sample: localhost:8080/api/v1/crypto/currencies/BTCUSDT)
Get the best and latest aggregated price of one cryptocurrency specified by Customer <br>
Data is initialized for currency table. Able to hit the endpoint straight to retrieve data

**Response sample**

```
{
    "currencyId": "BTCUSDT",
    "orderType": "BUY",
    "tokenAmount":  1,
    "orderPrice": 19722.9,
    "customerId": "C1W1"
}
```
**Return** <br>
Return the latest and best price of specified cryptocurrency.
___

### POST [/api/v1/crypto/transactions/trade]
Endpoint to allow user to buy or sell cryptocurrency using Balance USDT in wallet.
To buy crypto below request sample can be used, to sell simple change orderType to "SELL". Please ensure that sufficient amount in wallet.
Exceptions will be thrown. 

**Request sample**
```
{
    "currencyId": "BTCUSDT",
    "orderType": "BUY",
    "tokenAmount":  1,
    "orderPrice": 19722.9,
    "customerId": "C1W1"
}
```
**Exceptions** <br>
PriceChangedException will be thrown if buy order have a order price lower than ask price or if sell order have a order price lower than the bid price. <br>
InsufficientValueException will be thrown if customer buy or sell an amount that they cannot afford. <br>
___
