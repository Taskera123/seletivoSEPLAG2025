package br.com.lucasaraujo.api.ports.endereco;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.data.entities.EnderecoEntity;
import br.com.lucasaraujo.data.repositories.EnderecoRepository;
import br.com.lucasaraujo.model.CidadeModel;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.ports.endereco.EnderecoPort;
import br.com.lucasaraujo.stories.cidade.CidadeUseStory;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


@Component
public class EnderecoPortImp implements EnderecoPort {

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private CidadeUseStory cidadeUseStory;

    public EnderecoPortImp(EnderecoRepository enderecoRepository,
                           EnderecoMapper enderecoMapper,
                           CidadeUseStory cidadeUseStory) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.cidadeUseStory = cidadeUseStory;
    }


    @Transactional
    @Override
    public EnderecoModel criar(EnderecoModel enderecoModel) {

        validarRegrasEndereco(enderecoModel);

        if (enderecoModel.getCidade().getCidadeId() == null) {
            enderecoModel.setCidade(cidadeUseStory.criar(enderecoModel.getCidade()));
        } else {
            cidadeUseStory.atualizar(enderecoModel.getCidade().getCidadeId(), enderecoModel.getCidade());
        }

        return enderecoMapper.enderecoEntityToModel(
                enderecoRepository.saveAndFlush(
                        enderecoMapper.enderecoModelToEntity(enderecoModel)
                )
        );
    }


    @Override
    public EnderecoModel atualizar(Long cidadeId, EnderecoModel enderecoModel) {

        validarRegrasEndereco(enderecoModel);

        EnderecoModel enderecoModelBanco = buscarPorId(cidadeId);


        CidadeModel cidadeModelBanco = null;

        if(enderecoModel.getCidade().getCidadeId()== null){
             cidadeModelBanco = cidadeUseStory.criar(enderecoModel.getCidade());
        }else{
            cidadeModelBanco= cidadeUseStory.atualizar(enderecoModel.getCidade().getCidadeId()
                    ,enderecoModel.getCidade());
        }

        enderecoModelBanco.setEnderecoTipoLogradouro(enderecoModel.getEnderecoTipoLogradouro());
        enderecoModelBanco.setEnderecoLogradouro(enderecoModel.getEnderecoLogradouro());
        enderecoModelBanco.setEnderecoNumero(enderecoModel.getEnderecoNumero());
        enderecoModelBanco.setEnderecoBairro(enderecoModel.getEnderecoBairro());
        enderecoModelBanco.setCidade(cidadeModelBanco);

        return enderecoMapper.enderecoEntityToModel(
                enderecoRepository.save(
                        enderecoMapper.enderecoModelToEntity(enderecoModelBanco)
                )
        );
    }

    @Override
    public PageResponse<EnderecoModel> listaEnderecosPaginado(PageQuery pageQuery) {
        Page<EnderecoEntity> page = enderecoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<EnderecoModel> enderecoModelPage = page.map(enderecoMapper::enderecoEntityToModel);

        return new PageResponse<>(
                enderecoModelPage.getNumber(),
                enderecoModelPage.getTotalPages(),
                enderecoModelPage.getTotalElements(),
                enderecoModelPage.getSize(),
                enderecoModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long enderecoId) {
        try{
        EnderecoEntity enderecoEntity  = enderecoMapper
                .enderecoModelToEntity(buscarPorId(enderecoId));
        enderecoRepository.delete(enderecoEntity);
        }catch (Exception e){
            throw new RuntimeException("Não foi possível excluir o endereco pois o mesmo está ligado a pessoas e/ou unidades");
        }
    }


    private void validarRegrasEndereco(EnderecoModel enderecoModel) {

        if (enderecoModel.getEnderecoTipoLogradouro().isBlank()){
            throw new RuntimeException("É obrigatório informar o tipo de logradouro");
        }

        if (enderecoModel.getEnderecoTipoLogradouro().length()>50){
            throw new RuntimeException("Tamanho máximo para tipo de logradouro: 50 caracteres");
        }

        if (enderecoModel.getEnderecoLogradouro().isBlank()){
            throw new RuntimeException("É obrigatório informar o logradouro");
        }


        if (enderecoModel.getEnderecoLogradouro().length()>220){
            throw new RuntimeException("Tamanho máximo para logradouro: 200 caracteres");
        }

        if (enderecoModel.getEnderecoBairro().isBlank()){
            throw new RuntimeException("É obrigatório informar o bairro");
        }

        if (enderecoModel.getEnderecoBairro().length()>220){
            throw new RuntimeException("Tamanho máximo para bairro: 100 caracteres");
        }

        if (enderecoModel.getEnderecoNumero() == null){
            throw new RuntimeException("É obrigatório informar o numero");
        }
        if (enderecoModel.getCidade() == null){
            throw new RuntimeException("É obrigatório informar a cidade");
        }
    }

    @Override
    public EnderecoModel buscarPorId(Long cidadeId) {
        return enderecoMapper
                .enderecoEntityToModel( enderecoRepository.findById(cidadeId)
                        .orElseThrow(() -> new NotFoundException("Endereco não encontrado")));
    }


    @Override
    public PageResponse<EnderecoModel> buscarEnderecoFuncional(String nome, PageQuery pageQuery) {
        Page<EnderecoEntity> page = enderecoRepository
                .listaEnderecosFuncPorParteNome(
                    nome,PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
                );

        Page<EnderecoModel> enderecoModelPage = page.map(enderecoMapper::enderecoEntityToModel);

        return new PageResponse<>(
                enderecoModelPage.getNumber(),
                enderecoModelPage.getTotalPages(),
                enderecoModelPage.getTotalElements(),
                enderecoModelPage.getSize(),
                enderecoModelPage.getContent()
        );
    }
}
