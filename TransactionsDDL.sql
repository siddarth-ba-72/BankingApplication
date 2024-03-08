create table TRANSACTIONS(
    TRANSACTION_ID varchar(36) primary key,
    FROM_ACCOUNT_NUMBER long,
    TO_ACCOUNT_NUMBER long,
    AMOUNT number
);