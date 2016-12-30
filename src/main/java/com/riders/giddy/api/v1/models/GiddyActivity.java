package com.riders.giddy.api.v1.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by rik on 12/30/16.
 */

@Entity
public class GiddyActivity {

    @Value("${cloud.aws.s3.public_url}")
    private static String S3Url;

    @Id
    public UUID id;

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
    private GiddyScore giddyScore;

    public GiddyActivity() {
    }

    public GiddyActivity(String name, String remarks, String userName, String fileName, GiddyScore giddyScore) {
        this.name = name;
        this.remarks = remarks;
        this.userName = userName;
        this.fileName = fileName;
        this.giddyScore = giddyScore;
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

    public GiddyScore getGiddyScore() {
        return giddyScore;
    }

    public void setGiddyScore(GiddyScore giddyScore) {
        this.giddyScore = giddyScore;
    }

    public String getS3Url() {
        return S3Url.concat(this.id.toString() + '-' + this.getFileName());
    }
}
