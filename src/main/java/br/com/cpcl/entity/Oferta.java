package br.com.cpcl.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ofertas")
public class Oferta {
    
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "comercio_id")
    private Comercio comercioId;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produtoId;

    private BigDecimal preco;

    
    public Oferta() {
    }


    public Oferta(Long id, Comercio comercioId, Produto produtoId, BigDecimal preco) {
        this.id = id;
        this.comercioId = comercioId;
        this.produtoId = produtoId;
        this.preco = preco;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Comercio getComercioId() {
        return comercioId;
    }


    public void setComercioId(Comercio comercioId) {
        this.comercioId = comercioId;
    }


    public Produto getProdutoId() {
        return produtoId;
    }


    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }


    public BigDecimal getPreco() {
        return preco;
    }


    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    
}
