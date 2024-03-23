package org.skill.internetbankapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultReportOperation {
    @SerializedName("DateOperation")
    private String dateOper;
    @SerializedName("TypeOperation")
    private String typeOperation;
    @SerializedName("SumOperation")
    private String sumOperation;

    }


