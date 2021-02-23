package com.evatool.requirements.entity;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "REQ_Dimension")
public class RequirementDimension {

    @Id
    private final UUID id = UUID.randomUUID();

    private String title;

    public RequirementDimension(String title) {
        this.title = title;
    }

    public RequirementDimension() {

    }

    public static  RequirementDimension fromJson(String json){
        return  new Gson().fromJson(json, RequirementDimension.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = title;
    }
}
