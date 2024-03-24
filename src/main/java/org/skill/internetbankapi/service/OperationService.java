package org.skill.internetbankapi.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.skill.internetbankapi.entity.Operations;
import org.skill.internetbankapi.entity.UserAccount;
import org.skill.internetbankapi.model.ResultAccountOperation;
import org.skill.internetbankapi.model.ResultFinal;
import org.skill.internetbankapi.model.ResultReportOperation;
import org.skill.internetbankapi.repository.OperationsRepository;
import org.skill.internetbankapi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.skill.internetbankapi.config.AccountsConfiguration.*;
import static org.skill.internetbankapi.config.NameError.*;


@Service
@Data
@RequiredArgsConstructor
public class OperationService {
    private final ResultAccountOperation result;
    private final UserAccountRepository userAccountRepository;
    private final FinanceOperation financeOperation;
    private final OperationsRepository operationsRepository;
    private final ResultFinal resultFinal;

    public ResultFinal financeOperation(FinanceOperation financeOperation) throws NoSuchElementException, ParseException {
        List<ResultAccountOperation> resultAccountOperationList = new ArrayList<>();
        UserAccount userAccount = null;
        try {
            userAccount = userAccountRepository.findAll()
                    .stream()
                    .filter(s -> {
                        return s.getUserId() == financeOperation.getUserId();
                    })
                    .toList()
                    .getFirst();

        } catch (NoSuchElementException e) {
        }
        switch (financeOperation.getOperationType()) {
            case GET_BALANCE:
                try {
                    float bal = userAccount.getBalance();
                    result.setValue(String.format("%.2f", bal));
                    result.setComment("");
                    saveOrUpdateOperation(userAccount, null);
                } catch (NullPointerException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                resulFinanceOperationToZero();
                resultAccountOperationList.addLast(result);
                resultFinal.setBalance((ArrayList<ResultAccountOperation>) resultAccountOperationList);

                break;
            case TAKE_MONEY:
                try {
                    float bal = userAccount.getBalance();
                    bal = bal - Float.parseFloat(financeOperation.getSum());
                    if (bal < 0) {
                        result.setComment(INSUFFICIENT_FUNDS);
                        result.setValue("0");
                        bal = userAccount.getBalance();
                    } else {
                        if (Integer.parseInt(financeOperation.getSum()) <= 0) {
                            result.setComment(WITHDRAWALS_ZERO);
                            result.setValue("0");
                        } else {
                            userAccount.setBalance(bal);
                            result.setValue("1");
                            result.setComment("");
                            saveOrUpdateOperation(userAccount, null);
                        }
                    }
                } catch (NullPointerException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                resulFinanceOperationToZero();
                resultAccountOperationList.addLast(result);
                resultFinal.setTakeMoney((ArrayList<ResultAccountOperation>) resultAccountOperationList);
                break;
            case PUT_MONEY:
                try {
                    if (Integer.parseInt(financeOperation.getSum()) <= 0) {
                        result.setComment(DEPOSIT_ZERO);
                        result.setValue("0");
                    } else {
                        float bal = userAccount.getBalance();
                        bal = bal + Float.parseFloat(financeOperation.getSum());
                        userAccount.setBalance(bal);
                        result.setValue("1");
                        result.setComment("");
                        saveOrUpdateOperation(userAccount, null);
                    }
                } catch (NullPointerException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                resulFinanceOperationToZero();
                resultAccountOperationList.addLast(result);
                resultFinal.setPutMoney((ArrayList<ResultAccountOperation>) resultAccountOperationList);
                break;
            case TRANSFER_MONEY:
                try {
                    UserAccount userAccountReceiver = userAccountRepository.findAll()
                            .stream()
                            .filter(s -> {
                                return s.getUserId() == financeOperation.getUserIdReceiver();
                            })
                            .toList()
                            .getFirst();
                    try {
                        float bal = userAccount.getBalance();
                        bal = bal - Float.parseFloat(financeOperation.getSum());
                        if (bal < 0) {
                            result.setComment(INSUFFICIENT_FUNDS);
                            result.setValue("0");
                            bal = userAccount.getBalance();

                        } else {
                            if (Integer.parseInt(financeOperation.getSum()) <= 0) {
                                result.setComment(WITHDRAWALS_ZERO);
                                result.setValue("0");

                            } else {
                                userAccount.setBalance(bal);
                                result.setValue("1");
                                result.setComment("");
                            }
                            if (result.getValue() == "1") {
                                float bal1 = userAccountReceiver.getBalance();
                                bal1 = bal1 + Float.parseFloat(financeOperation.getSum());
                                userAccountReceiver.setBalance(bal1);
                                result.setValue("1");
                                result.setComment("");
                                saveOrUpdateOperation(userAccount, userAccountReceiver);
                            }
                        }
                    } catch (NullPointerException e) {
                        result.setValue("-1");
                        result.setComment(ACCOUNT_NOT_FOUND);
                    }
                } catch (NoSuchElementException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_RECEIVER_NOT_FOUND);

                }
                resulFinanceOperationToZero();
                resultAccountOperationList.addLast(result);
                resultFinal.setTransferMoney((ArrayList<ResultAccountOperation>) resultAccountOperationList);
                break;
            case GET_OPERATION_LIST:
                resulFinanceOperationToZero();
                List<Operations> operationsList = (ArrayList<Operations>) operationsRepository.findAll();
                List<ResultReportOperation> resultReportOperationList = new ArrayList<>();
                Date startDate = null;
                Date finishDate = null;
                if (financeOperation.getStartDate() == null) {
                    startDate = new SimpleDateFormat("dd-MM-yyyy").parse(BEGIN_START_DATE);
                } else {
                    startDate = financeOperation.getStartDate();
                }
                if (financeOperation.getFinishDate() == null) {
                    finishDate = new Date();
                } else {
                    finishDate = financeOperation.getFinishDate();
                }
                for (Operations operations : operationsList) {
                    Date operationDate = operations.getDataOper();
                    String dateOper = new SimpleDateFormat("dd-MM-yyyy").format(operations.getDataOper());
                    if (Long.valueOf(operations.getUserAcc()) == Long.valueOf(financeOperation.getUserId())) {
                        if (startDate.before(operations.getDataOper()) &&
                                finishDate.after(operations.getDataOper())
                        ) {
                            resultReportOperationList.addLast(new ResultReportOperation(
                                    dateOper,
                                    operations.getTypeOperation(),
                                    String.format("%.2f", operations.getSumOperation())));
                        } else if (startDate.equals(operations.getDataOper()) ||
                                finishDate.equals(operations.getDataOper()))
                            resultReportOperationList.addLast(new ResultReportOperation(
                                    dateOper,
                                    operations.getTypeOperation(),
                                    String.format("%.2f", operations.getSumOperation())));
                    } else if (Long.valueOf(operations.getUserAccRec()) == Long.valueOf(financeOperation.getUserId())) {
                        Float recSum = Float.valueOf(financeOperation.getSum());
                        resultReportOperationList.addLast(new ResultReportOperation(
                                dateOper,
                                operations.getTypeOperation(),
                                String.format("%.2f", recSum)));
                    }
                }
                resultFinal.setResultReportOperationList((ArrayList<ResultReportOperation>) resultReportOperationList);
                break;
            default:
                resulFinanceOperationToZero();
                result.setValue("-1");
                result.setComment(OPERATION_NOT_VALID);
                resultAccountOperationList.addLast(result);
                resultFinal.setBalance((ArrayList<ResultAccountOperation>) resultAccountOperationList);
        }
        return resultFinal;
    }

    @Transactional
    private void saveOrUpdateOperation(UserAccount userAccount, UserAccount userAccountReciver) {
        operationsRepository.save(new Operations());
        Operations operationItem = operationsRepository.findAll()
                .stream()
                .toList()
                .getLast();
        operationItem.setDataOper(new Date());
        operationItem.setUserAcc(userAccount.getUserId());
        operationItem.setComment("");
        operationItem.setUserAccRec(0L);
        operationItem.setSumOperation(0F);
        operationItem.setTypeOperation(financeOperation.getOperationType());
        switch (financeOperation.getOperationType()) {
            case GET_BALANCE:
                operationItem.setStatusOperation(1);
                operationItem.setSumOperation(userAccount.getBalance());
                operationsRepository.save(operationItem);
                break;
            case TAKE_MONEY:
                operationItem.setStatusOperation(Integer.parseInt(result.getValue()));
                operationItem.setComment(result.getComment());
                Float debit = Float.valueOf(Float.parseFloat(financeOperation.getSum())) * (-1F);
                operationItem.setSumOperation(debit);
                operationsRepository.save(operationItem);
                userAccountRepository.save(userAccount);
                break;
            case PUT_MONEY:
                operationItem.setStatusOperation(Integer.parseInt(result.getValue()));
                operationItem.setComment(result.getComment());
                operationItem.setSumOperation(Float.parseFloat(financeOperation.getSum()));
                operationsRepository.save(operationItem);
                userAccountRepository.save(userAccount);
                break;
            case TRANSFER_MONEY:
                operationItem.setStatusOperation(Integer.parseInt(result.getValue()));
                operationItem.setComment(result.getComment());
                debit = Float.valueOf(Float.parseFloat(financeOperation.getSum())) * (-1F);
                operationItem.setSumOperation(debit);
                operationItem.setUserAccRec(financeOperation.getUserIdReceiver());
                operationsRepository.save(operationItem);
                userAccountRepository.save(userAccount);
                userAccountRepository.save(userAccountReciver);
            default:
        }
    }

    private void resulFinanceOperationToZero() {
        resultFinal.setBalance(null);
        resultFinal.setTakeMoney(null);
        resultFinal.setPutMoney(null);
        resultFinal.setTransferMoney(null);
        resultFinal.setResultReportOperationList(null);
    }
}

