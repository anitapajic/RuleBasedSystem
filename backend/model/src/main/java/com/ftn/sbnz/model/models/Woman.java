package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;

import java.io.Serializable;

public class Woman implements Serializable {

    @Position(0)
    private String name;

    public Woman(String name) {
        this.name = name;
    }

    public Woman() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
