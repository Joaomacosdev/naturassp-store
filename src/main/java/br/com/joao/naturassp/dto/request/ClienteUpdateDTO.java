package br.com.joao.naturassp.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.*;


public record ClienteUpdateDTO(

        @NotNull
        Long idCliente,

        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @Email(message = "E-mail deve ser válido")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
        String email,

        @Pattern(
                regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}",
                message = "Telefone deve estar no formato (99) 99999-9999 ou (99) 9999-9999"
        )
        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
        String telefone,
        String cpf,
        String nomeUsuario,
        String senha,
        @Valid
        EnderecoRequestDTO endereco




) {
}
