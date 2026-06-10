package com.app.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    @Schema(description="Nome", minLength = 1, maxLength=50, nullable = false)
    private String name;

    @Size(min = 11, max = 11)
    @NotBlank(message = "CPF is required")
    @Schema(description="CPF", nullable = false)
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Size(min = 1, max = 50)
    @NotBlank(message = "Email is required")
    @Email(
            message = "Please provide a valid email address",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
    )
    @Column(name = "email", nullable = false, unique = true)
    @Schema(description="Email", minLength = 1, maxLength=50, nullable = false)
    private String email;
}
