package br.com.lucasaraujo.data.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotacao")
public class LotacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long lotacaoId;

    @Column(name = "lot_data_lotacao", nullable = false)
    private LocalDate lotacaoDataLotacao;

    @Column(name = "lot_data_remocao", nullable = false)
    private LocalDate lotacaoDataRemocao;

    @Column(name= "lot_portaria", nullable = false)
    private String lotacaoPortaria;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private UnidadeEntity unidade;

    public Long getLotacaoId() {
        return lotacaoId;
    }

    public void setLotacaoId(Long lotacaoId) {
        this.lotacaoId = lotacaoId;
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public UnidadeEntity getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeEntity unidade) {
        this.unidade = unidade;
    }

    public LocalDate getLotacaoDataLotacao() {
        return lotacaoDataLotacao;
    }

    public void setLotacaoDataLotacao(LocalDate lotacaoDataLotacao) {
        this.lotacaoDataLotacao = lotacaoDataLotacao;
    }

    public LocalDate getLotacaoDataRemocao() {
        return lotacaoDataRemocao;
    }

    public void setLotacaoDataRemocao(LocalDate lotacaoDataRemocao) {
        this.lotacaoDataRemocao = lotacaoDataRemocao;
    }

    public String getLotacaoPortaria() {
        return lotacaoPortaria;
    }

    public void setLotacaoPortaria(String lotacaoPortaria) {
        this.lotacaoPortaria = lotacaoPortaria;
    }

    public LotacaoEntity() {
    }

    public LotacaoEntity(Long lotacaoId, LocalDate lotacaoDataLotacao, LocalDate lotacaoDataRemocao,
                         String lotacaoPortaria,PessoaEntity pessoa, UnidadeEntity unidade) {
        this.lotacaoId = lotacaoId;
        this.lotacaoDataLotacao = lotacaoDataLotacao;
        this.lotacaoDataRemocao = lotacaoDataRemocao;
        this.lotacaoPortaria = lotacaoPortaria;
        this.pessoa = pessoa;
        this.unidade = unidade;
    }

    public LotacaoEntity(LocalDate lotacaoDataLotacao, LocalDate lotacaoDataRemocao,
                         String lotacaoPortaria,PessoaEntity pessoa, UnidadeEntity unidade) {
        this.lotacaoDataLotacao = lotacaoDataLotacao;
        this.lotacaoDataRemocao = lotacaoDataRemocao;
        this.lotacaoPortaria = lotacaoPortaria;
        this.pessoa = pessoa;
        this.unidade = unidade;
    }
}
