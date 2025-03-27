package br.com.lucasaraujo.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "endereco_seq")
    @SequenceGenerator( name = "endereco_seq", sequenceName = "endereco_end_id_seq", allocationSize = 1)
    @Column(name = "end_id")
    private Long enderecoId;

    @Column(name = "end_tipo_logradouro", length = 50, nullable = false)
    private String enderecoTipoLogradouro;

    @Column(name = "end_logradouro", length = 200, nullable = false)
    private String enderecoLogradouro;

    @Column(name = "end_numero", length = 200, nullable = false)
    private Integer enderecoNumero;

    @Column(name = "end_bairro", length = 100, nullable = false)
    private String enderecoBairro;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private CidadeEntity cidade;

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getEnderecoTipoLogradouro() {
        return enderecoTipoLogradouro;
    }

    public void setEnderecoTipoLogradouro(String enderecoTipoLogradouro) {
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
    }

    public String getEnderecoLogradouro() {
        return enderecoLogradouro;
    }

    public void setEnderecoLogradouro(String enderecoLogradouro) {
        this.enderecoLogradouro = enderecoLogradouro;
    }

    public Integer getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(Integer enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public CidadeEntity getCidade() {
        return cidade;
    }

    public void setCidade(CidadeEntity cidade) {
        this.cidade = cidade;
    }

    public EnderecoEntity() {
    }

    public EnderecoEntity(Long enderecoId, String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero, String enderecoBairro, CidadeEntity cidade) {
        this.enderecoId = enderecoId;
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
        this.cidade = cidade;
    }

    public EnderecoEntity(String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero, String enderecoBairro, CidadeEntity cidade) {
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
        this.cidade = cidade;
    }

    public EnderecoEntity(String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero,
                          String enderecoBairro/*, CidadeEntity cidade*/) {
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
       // this.cidade = cidade;
    }
}
