package br.com.lucastasca.stories.endereco;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.EnderecoModel;
import br.com.lucastasca.ports.endereco.EnderecoPort;

public class EnderecoUseStory {

    private final EnderecoPort enderecoPort;

    public EnderecoUseStory(EnderecoPort enderecoPort) {
        this.enderecoPort = enderecoPort;
    }

    public EnderecoModel buscarPorId(Long endId){
        return enderecoPort.buscarPorId(endId);
    }

    public PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery){
        return enderecoPort.listaEnderecosPaginado(pageQuery);
    }

    public EnderecoModel criar(EnderecoModel EnderecoModel){
        System.out.println("entrou na useStory");
        return enderecoPort.criar(EnderecoModel);
    }

    public EnderecoModel atualizar(Long endId,EnderecoModel EnderecoModel){
        return enderecoPort.atualizar(endId,EnderecoModel);
    }
}
