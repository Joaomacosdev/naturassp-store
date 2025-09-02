package br.com.joao.naturassp.dto.request;

import br.com.joao.naturassp.model.Categoria;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record ProdutoUpdateDTO(

        @NotNull(message = "Id obrigatório")
        Long id,

        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,
        @Size(max = 500, message = "O detalhe deve ter no máximo 500 caracteres")
        String detalhe,
        @Size(max = 255, message = "O link da foto deve ter no máximo 255 caracteres")
        String linkFoto,
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        BigDecimal preco,
        Categoria categoria,
        Boolean disponivel
) {
}
