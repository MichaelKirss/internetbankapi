package org.skill.internetbankapi.entity;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "OPERATIONS",schema="public" )
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATA_OPER")
    private Date dataOper;
    @Column(name = "USER_ACC")
    private int userAcc;
    @Column(name = "TYPE_OPERATION")
    private int typeOperation;
    @Column(name = "STATUS_OPERATION")
    private int statusOperation;
    @Column(name = "SUM_OPERATION")
    private float sumOperation;
    @Column(name = "API_METHOD")
    private String apiMethod;


}
