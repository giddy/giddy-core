package com.riders.giddy.api.v1.models.persistance;

import javax.persistence.*;

/**
 * Created by alex_ on 11/6/2016.
 */
@Entity
@Table(name = "giddy_descriptor")
public class Descriptor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    Edge edge;
    @Column
    private float safe;
    @Column
    private float touristy;
    @Column
    private float recreational;
    @Column
    private float fast;
    @Column
    private float challenging;
    @Column
    private float crowded;
}
