package org.skill.internetbankapi.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
@Data
@Service
@RequiredArgsConstructor
public class FinanceOperation {
    private Date dateOperation;
    private String operationType;
    private Long userId;
    private float sum;
    private Date startDate;
    private Date finishDate;
    private Long userIdReceiver;
}

