package com.avneet.bankaccount.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
public record LoginDTO(
        @JsonProperty(value = "username") String username,
        @JsonProperty(value = "password") String password
){}
