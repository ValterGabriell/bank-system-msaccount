package io.github.valtergabriell.msaccount.infra;

import io.github.valtergabriell.msaccount.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findClientBycpf(String cpf);
}
