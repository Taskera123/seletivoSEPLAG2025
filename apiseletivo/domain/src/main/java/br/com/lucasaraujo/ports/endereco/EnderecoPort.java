package br.com.lucasaraujo.ports.endereco;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.model.EnderecoModel;

public interface EnderecoPort {

    EnderecoModel criar(EnderecoModel EnderecoModel);

    EnderecoModel buscarPorId(Long cidadeId);

    EnderecoModel atualizar(Long cidadeId, EnderecoModel EnderecoModel);

    PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery);

    void excluir(Long enderecoId);

    PageResponse<EnderecoModel> buscarEnderecoFuncional(String nome, PageQuery pageQuery);
}
