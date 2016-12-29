package com.riders.giddy.api.v1.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by rik on 12/30/16.
 */

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

    @NotBlank(message = "fileName must not be blank!")
    @Column
    private String fileName;

    @OneToOne(cascade=CascadeType.ALL)
    private GiddyScore giddyScore;

    // Used only for request body, not saved into db
    @Transient
    @NotNull(message = "file must not be null!")
    private MultipartFile file;

    public GiddyActivity() {
    }

    public GiddyActivity(String name, String remarks, String userName, String fileName, GiddyScore giddyScore,
                         MultipartFile file) {
        this.name = name;
        this.remarks = remarks;
        this.userName = userName;
        this.fileName = fileName;
        this.giddyScore = giddyScore;
        this.file = file;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
