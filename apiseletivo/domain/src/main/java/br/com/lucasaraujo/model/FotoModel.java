package br.com.lucasaraujo.model;

import br.com.lucasaraujo.service.Resource;

public class FotoModel {

    private Long pessoaId;

    private Resource foto;

    private String linkFoto;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Resource getFoto() {
        return foto;
    }

    public void setFoto(Resource foto) {
        this.foto = foto;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public FotoModel() {
    }

    public FotoModel(Long pessoaId, Resource foto) {
        this.pessoaId = pessoaId;
        this.foto = foto;
    }

    public FotoModel(Resource foto) {
        this.foto = foto;
    }

    public FotoModel(Long pessoaId, String linkFoto) {
        this.pessoaId = pessoaId;
        this.linkFoto = linkFoto;
    }
}
