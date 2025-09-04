package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.Cliente;
import br.com.joao.naturassp.model.Endereco;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco


) {

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCpf(), cliente.getEndereco());
    }
}
