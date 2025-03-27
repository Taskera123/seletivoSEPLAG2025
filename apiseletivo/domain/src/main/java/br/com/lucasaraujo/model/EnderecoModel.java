package br.com.lucasaraujo.model;

public class EnderecoModel {

    private Long enderecoId;

    private String enderecoTipoLogradouro;

    private String enderecoLogradouro;

    private Integer enderecoNumero;

    private String enderecoBairro;

    private CidadeModel cidade;

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

    public CidadeModel getCidade() {
        return cidade;
    }

    public void setCidade(CidadeModel cidade) {
        this.cidade = cidade;
    }

    public EnderecoModel() {
    }

    public EnderecoModel(Long enderecoId, String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero, String enderecoBairro, CidadeModel cidade) {
        this.enderecoId = enderecoId;
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
        this.cidade = cidade;
    }

    public EnderecoModel(String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero, String enderecoBairro, CidadeModel cidade) {
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
        this.cidade = cidade;
    }

    public EnderecoModel(Long enderecoId, String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero, String enderecoBairro) {
        this.enderecoId = enderecoId;
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
    }

    public EnderecoModel(String enderecoTipoLogradouro, String enderecoLogradouro, Integer enderecoNumero, String enderecoBairro) {
        this.enderecoTipoLogradouro = enderecoTipoLogradouro;
        this.enderecoLogradouro = enderecoLogradouro;
        this.enderecoNumero = enderecoNumero;
        this.enderecoBairro = enderecoBairro;
    }
}
