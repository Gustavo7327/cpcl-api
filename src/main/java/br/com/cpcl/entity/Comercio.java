package br.com.cpcl.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comercios")
public class Comercio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeFantasia;

    private String cnpj;

    private String endereco;

    private String telefone;

    private String horarioFuncionamento;

    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    private ComercioStatus status;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;


    public Comercio(Long id, String nomeFantasia, String cnpj, String endereco, String telefone,
            String horarioFuncionamento, LocalDate dataCadastro, ComercioStatus status, Usuario usuarioId) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dataCadastro = dataCadastro;
        this.status = status;
        this.usuarioId = usuarioId;
    }


    public Comercio() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate localDate) {
        this.dataCadastro = localDate;
    }

    public ComercioStatus getStatus() {
        return status;
    }

    public void setStatus(ComercioStatus status) {
        this.status = status;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }


    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }
    
 
}
