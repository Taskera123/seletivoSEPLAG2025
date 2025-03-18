package br.com.lucastasca.stories.unidade;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.UnidadeModel;
import br.com.lucastasca.ports.unidade.UnidadePort;

public class UnidadeUseStory {

    private final UnidadePort unidadePort;

    public UnidadeUseStory(UnidadePort unidadePort) {
        this.unidadePort = unidadePort;
    }

    public UnidadeModel buscarPorId(Long unidId){
        return unidadePort.buscarPorId(unidId);
    }

    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery){
        return unidadePort.listaUnidadesPaginado(pageQuery);
    }

    public UnidadeModel criar(UnidadeModel unidadeModel){
        return unidadePort.criar(unidadeModel);
    }

    public UnidadeModel atualizar(Long unidId,UnidadeModel unidadeModel){
        return unidadePort.atualizar(unidId,unidadeModel);
    }

    public void excluir(Long unidId){
         unidadePort.excluir(unidId);
    }
}
