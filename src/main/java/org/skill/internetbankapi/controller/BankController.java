package org.skill.internetbankapi.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.skill.internetbankapi.model.ResultAccontOperation;
import org.skill.internetbankapi.service.OperationService;
import org.springframework.web.bind.annotation.*;

import static org.skill.internetbankapi.model.JsonUtil.serialToJs;

@Data
@RestController
@RequiredArgsConstructor
public class BankController {
    private final OperationService operationService;
    private ResultAccontOperation resultAccontOperation;

    @GetMapping("/acc")
    public String getAcc(@RequestParam("operation") String operation,
                         @RequestParam("userid") String usersId) {
        Long userId = Long.parseLong(usersId);
        resultAccontOperation = operationService.financeOperation(operation, userId, "");
        return serialToJs(resultAccontOperation);
    }

    @GetMapping("/oper")
    public String getOper(@RequestParam("operation") String operation,
                          @RequestParam("userid") String usersId,
                          @RequestParam("sum") String sum) {

        Long userId = Long.parseLong(usersId);
        resultAccontOperation = operationService.financeOperation(operation, userId, sum);
        return serialToJs(resultAccontOperation);
    }
}
