package br.com.joao.naturassp.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record EnderecoRequestDTO(
        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP deve estar no formato 99999-999 ou 99999999")
        String cepCliente,
        @NotBlank(message = "O logradouro é obrigatório")
        @Size(max = 100, message = "Logradouro deve ter no máximo 100 caracteres")
        String logradouro,
        @NotBlank(message = "O número é obrigatório")
        @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
        String numero,
        @Size(max = 50, message = "Complemento deve ter no máximo 50 caracteres")
        String complemento,
        @NotBlank(message = "O bairro é obrigatório")
        @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
        String bairro,
        @NotBlank(message = "A cidade é obrigatória")
        @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
        String cidade,
        @NotBlank(message = "O estado é obrigatório")
        @Pattern(regexp = "^[A-Z]{2}$", message = "Estado deve ser a sigla de 2 letras (ex: SP, RJ)")
        String estado
) {
}
