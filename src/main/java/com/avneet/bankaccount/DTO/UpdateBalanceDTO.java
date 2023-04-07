package com.avneet.bankaccount.DTO;
import com.avneet.bankaccount.Utility.Utility;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateBalanceDTO(@JsonProperty(value = "balance") double balance) {

    public UpdateBalanceDTO(@JsonProperty(value = "balance") double balance) {
        this.balance = Utility.round(balance);
    }
}