package com.example.securitydemo.domian;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author
 * @version 1.0
 * @date 2020/6/3 0003 17:16
 * @description
 **/
@Entity
@Table(name = "t_role")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role implements Serializable {

    private static final long serialVersionUID = -7564906850576689759L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private String name;



}
