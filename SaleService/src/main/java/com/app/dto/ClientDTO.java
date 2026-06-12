package com.app.dto;

import com.app.domain.Client.StatusClient;

public record ClientDTO(Long id, Long code, String name, String cpf, String email, StatusClient status) {}
