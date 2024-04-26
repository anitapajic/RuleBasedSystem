package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;

import java.io.Serializable;

public class Man implements Serializable {
    @Position(0)
    private String name;

    public Man(String name) {
        this.name = name;
    }

    public Man() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
