package com.riders.giddy.api.v1.models;

import com.riders.giddy.api.v1.models.dao.GiddyEdgeScore;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class GiddyActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotBlank(message = "name must not be blank!")
    @Column
    private String name;

    @NotBlank(message = "remarks must not be blank!")
    @Column
    private String remarks;

    @NotBlank(message = "userName must not be blank!")
    @Column
    private String userName;

    @Column
    private String fileName;

    @OneToOne(cascade=CascadeType.ALL)
    private GiddyEdgeScore giddyScore;

    @Value("${cloud.aws.s3.public_url}")
    @Transient
    private static String S3Url;

    public GiddyActivity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public GiddyEdgeScore getGiddyScore() {
        return giddyScore;
    }

    public void setGiddyScore(GiddyEdgeScore giddyScore) {
        this.giddyScore = giddyScore;
    }

    public String getS3Url() {
        return S3Url.concat(this.id.toString() + '-' + this.getFileName());
    }
}
