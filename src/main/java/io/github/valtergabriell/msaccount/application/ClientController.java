package io.github.valtergabriell.msaccount.application;


import io.github.valtergabriell.msaccount.application.validator.CPFValidator;
import io.github.valtergabriell.msaccount.entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
