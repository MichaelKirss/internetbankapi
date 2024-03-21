CREATE TABLE USER_ACCOUNT (
 ID SERIAL PRIMARY KEY NOT NULL,
 USER_ID INTEGER not null,
 BALANCE numeric (16,2)
);
CREATE TABLE type_operations (
	id serial primary key NOT NULL,
	api_method VARCHAR NOT NULL,
	METHOD_NAME VARCHAR NOT NULL);

INSERT INTO USER_ACCOUNT (user_id,balance)
	VALUES (17,650),(19,6300),(13,1500),(15,27300);

INSERT INTO type_operations (api_method,METHOD_NAME)
	VALUES 	('getBalance', 'Запрос баланса'),
			('takeMoney', 'Снятие со счета'),
			('putMoney', 'Пополнение счета'),
			('getOperationList', 'Запрос операций'),
			('transferMoney', 'Перевод другому');			

CREATE TABLE operations (
	ID SERIAL primary key NOT NULL,
	DATA_OPER date NOT NULL,
	USER_ACC int4 NOT NULL,
	TYPE_OPERATION int4 NOT NULL,
	STATUS_OPERATION  int4 NOT NULL,
	SUM_OPERATION numeric(16, 2),
	API_METHOD VARCHAR NOT NULL,
	FOREIGN key (user_acc) references user_account(id),
	FOREIGN key (type_operation) references type_operations(id) 
);
