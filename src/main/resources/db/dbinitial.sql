CREATE TABLE USER_ACCOUNT (
 ID SERIAL PRIMARY KEY NOT NULL,
 USER_ID INTEGER not null,
 BALANCE numeric (16,2)
);

INSERT INTO USER_ACCOUNT (user_id,balance)
	VALUES (17,650),(19,6300),(13,1500),(15,27300);
CREATE TABLE operations (
	ID SERIAL primary key NOT NULL,
	DATA_OPER date ,
	USER_ACC int4 ,
	USER_ACC_RECEIVER int4,
	TYPE_OPERATION VARCHAR ,
	STATUS_OPERATION  int4 ,
	SUM_OPERATION numeric(16, 2),
	COMMENT VARCHAR
);
