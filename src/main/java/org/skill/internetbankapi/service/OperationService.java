package org.skill.internetbankapi.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.skill.internetbankapi.entity.UserAccount;
import org.skill.internetbankapi.model.ResultAccontOperation;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

import static org.skill.internetbankapi.config.AccountsConfiguration.*;
import static org.skill.internetbankapi.config.NameError.*;

@Service
@Data
@RequiredArgsConstructor
public class OperationService {
    private final ResultAccontOperation result;

    public ResultAccontOperation financeOperation(String operationType, Long userId, String sum) throws NoSuchElementException {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(UserAccount.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<UserAccount> listUserAccount = session.createQuery(REQUEST_USER_ACCOUNTS)
                .list()
                .stream()
                .filter(p -> {
                    UserAccount s = (UserAccount) p;
                    return s.getUserId() == userId;
                })
                .toList();

        switch (operationType) {
            case GET_BALANCE:
                try {
                    float bal = listUserAccount.getFirst().getBalance();
                    result.setValue(String.format("%.2f", bal));
                    result.setComment("");
                } catch (NoSuchElementException e) {
                    result.setValue("-1");
                    result.setComment(ACCOUNT_NOT_FOUND);
                }
                break;
            case TAKE_MONEY:
                try {
                    float bal = listUserAccount.getFirst().getBalance();
                    bal = bal - Float.parseFloat(sum);
                    if (bal < 0) {
                        result.setComment(INSUFFICIENT_FUNDS);
                        result.setValue("0");
                        bal = listUserAccount.getFirst().getBalance();
                    } else {
                        if (Integer.parseInt(sum) <= 0) {
                            result.setComment(WITHDRAWALS_ZERO);
                            result.setValue("0");
                        } else {
                            listUserAccount.getFirst().setBalance(bal);
                            session.update(listUserAccount.getFirst());
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
                        float bal = listUserAccount.getFirst().getBalance();
                        bal = bal + Float.parseFloat(sum);
                        listUserAccount.getFirst().setBalance(bal);
                        session.update(listUserAccount.getFirst());
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
        tx.commit();
        return result;
    }
}
