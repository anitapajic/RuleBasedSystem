package com.ftn.sbnz.model.models;

import java.io.Serializable;

public class Account implements Serializable {

    private Long customerId;

    private Double accountState;


    public Account() {
        super();
    }

    public Account(Long customerId, Double accountState) {
        this.customerId = customerId;
        this.accountState = accountState;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAccountState() {
        return accountState;
    }

    public void setAccountState(Double accountState) {
        this.accountState = accountState;
    }
}
