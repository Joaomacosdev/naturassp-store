package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.Cliente;
import br.com.joao.naturassp.model.Endereco;

import java.time.LocalDate;

public record ClienteResponseDTO(
        Long idCliente,
        String nome,
        String email,
        String telefone,
        String cpf,
        LocalDate dataNasc,

        Endereco endereco


) {

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCpf(), cliente.getDataNasc(),cliente.getEndereco());
    }
}
