package br.com.joao.naturassp.model;

import br.com.joao.naturassp.dto.request.EnderecoRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    @Column(name = "cep_cliente", length = 10, nullable = false)
    private String cepCliente;
    @Column(name = "logradouro", length = 100)
    private String logradouro;
    @Column(name = "numero", length = 10)
    private String numero;
    @Column(name = "complemento", length = 50)
    private String complemento;
    @Column(name = "bairro", length = 100)
    private String bairro;
    @Column(name = "cidade", length = 100)
    private String cidade;
    @Column(name = "estado", length = 2)
    private String estado;

    public Endereco() {
    }

    public Endereco(EnderecoRequestDTO endereco) {
        this.cepCliente = endereco.cepCliente();
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();

    }

    public String getCepCliente() {
        return cepCliente;
    }

    public Endereco setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Endereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public Endereco setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Endereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Endereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Endereco setEstado(String estado) {
        this.estado = estado;
        return this;
    }
}
