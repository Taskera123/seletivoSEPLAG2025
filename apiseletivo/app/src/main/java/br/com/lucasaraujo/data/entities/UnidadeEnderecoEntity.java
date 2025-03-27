package br.com.lucasaraujo.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEnderecoEntity {

    @EmbeddedId
    private UnidadeEnderecoId unidEnderecoId;

    @ManyToOne
    @MapsId("unidade")
    @JoinColumn(name = "unid_id")
    private UnidadeEntity unidade;


    @ManyToOne
    @MapsId("endereco")
    @JoinColumn(name = "end_id")
    private EnderecoEntity endereco;

    public UnidadeEnderecoId getUnidEnderecoId() {
        return unidEnderecoId;
    }

    public void setUnidEnderecoId(UnidadeEnderecoId unidEnderecoId) {
        this.unidEnderecoId = unidEnderecoId;
    }

    public UnidadeEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeEntity unidade) {
        this.unidade = unidade;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    public UnidadeEnderecoEntity() {
    }

    public UnidadeEnderecoEntity(UnidadeEnderecoId unidEnderecoId) {
        this.unidEnderecoId = unidEnderecoId;
    }

    public UnidadeEnderecoEntity(UnidadeEntity unidade, EnderecoEntity endereco) {
        this.unidade = unidade;
        this.endereco = endereco;
    }

    public UnidadeEnderecoEntity(UnidadeEnderecoId unidEnderecoId, UnidadeEntity unidade, EnderecoEntity endereco) {
        this.unidEnderecoId = unidEnderecoId;
        this.unidade = unidade;
        this.endereco = endereco;
    }
}
