package com.avneet.bankaccount.DTO;
import com.avneet.bankaccount.Utility.Round;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateBalanceDTO(@JsonProperty(value = "balance") double balance) {

    public UpdateBalanceDTO(@JsonProperty(value = "balance") double balance) {
        this.balance = Round.round(balance);
    }
}