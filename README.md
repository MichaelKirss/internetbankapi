Для работы приложения создаем базу счетов пользователей с остатками
Иинициализация базы данных счетов resources/db/dbinitial.sql

Реализовано

Запрос баланса метод API getBalance 
МЕТОД GET
endpoint "/acc"
параметры  запроса "?operation=getBalance&userid=12"

Снятие со счета метод API takeMoney 
МЕТОД GET
endpoint "/oper"
параметры запроса в запросе "?operation=takeMoney&userid=12&sum=100"

Внесение на счет метод API putMoney 
МЕТОД GET
endpoint "/oper"
параметры запроса в запросе "?operation=putMoney&userid=12&sum=100"

Входные параметры
operation - метод API
userid - ID пользователя
sum - Сумма