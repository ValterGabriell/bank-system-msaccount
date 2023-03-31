package io.github.valtergabriell.msaccount.application;


import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {


    private final ClientService clientService;

    @GetMapping("ok")
    public String checkIfServiceIsUp() {
        return "ok";
    }


    @PostMapping
    public ResponseEntity<CreateClientResponse> createClient(@RequestBody CreateClientRequest request) {
        CreateClientResponse clientResponse = clientService.createClient(request);
        return ResponseEntity.ok(clientResponse);
    }

}
