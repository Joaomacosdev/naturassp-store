package br.com.joao.naturassp.repository;

import br.com.joao.naturassp.dto.response.ClienteResponseDTO;
import br.com.joao.naturassp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByEmailOrTelefone(String email, String telefone);

    public Boolean existsByEmail(String email);

    public Boolean existsByCpf(String cpf);

   public Cliente findByCpf(String cpf);


}
