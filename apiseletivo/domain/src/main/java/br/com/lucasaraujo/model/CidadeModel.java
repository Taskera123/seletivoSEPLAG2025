package br.com.lucasaraujo.model;

public class CidadeModel {

    private Long cidadeId;

    private String cidadeNome;

    private String cidadeUf;

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public String getCidadeUf() {
        return cidadeUf;
    }

    public void setCidadeUf(String cidadeUf) {
        this.cidadeUf = cidadeUf;
    }

    public CidadeModel() {
    }

    public CidadeModel(Long cidadeId, String cidadeNome, String cidadeUf) {
        this.cidadeId = cidadeId;
        this.cidadeNome = cidadeNome;
        this.cidadeUf = cidadeUf;
    }

    public CidadeModel(String cidadeNome, String cidadeUf) {
        this.cidadeNome = cidadeNome;
        this.cidadeUf = cidadeUf;
    }
}
