package br.univille.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carros {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String modelo;
    private String marca;
    private int ano;
    private String cor;
    private int quilometragem;
    private Boolean ativo = true;
    private int valor;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getmodelo() {
        return modelo;
    }
    public void setmodelo(String modelo) {
        this.modelo = modelo;
    }
    public String getmarca() {
        return marca;
    }
    public void setmarca(String marca) {
        this.marca = marca;
    }
    public int getano() {
        return ano;
    }
    public void setano(int ano) {
        this.ano = ano;
    }
    public int getquilometragem() {
        return quilometragem;
    }
    public void setquilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }
    public String getcor() {
        return cor;
    }
    public void setcor(String cor) {
        this.cor = cor;
    }

    public int getvalor() {
        return valor;
    }
    public void setvalor(int valor) {
        this.valor = valor;        
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
}