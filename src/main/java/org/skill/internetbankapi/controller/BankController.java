package org.skill.internetbankapi.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.skill.internetbankapi.model.ResultAccountOperation;
import org.skill.internetbankapi.model.ResultFinal;
import org.skill.internetbankapi.service.FinanceOperation;
import org.skill.internetbankapi.service.OperationService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.skill.internetbankapi.config.NameError.INPUT_DATE_NOT_VALID;
import static org.skill.internetbankapi.model.JsonUtil.serialToJs;

@Data
@RestController
@RequiredArgsConstructor
public class BankController {
    private final OperationService operationService;
    private List<ResultAccountOperation> resultAccountOperationList;
    private final FinanceOperation financeOperation;
    private String resultReportOperationsList;
    private ResultFinal resultFinal;


    @GetMapping("/api")
    public String getRequest(@RequestParam(name = "operation") String operation,
                             @RequestParam(name = "userid") String userAcc,
                             @RequestParam(name = "sum", required = false) String sum,
                             @RequestParam(name = "useridrec", required = false) String accReceiver,
                             @RequestParam(name = "startdate", required = false) String startDate,
                             @RequestParam(name = "finishdate", required = false) String finishDate
    ) throws ParseException {
        financeOperation.setOperationType(operation);
        financeOperation.setUserId(Long.parseLong(userAcc));
        financeOperation.setSum(sum);
        if (accReceiver != null) financeOperation.setUserIdReceiver(Long.parseLong(accReceiver));
        try {
            if (startDate != null)
                financeOperation.setStartDate(new SimpleDateFormat("dd-MM-yyyy").parse(startDate));
            if (finishDate != null)
                financeOperation.setFinishDate(new SimpleDateFormat("dd-MM-yyyy").parse(finishDate));
        } catch (ParseException e) {
            return INPUT_DATE_NOT_VALID;
        }
        resultFinal = operationService.financeOperation(financeOperation);
        return serialToJs(resultFinal);
    }
}

