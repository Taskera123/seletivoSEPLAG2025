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

    public ServidorEfetivoModel buscarPorId(Long pesId){
        return servidorEfetivoPort.buscarPorId(pesId);
    }

   public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery){
        return servidorEfetivoPort.listaServidoresEfetivosPaginado(pageQuery);
    }

    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.criar(servidorEfetivoModel);
    }

    public ServidorEfetivoModel atualizar(Long pesId,ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.atualizar(pesId,servidorEfetivoModel);
    }

    public void excluir(Long pesId){
         servidorEfetivoPort.excluir(pesId);
    }

    public PageResponse<ServidorEfetivoModel> buscarServidoreLotadosUnidade(Long unidId, PageQuery pageQuery) {
        return servidorEfetivoPort.buscarServidoresLotadosUnidade(unidId,pageQuery);
    }
}
