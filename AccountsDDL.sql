create table ACCOUNTS (
	CUSTOMER_ID int primary key,
	PASSWORD varchar(12),
	OLD_PASSWORD varchar(12) default null,
	ACCOUNT_NO number(8) unique,
	IFSC_CODE varchar(15) unique,
	BALANCE number default 5000,
	ACCOUNT_TYPE varchar(10),
	MOBILE_NO number(10) unique
);