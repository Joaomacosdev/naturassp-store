package br.com.joao.naturassp.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.*;


public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail deve ser válido")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
                regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}",
                message = "Telefone deve estar no formato (99) 99999-9999 ou (99) 9999-9999"
        )
        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
        String telefone,
        @NotBlank(message = "O CPF é obrigatório")
        String cpf,
        @NotBlank
        String nomeUsuario,
        @NotBlank
        String senha,
        @NotNull
        @Valid
        EnderecoRequestDTO endereco




) {
}
