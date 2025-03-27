package br.com.lucasaraujo.model;

import java.time.LocalDate;

public class LotacaoModel {

    private Long lotacaoId;

    private LocalDate lotacaoDataLotacao;

    private LocalDate lotacaoDataRemocao;

    private String lotacaoPortaria;

    private Long pessoaId;

    private Long unidadeId;

    private PessoaModel pessoaModel;

    private UnidadeModel unidadeModel;

    public Long getLotacaoId() {
        return lotacaoId;
    }

    public void setLotacaoId(Long lotacaoId) {
        this.lotacaoId = lotacaoId;
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

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public LotacaoModel() {
    }

    public PessoaModel getPessoaModel() {
        return pessoaModel;
    }

    public void setPessoaModel(PessoaModel pessoaModel) {
        this.pessoaModel = pessoaModel;
    }

    public UnidadeModel getUnidadeModel() {
        return unidadeModel;
    }

    public void setUnidadeModel(UnidadeModel unidadeModel) {
        this.unidadeModel = unidadeModel;
    }

    public LotacaoModel(Long lotacaoId, LocalDate lotacaoDataLotacao, LocalDate lotacaoDataRemocao,
                        String lotacaoPortaria, Long pessoaId, Long unidadeId) {
        this.lotacaoId = lotacaoId;
        this.lotacaoDataLotacao = lotacaoDataLotacao;
        this.lotacaoDataRemocao = lotacaoDataRemocao;
        this.lotacaoPortaria = lotacaoPortaria;
        this.pessoaId = pessoaId;
        this.unidadeId = unidadeId;
    }

    public LotacaoModel(LocalDate lotacaoDataLotacao, LocalDate lotacaoDataRemocao,
                        String lotacaoPortaria, Long pessoaId, Long unidadeId) {
        this.lotacaoDataLotacao = lotacaoDataLotacao;
        this.lotacaoDataRemocao = lotacaoDataRemocao;
        this.lotacaoPortaria = lotacaoPortaria;
        this.pessoaId = pessoaId;
        this.unidadeId = unidadeId;
    }

    public LotacaoModel(Long lotacaoId, LocalDate lotacaoDataLotacao, LocalDate lotacaoDataRemocao,
                        String lotacaoPortaria,PessoaModel pessoaModel, UnidadeModel unidadeModel) {
        this.lotacaoId = lotacaoId;
        this.lotacaoDataLotacao = lotacaoDataLotacao;
        this.lotacaoDataRemocao = lotacaoDataRemocao;
        this.lotacaoPortaria = lotacaoPortaria;
        this.pessoaModel = pessoaModel;
        this.unidadeModel = unidadeModel;
    }

    public LotacaoModel(LocalDate lotacaoDataLotacao, LocalDate lotacaoDataRemocao,
                        String lotacaoPortaria,PessoaModel pessoaModel, UnidadeModel unidadeModel) {

        this.lotacaoDataLotacao = lotacaoDataLotacao;
        this.lotacaoDataRemocao = lotacaoDataRemocao;
        this.lotacaoPortaria = lotacaoPortaria;
        this.pessoaModel = pessoaModel;
        this.unidadeModel = unidadeModel;
    }

}
