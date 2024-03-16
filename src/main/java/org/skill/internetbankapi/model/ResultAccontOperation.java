package org.skill.internetbankapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResultAccontOperation {
    @SerializedName("Value")
    private String value;
    @SerializedName("Comment")
    private String comment;
}
