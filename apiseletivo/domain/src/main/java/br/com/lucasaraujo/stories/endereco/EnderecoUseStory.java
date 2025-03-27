package br.com.lucasaraujo.stories.endereco;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.ports.endereco.EnderecoPort;

public class EnderecoUseStory {

    private final EnderecoPort enderecoPort;

    public EnderecoUseStory(EnderecoPort enderecoPort) {
        this.enderecoPort = enderecoPort;
    }

    public EnderecoModel buscarPorId(Long enderecoId){
        return enderecoPort.buscarPorId(enderecoId);
    }

    public PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery){
        return enderecoPort.listaEnderecosPaginado(pageQuery);
    }

    public EnderecoModel criar(EnderecoModel EnderecoModel){
        return enderecoPort.criar(EnderecoModel);
    }

    public EnderecoModel atualizar(Long enderecoId,EnderecoModel EnderecoModel){
        return enderecoPort.atualizar(enderecoId,EnderecoModel);
    }

    public void excluir(Long enderecoId){
        enderecoPort.excluir(enderecoId);
    }

    public PageResponse<EnderecoModel> buscarEnderecoFuncional(String nome, PageQuery pageQuery) {
        return enderecoPort.buscarEnderecoFuncional(nome,pageQuery);
    }
}
