package com.ftn.sbnz.model.models.backwardModel;

import org.kie.api.definition.type.Position;

public class BackwardModel {
    @Position(0)
    private String ingredient;
    @Position(1)
    private String medicine;
    @Position(2)
    private BackwardType backwardType;

    public BackwardModel(String ingredient, String medicine, BackwardType backwardType) {
        this.ingredient = ingredient;
        this.medicine = medicine;
        this.backwardType = backwardType;
    }
    public BackwardModel(String ingredient, String medicine) {
        this.ingredient = ingredient;
        this.medicine = medicine;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public BackwardType getBackwardType() {
        return backwardType;
    }

    public void setBackwardType(BackwardType backwardType) {
        this.backwardType = backwardType;
    }
}
