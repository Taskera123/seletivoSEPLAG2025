package br.com.lucastasca.stories.servidor;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.ServidorEfetivoModel;
import br.com.lucastasca.ports.servidor.ServidorEfetivoPort;

public class ServidorEfetivoUseStory {

    private final ServidorEfetivoPort servidorEfetivoPort;

    public ServidorEfetivoUseStory(ServidorEfetivoPort servidorEfetivoPort) {
        this.servidorEfetivoPort = servidorEfetivoPort;
    }

    public ServidorEfetivoModel buscarPorId(Long cidId){
        return servidorEfetivoPort.buscarPorId(cidId);
    }

   public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery){
        return servidorEfetivoPort.listaServidoresEfetivosPaginado(pageQuery);
    }

    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.criar(servidorEfetivoModel);
    }

    public ServidorEfetivoModel atualizar(Long cidId,ServidorEfetivoModel servidorEfetivoModel){
        return servidorEfetivoPort.atualizar(cidId,servidorEfetivoModel);
    }

    public void excluir(Long cidId){
         servidorEfetivoPort.excluir(cidId);
    }
}
