package br.com.cpcl.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ofertas")
public class Oferta {
    
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comercio_id")
    private Comercio comercio;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private BigDecimal preco;

    
    public Oferta() {
    }


    public Oferta(Long id, Comercio comercio, Produto produto, BigDecimal preco) {
        this.id = id;
        this.comercio = comercio;
        this.produto = produto;
        this.preco = preco;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Comercio getComercio() {
        return comercio;
    }


    public void setComercioId(Comercio comercio) {
        this.comercio = comercio;
    }


    public Produto getProdutoId() {
        return produto;
    }


    public void setProdutoId(Produto produto) {
        this.produto = produto;
    }


    public BigDecimal getPreco() {
        return preco;
    }


    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    
}
