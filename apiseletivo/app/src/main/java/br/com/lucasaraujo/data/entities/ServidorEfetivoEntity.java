package br.com.lucasaraujo.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivoEntity {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @Column(name = "se_matricula")
    private String servidorMatricula;


    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public String getServidorMatricula() {
        return servidorMatricula;
    }

    public void setServidorMatricula(String servidorMatricula) {
        this.servidorMatricula = servidorMatricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServidorEfetivoEntity() {
    }

    public ServidorEfetivoEntity(Long id, String servidorMatricula, PessoaEntity pessoa) {
        this.id = id;
        this.servidorMatricula = servidorMatricula;
        this.pessoa = pessoa;
    }
}
