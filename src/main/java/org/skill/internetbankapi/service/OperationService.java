package org.skill.internetbankapi.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.skill.internetbankapi.entity.UserAccount;
import org.skill.internetbankapi.model.ResultAccontOperation;
import org.skill.internetbankapi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.skill.internetbankapi.config.AccountsConfiguration.*;
import static org.skill.internetbankapi.config.NameError.*;

@Service
@Data
@RequiredArgsConstructor
public class OperationService {
    private final ResultAccontOperation result;
    private final UserAccountRepository userAccountRepository;

    public ResultAccontOperation financeOperation(String operationType, Long userId, String sum) throws NoSuchElementException {
        List<UserAccount> userAccountList = userAccountRepository.findAll()
                .stream()
                .filter(p -> {
                    UserAccount s = (UserAccount) p;
                    return s.getUserId() == userId;
                })
                .toList();

        switch (operationType) {
            case GET_BALANCE:
                try {
                    float bal = userAccountList.getFirst().getBalance();
                    result.setValue(String.format("%.2f", bal));
                    result.setComment("");
                } catch (NoSuchElementException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                break;
            case TAKE_MONEY:
                try {
                    float bal = userAccountList.getFirst().getBalance();
                    bal = bal - Float.parseFloat(sum);
                    if (bal < 0) {
                        result.setComment(INSUFFICIENT_FUNDS);
                        result.setValue("0");
                        bal = userAccountList.getFirst().getBalance();
                    } else {
                        if (Integer.parseInt(sum) <= 0) {
                            result.setComment(WITHDRAWALS_ZERO);
                            result.setValue("0");
                        } else {
                            userAccountList.getFirst().setBalance(bal);
                            userAccountRepository.save(userAccountList.getFirst());
                            result.setValue("1");
                            result.setComment("");
                        }
                    }
                } catch (NoSuchElementException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                break;
            case PUT_MONEY:
                try {
                    if (Integer.parseInt(sum) <= 0) {
                        result.setComment(DEPOSIT_ZERO);
                        result.setValue("0");
                    } else {
                        float bal = userAccountList.getFirst().getBalance();
                        bal = bal + Float.parseFloat(sum);
                        userAccountList.getFirst().setBalance(bal);
                        userAccountRepository.save(userAccountList.getFirst());
                        result.setValue("1");
                        result.setComment("");
                    }
                } catch (NoSuchElementException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                break;
            case GET_OPERATION_LIST:

                break;
            case TRANSFER_MONEY:

                break;
            default:
                result.setValue("-1");
                result.setComment(OPERATION_NOT_VALID);
        }
        return result;
    }
}
