package com.avneet.bankaccount.DTO;

import com.avneet.bankaccount.Utility.Utility;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SaveDTO(
         @JsonProperty(value = "username") String username,
         @JsonProperty(value = "password") String password,
         @JsonProperty(value = "firstName") String firstName,
         @JsonProperty(value = "lastName") String lastName,
         @JsonProperty(value = "email") String email
){
    public SaveDTO(@JsonProperty(value = "username") String username,
                   @JsonProperty(value = "password") String password,
                   @JsonProperty(value = "firstName") String firstName,
                   @JsonProperty(value = "lastName") String lastName,
                   @JsonProperty(value = "email") String email) {
        this.username = username;
        this.password = password;
        this.firstName = Utility.capitalizeFirstLetter(firstName);
        this.lastName = Utility.capitalizeFirstLetter(lastName);
        this.email = Utility.capitalizeFirstLetter(email);
    }
}
