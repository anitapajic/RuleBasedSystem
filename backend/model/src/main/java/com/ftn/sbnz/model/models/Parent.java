package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Position;

import java.io.Serializable;

public class Parent implements Serializable {
    @Position(1)
    private String childName;
    @Position(0)
    private String parentName;

    public Parent(String parentName, String childName) {
        this.childName = childName;
        this.parentName = parentName;
    }

    public Parent() {
        super();
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
