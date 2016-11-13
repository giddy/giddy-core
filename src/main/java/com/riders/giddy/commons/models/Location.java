package com.riders.giddy.commons.models;

import org.springframework.data.annotation.Id;

class Location {
    @Id
    private String id;
    private double[] position;
}
