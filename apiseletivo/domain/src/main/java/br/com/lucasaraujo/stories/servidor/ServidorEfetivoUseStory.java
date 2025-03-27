package br.com.lucasaraujo.stories.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.ServidorEfetivoModel;
import br.com.lucasaraujo.ports.servidor.ServidorEfetivoPort;

public class ServidorEfetivoUseStory {

    private final ServidorEfetivoPort servidorEfetivoPort;

    public ServidorEfetivoUseStory(ServidorEfetivoPort servidorEfetivoPort) {
        this.servidorEfetivoPort = servidorEfetivoPort;
    }

    public ServidorEfetivoModel buscarPorId(Long pessoaId){
        return servidorEfetivoPort.buscarPorId(pessoaId);
    }

   public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery){
        return servidorEfetivoPort.listaServidoresEfetivosPaginado(pageQuery);
    }

    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.criar(servidorEfetivoModel);
    }

    public ServidorEfetivoModel atualizar(Long pessoaId,ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.atualizar(pessoaId,servidorEfetivoModel);
    }

    public void excluir(Long pessoaId){
         servidorEfetivoPort.excluir(pessoaId);
    }

    public PageResponse<ServidorEfetivoModel> buscarServidoreLotadosUnidade(Long unidadeId, PageQuery pageQuery) {
        return servidorEfetivoPort.buscarServidoresLotadosUnidade(unidadeId,pageQuery);
    }
}
