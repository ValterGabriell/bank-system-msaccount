package io.github.valtergabriell.msaccount.application;


import io.github.valtergabriell.msaccount.application.dto.CommonResponse;
import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import io.github.valtergabriell.msaccount.entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("account")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<CommonResponse<CreateClientResponse>> createClient(@RequestBody CreateClientRequest request) {
        URI headerLocation = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:9090").query("cpf={cpf}").buildAndExpand(request.getCpf()).toUri();
        CommonResponse<CreateClientResponse> client = clientService.createClient(request, headerLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Client> getAccountData(@RequestParam("cpf") String cpf) {
        var account = clientService.getAccountByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @DeleteMapping(params = "cpf")
    public ResponseEntity deleteAccountData(@RequestParam("cpf") String cpf) {
        clientService.deleteAccountByCpf(cpf);
        return ResponseEntity.noContent().build();
    }

}
