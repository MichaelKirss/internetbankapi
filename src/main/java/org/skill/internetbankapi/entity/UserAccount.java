package org.skill.internetbankapi.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "USER_ACCOUNT", schema = "public")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "BALANCE")
    private float balance;
}

