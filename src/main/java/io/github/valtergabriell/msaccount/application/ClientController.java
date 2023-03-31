package io.github.valtergabriell.msaccount.application;


import io.github.valtergabriell.msaccount.application.dto.CommonResponse;
import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<CommonResponse<CreateClientResponse>> createClient(@RequestBody CreateClientRequest request) {
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(request.getCpf()).toUri();
        CommonResponse<CreateClientResponse> client = clientService.createClient(request, headerLocation);
        return ResponseEntity.ok(client);
    }

}
