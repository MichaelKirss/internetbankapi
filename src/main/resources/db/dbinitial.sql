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
	id serial primary key NOT NULL,
	data_oper date NOT NULL,
	user_acc int4 NOT NULL,
	type_operation int4 NOT NULL,
	sum numeric(16, 2),
	FOREIGN key (user_acc) references user_account(id),
	FOREIGN key (type_operation) references type_operations(id) 
);
