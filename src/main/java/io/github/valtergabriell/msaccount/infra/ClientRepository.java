package io.github.valtergabriell.msaccount.infra;

import io.github.valtergabriell.msaccount.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
