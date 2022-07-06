# ATM application

This is an implementation of ATM service using spring-boot.

# Requirements
For building and running we need Java 1.8 and Maven 3
Build -> "mvn clean install"
Run -> "mvn spring-boot:run"

# Balance API

POST http://localhost:8080/transactions
Body: {
    "accountDetails": {
        "accountNumber": 123456789,
        "pin": 1234
    },
    "txnType": "BALANCE"
}

# Withdraw API

POST http://localhost:8080/transactions
Body: {
    "accountDetails": {
        "accountNumber": 123456789,
        "pin": 1234
    },
    "txnType": "WITHDRAW",
    "amount": 100
}
