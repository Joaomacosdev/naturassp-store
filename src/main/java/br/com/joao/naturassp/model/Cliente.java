package br.com.joao.naturassp.model;

import br.com.joao.naturassp.dto.request.ClienteRequestDTO;
import br.com.joao.naturassp.dto.request.ClienteUpdateDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "nome_cliente", length = 100, nullable = false)
    private String nome;
    @Column(name = "email_cliente", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "telefone_cliente", length = 20, nullable = false, unique = true)
    private String telefone;

    @Column(name = "cpf_cliente", length = 15)
    private String cpf;

    @Column(name = "data_nasc")
    private LocalDate dataNasc;

    @Embedded
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;


    public Cliente() {
    }

    public Cliente(ClienteRequestDTO requestDTO) {
        this.nome = requestDTO.nome();
        this.email = requestDTO.email();
        this.telefone = requestDTO.telefone();
        this.cpf = requestDTO.cpf();
        this.endereco = new Endereco(requestDTO.endereco());
    }

    public void atualizar(ClienteUpdateDTO updateDTO) {
        if (nome != null){
            this.nome = updateDTO.nome();
        }

        if (email != null){
            this.email = updateDTO.email();
        }

        if (telefone != null){
            this.telefone = updateDTO.telefone();
        }

        if (cpf != null){
            this.cpf = updateDTO.cpf();
        }

        if (nome != null){
            this.nome = updateDTO.nome();
        }

        if (endereco != null){
            this.endereco.atualizarEndereco(updateDTO.endereco());
        }
    }

    public Long getId() {
        return idCliente;
    }

    public Cliente setId(Long id) {
        this.idCliente = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public Cliente setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
        return this;
    }

    public Cliente setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Cliente setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public Cliente setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cliente cliente = (Cliente) object;
        return Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCliente);
    }


}
