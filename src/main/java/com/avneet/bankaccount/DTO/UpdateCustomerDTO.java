package com.avneet.bankaccount.DTO;

import com.avneet.bankaccount.Utility.RoundUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCustomerDTO(
        @JsonProperty(value = "firstName") String firstName,
        @JsonProperty(value = "lastName") String lastName,
        @JsonProperty(value = "balance") double balance,
        @JsonProperty(value = "email") String email) {
    public UpdateCustomerDTO(
            @JsonProperty(value = "firstName") String firstName,
            @JsonProperty(value = "lastName") String lastName,
            @JsonProperty(value = "balance") double balance,
            @JsonProperty(value = "email") String email) {

        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.balance = RoundUtil.round(balance);
        this.email = email.toLowerCase();
    }
}
