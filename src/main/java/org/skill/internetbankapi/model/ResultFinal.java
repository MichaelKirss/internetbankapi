package org.skill.internetbankapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
@RequiredArgsConstructor
public class ResultFinal
{

    @SerializedName("OperationList")
    private ArrayList <ResultReportOperation> ResultReportOperationList;
    @SerializedName("PutMoney")
    private ArrayList <ResultAccountOperation> putMoney;
    @SerializedName("Balance")
    private ArrayList <ResultAccountOperation> balance;
    @SerializedName("TakeMoney")
    private ArrayList <ResultAccountOperation> takeMoney;
    @SerializedName("TransferMoney")
    private ArrayList <ResultAccountOperation> transferMoney;







}
