package br.com.lucastasca.stories.cidade;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.CidadeModel;
import br.com.lucastasca.ports.cidade.CidadePort;

public class CidadeUseStory {

    private final CidadePort cidadePort;

    public CidadeUseStory(CidadePort cidadePort) {
        this.cidadePort = cidadePort;
    }

    public CidadeModel buscarPorId(Long cidId){
        return cidadePort.buscarPorId(cidId);
    }

    public PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery){
        return cidadePort.listaCidadesPaginado(pageQuery);
    }

    public CidadeModel criar(CidadeModel cidadeModel){
        return cidadePort.criar(cidadeModel);
    }

    public CidadeModel atualizar(Long cidId,CidadeModel cidadeModel){
        return cidadePort.atualizar(cidId,cidadeModel);
    }

    public void excluir(Long cidId){
         cidadePort.excluir(cidId);
    }
}
