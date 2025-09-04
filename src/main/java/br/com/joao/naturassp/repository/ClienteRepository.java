package br.com.joao.naturassp.repository;

import br.com.joao.naturassp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByEmailOrTelefone(String email, String telefone);

    public Boolean existsByEmail(String email);

    public Boolean existsByCpf(String cpf);

}
