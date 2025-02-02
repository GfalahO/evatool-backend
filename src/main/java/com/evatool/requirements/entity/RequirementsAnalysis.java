package com.evatool.requirements.entity;

import com.google.gson.Gson;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "REQ_Analysis")
public class RequirementsAnalysis {

    @Id
    private UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static  RequirementsAnalysis fromJson(String json){
        return  new Gson().fromJson(json, RequirementsAnalysis.class);
    }
}
