package br.com.lucastasca.api.ports.endereco;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.api.mappers.endereco.EnderecoMapper;
import br.com.lucastasca.data.entities.EnderecoEntity;
import br.com.lucastasca.data.repositories.EnderecoRepository;
import br.com.lucastasca.model.CidadeModel;
import br.com.lucastasca.model.EnderecoModel;
import br.com.lucastasca.ports.endereco.EnderecoPort;
import br.com.lucastasca.stories.cidade.CidadeUseStory;
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

        if (enderecoModel.getCidade().getCidId() == null) {
            enderecoModel.setCidade(cidadeUseStory.criar(enderecoModel.getCidade()));
        } else {
            cidadeUseStory.atualizar(enderecoModel.getCidade().getCidId(), enderecoModel.getCidade());
        }

        return enderecoMapper.enderecoEntityToModel(
                enderecoRepository.saveAndFlush(
                        enderecoMapper.enderecoModelToEntity(enderecoModel)
                )
        );
    }

    @Override
    public EnderecoModel buscarPorId(Long cidId) {
        return enderecoMapper
                .enderecoEntityToModel( enderecoRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));
    }

    @Override
    public EnderecoModel atualizar(Long cidId, EnderecoModel enderecoModel) {
        EnderecoModel enderecoModelBanco = buscarPorId(cidId);

        CidadeModel cidadeModelBanco = null;

        if(enderecoModel.getCidade().getCidId()== null){
             cidadeModelBanco = cidadeUseStory.criar(enderecoModel.getCidade());
        }else{
            cidadeModelBanco= cidadeUseStory.atualizar(enderecoModel.getCidade().getCidId()
                    ,enderecoModel.getCidade());
        }

        enderecoModelBanco.setEndTipoLogradouro(enderecoModel.getEndTipoLogradouro());
        enderecoModelBanco.setEndLogradouro(enderecoModel.getEndLogradouro());
        enderecoModelBanco.setEndNumero(enderecoModel.getEndNumero());
        enderecoModelBanco.setEndBairro(enderecoModel.getEndBairro());
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
}
