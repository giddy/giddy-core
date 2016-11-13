package com.riders.giddy.api.v1.models.persistance;

import javax.persistence.*;

/**
 * Created by alex_ on 11/6/2016.
 */
@Entity
@Table(name = "giddy_edge")
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Descriptor descriptor;
    @Column
    private Integer edgeId;
    @Column
    private Integer baseNodeId;
    @Column
    private Integer adjacentNodeId;
    @Column
    private float latitude;
    @Column
    private float longitude;
    @Column
    private float peer;
}