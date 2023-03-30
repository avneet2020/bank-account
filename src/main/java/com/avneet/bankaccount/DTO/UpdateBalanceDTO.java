package com.avneet.bankaccount.DTO;
import com.avneet.bankaccount.Utility.RoundUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateBalanceDTO(@JsonProperty(value = "balance") double balance) {

    public UpdateBalanceDTO(@JsonProperty(value = "balance") double balance) {
        this.balance = RoundUtil.round(balance);
    }
}