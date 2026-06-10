package com.app.controller;

import com.app.dto.ClientDTO;
import com.app.domain.Client;
import com.app.service.CreateClientService;
import com.app.service.SearchClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    private CreateClientService createClientService;
    private SearchClientService searchClientService;

    @Autowired
    public ClientController(CreateClientService createClientService, SearchClientService searchClientService) {
        this.createClientService = createClientService;
        this.searchClientService = searchClientService;
    }

    // Todos os métodos da classe CreateClientService

    @PostMapping
    @Operation(summary = "Salvando um cliente no banco")
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        return ResponseEntity.ok().body(createClientService.createClient(clientDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterando um cliente no banco")
    public ResponseEntity<Client> alterClient(@PathVariable(value = "id", required = true) Long id, @RequestBody @Valid ClientDTO clientDTO) {
        return ResponseEntity.ok().body(createClientService.alterClient(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletando um cliente no banco")
    public ResponseEntity<Client> deleteClient(@PathVariable(value = "id", required = true) Long id) {
        createClientService.deleteClient(id);

        return ResponseEntity.ok().build();
    }

    // Todos os métodos da classe searchClientService

    @GetMapping
    @Operation(summary = "Pagina de clientes")
    public ResponseEntity<Page<Client>> searchPageClient(Pageable pageable) {
        return ResponseEntity.ok(searchClientService.searchPageClient(pageable));
    }

    @GetMapping("/clientIsRegistered/{id}")
    @Operation(summary = "Verifica se o cliente esta registrado")
    public ResponseEntity<Client> clientIsRegistered(@PathVariable(value = "id", required = true) Long id) {
        searchClientService.clientIsRegistered(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente atravez do ID")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(searchClientService.getClientById(id));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Busca um cliente atravez do email")
    public ResponseEntity<Client> getClientByEmail(@PathVariable(value = "email", required = true) String email) {
        return ResponseEntity.ok(searchClientService.getClientByEmail(email));
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Busca um cliente atravez do cpf")
    public ResponseEntity<Client> findClientByCpf(@PathVariable(value = "cpf", required = true) String cpf) {
        return ResponseEntity.ok(searchClientService.findClientByCpf(cpf));
    }

    @GetMapping("/AllClients")
    @Operation(summary = "Lista todos os clientes do banco")
    public ResponseEntity<List<Client>> getListAllClient() {
        return ResponseEntity.ok(searchClientService.getListAllClient());
    }
}
