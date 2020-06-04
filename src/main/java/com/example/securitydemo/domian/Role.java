package com.example.securitydemo.domian;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author
 * @version 1.0
 * @date 2020/6/3 0003 17:16
 * @description
 **/
@Entity
@Table(name = "t_role")
@Data
public class Role {

    @Id
    @Column(name = "id")
    private Long id;

    @Column
    private String name;



}
