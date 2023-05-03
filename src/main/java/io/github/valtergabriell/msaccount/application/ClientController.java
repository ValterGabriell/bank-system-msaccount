package io.github.valtergabriell.msaccount.application;


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


    @GetMapping(params = "id")
    public ResponseEntity<Client> getAccountData(@RequestParam("id") String id) {
        var account = clientService.getAccountByIdentifier(id);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity deleteAccountData(@RequestParam("id") String id) {
        clientService.deleteAccountByIdentifier(id);
        return ResponseEntity.noContent().build();
    }

}
