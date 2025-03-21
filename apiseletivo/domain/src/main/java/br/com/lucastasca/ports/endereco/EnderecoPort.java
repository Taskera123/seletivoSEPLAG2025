package br.com.lucastasca.ports.endereco;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.model.EnderecoModel;

public interface EnderecoPort {

    EnderecoModel criar(EnderecoModel EnderecoModel);

    EnderecoModel buscarPorId(Long cidId);

    EnderecoModel atualizar(Long cidId, EnderecoModel EnderecoModel);

    PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery);

}
