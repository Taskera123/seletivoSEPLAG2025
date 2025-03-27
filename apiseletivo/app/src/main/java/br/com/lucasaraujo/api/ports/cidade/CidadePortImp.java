package br.com.lucasaraujo.api.ports.cidade;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.cidade.CidadeMapper;
import br.com.lucasaraujo.data.entities.CidadeEntity;
import br.com.lucasaraujo.data.entities.EnderecoEntity;
import br.com.lucasaraujo.data.repositories.CidadeRepository;
import br.com.lucasaraujo.data.repositories.EnderecoRepository;
import br.com.lucasaraujo.model.CidadeModel;
import br.com.lucasaraujo.ports.cidade.CidadePort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadePortImp implements CidadePort {

    private final CidadeRepository cidadeRepository;

    private final CidadeMapper cidadeMapper;

    private final EnderecoRepository enderecoRepository;

    public CidadePortImp(CidadeRepository cidadeRepository, CidadeMapper cidadeMapper, EnderecoRepository enderecoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeMapper = cidadeMapper;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public CidadeModel buscarPorId(Long cidadeId) {

        return cidadeMapper
                .cidadeEntityToModel( cidadeRepository.findById(cidadeId)
                        .orElseThrow(() -> new NotFoundException("Cidade não encontrada")));
    }

    @Transactional
    @Override
    public CidadeModel criar(CidadeModel cidadeModel) {
        if(cidadeModel.getCidadeUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(cidadeModel.getCidadeNome().isBlank() || cidadeModel.getCidadeNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }
        return cidadeMapper.cidadeEntityToModel(
                cidadeRepository.saveAndFlush(
                        cidadeMapper.cidadeModelToEntity(cidadeModel)
                )
        );
    }

    @Override
    public CidadeModel atualizar(Long cidadeId, CidadeModel cidadeModel) {

        if(cidadeModel.getCidadeUf().length() !=2){
            throw new RuntimeException("UF deve ter dois caracteres");
        }

        if(cidadeModel.getCidadeNome().isBlank() || cidadeModel.getCidadeNome().length() >200){
            throw new RuntimeException("Nome da cidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        CidadeModel cidadeModelBanco = buscarPorId(cidadeId);

        cidadeModelBanco.setCidadeNome(cidadeModel.getCidadeNome());
        cidadeModelBanco.setCidadeUf(cidadeModel.getCidadeUf());

        return cidadeMapper.cidadeEntityToModel(
                cidadeRepository.save(
                        cidadeMapper.cidadeModelToEntity(cidadeModelBanco)
                )
        );
    }

    @Override
    public PageResponse<CidadeModel> listaCidadesPaginado(PageQuery pageQuery) {
        Page<CidadeEntity> page = cidadeRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<CidadeModel> cidadeModelPage = page.map(cidadeMapper::cidadeEntityToModel);

        return new PageResponse<>(
                cidadeModelPage.getNumber(),
                cidadeModelPage.getTotalPages(),
                cidadeModelPage.getTotalElements(),
                cidadeModelPage.getSize(),
                cidadeModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidadeId) {
        CidadeModel cidadeModelBanco = buscarPorId(cidadeId);
        List<EnderecoEntity> enderecoEntityList = enderecoRepository.findByCidadeCidadeId(cidadeId);
        if(!enderecoEntityList.isEmpty()){
            throw new RuntimeException("Não foi possível excluir a cidade pois a mesma está ligada a um ou mais enderecos");
        }
        cidadeRepository.delete(cidadeMapper.cidadeModelToEntity(cidadeModelBanco));
    }

}
