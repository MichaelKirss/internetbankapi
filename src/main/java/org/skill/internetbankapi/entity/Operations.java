package org.skill.internetbankapi.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "OPERATIONS",schema="public" )
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATA_OPER")
    private Date dataOper;
    @Column(name = "USER_ACC")
    private Long userAcc;
    @Column(name = "TYPE_OPERATION")
    private String typeOperation;
    @Column(name = "STATUS_OPERATION")
    private int statusOperation;
    @Column(name = "SUM_OPERATION")
    private float sumOperation;
    @Column(name = "USER_ACC_RECEIVER")
    private Long userAccRec;
    @Column(name = "COMMENT")
    private String comment;

   }
