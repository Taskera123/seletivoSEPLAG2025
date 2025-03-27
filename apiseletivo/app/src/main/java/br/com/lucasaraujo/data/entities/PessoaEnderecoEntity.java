package br.com.lucasaraujo.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEnderecoEntity {

    @EmbeddedId
    private PessoaEnderecoId pesEnderecoId;

    @ManyToOne
    @MapsId("pessoa")
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;


    @ManyToOne
    @MapsId("endereco")
    @JoinColumn(name = "end_id")
    private EnderecoEntity endereco;

    public PessoaEnderecoId getPesEnderecoId() {
        return pesEnderecoId;
    }

    public void setPesEnderecoId(PessoaEnderecoId pesEnderecoId) {
        this.pesEnderecoId = pesEnderecoId;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public PessoaEnderecoEntity() {
    }

    public PessoaEnderecoEntity(PessoaEnderecoId pesEnderecoId) {
        this.pesEnderecoId = pesEnderecoId;
    }

    public PessoaEnderecoEntity(PessoaEntity pessoa, EnderecoEntity endereco) {
        this.pessoa = pessoa;
        this.endereco = endereco;
    }

    public PessoaEnderecoEntity(PessoaEnderecoId pesEnderecoId, PessoaEntity pessoa, EnderecoEntity endereco) {
        this.pesEnderecoId = pesEnderecoId;
        this.pessoa = pessoa;
        this.endereco = endereco;
    }
}
