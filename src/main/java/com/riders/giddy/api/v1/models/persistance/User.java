package com.riders.giddy.api.v1.models.persistance;

import javax.persistence.*;

/**
 * Created by alex_ on 11/6/2016.
 */
@Entity
@Table(name = "giddy_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
}
