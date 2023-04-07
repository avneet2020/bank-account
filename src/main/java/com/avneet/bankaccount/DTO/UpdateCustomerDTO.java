package com.avneet.bankaccount.DTO;

import com.avneet.bankaccount.Utility.Utility;
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

        this.firstName = Utility.capitalizeFirstLetter(firstName);
        this.lastName = Utility.capitalizeFirstLetter(lastName);
        this.email = Utility.capitalizeFirstLetter(email);
        this.balance = Utility.round(balance);
    }
}
