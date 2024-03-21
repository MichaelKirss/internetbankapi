package org.skill.internetbankapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "TYPE_OPERATIONS", schema="public")
public class TypeOperations {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "API_METHOD")
        private String apiMethod;
        @Column(name = "METHOD_NAME")
        private String methodName;


}
