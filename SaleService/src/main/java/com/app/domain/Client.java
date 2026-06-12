package com.app.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;

    private Long code;

    private String name;

    private String cpf;

    private String email;

    private StatusClient status;

    public enum StatusClient {
        ACTIVE,
        INACTIVE
    }
}