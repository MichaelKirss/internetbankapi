package org.skill.internetbankapi.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
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


    @GetMapping("/api/{operation}")
    public String getRequest(@PathVariable String operation,
                             @RequestParam(name = "userid") String userAcc,
                             @RequestParam(name = "sum", required = false) String sum,
                             @RequestParam(name = "useridrec", required = false) String accReceiver,
                             @RequestParam(name = "startdate", required = false) String startDate,
                             @RequestParam(name = "finishdate", required = false) String finishDate
    ) throws ParseException {
        float sumFloat;
        financeOperation.setOperationType(operation);
        financeOperation.setUserId(Long.parseLong(userAcc));
        if (sum !=null) {
            sumFloat = Float.valueOf(Float.parseFloat(sum));
            financeOperation.setSum(sumFloat / 100);
        }
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

