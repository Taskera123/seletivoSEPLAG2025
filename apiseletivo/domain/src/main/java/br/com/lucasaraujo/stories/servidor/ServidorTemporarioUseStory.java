package br.com.lucasaraujo.stories.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.ServidorTemporarioModel;
import br.com.lucasaraujo.ports.servidor.ServidorTemporarioPort;

public class ServidorTemporarioUseStory {

    private final ServidorTemporarioPort servidorTemporarioPort;

    public ServidorTemporarioUseStory(ServidorTemporarioPort servidorTemporarioPort) {
        this.servidorTemporarioPort = servidorTemporarioPort;
    }

    public ServidorTemporarioModel buscarPorId(Long pesId){
        return servidorTemporarioPort.buscarPorId(pesId);
    }

   public PageResponse<ServidorTemporarioModel> listaServidoresTemporariosPaginado(PageQuery pageQuery){
        return servidorTemporarioPort.listaServidoresTemporariosPaginado(pageQuery);
    }

    public ServidorTemporarioModel criar(ServidorTemporarioModel servidorTemporarioModel){
        return servidorTemporarioPort.criar(servidorTemporarioModel);
    }

    public ServidorTemporarioModel atualizar(Long pesId,ServidorTemporarioModel servidorTemporarioModel){
        return servidorTemporarioPort.atualizar(pesId,servidorTemporarioModel);
    }

    public void excluir(Long pesId){
         servidorTemporarioPort.excluir(pesId);
    }
}
