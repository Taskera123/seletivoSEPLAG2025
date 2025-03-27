package br.com.lucasaraujo.api.ports.unidade;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.api.mappers.unidade.UnidadeMapper;
import br.com.lucasaraujo.data.entities.*;
import br.com.lucasaraujo.data.repositories.EnderecoRepository;
import br.com.lucasaraujo.data.repositories.LotacaoRepository;
import br.com.lucasaraujo.data.repositories.UnidadeEnderecoRepository;
import br.com.lucasaraujo.data.repositories.UnidadeRepository;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.model.UnidadeModel;
import br.com.lucasaraujo.ports.unidade.UnidadePort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UnidadePortImp implements UnidadePort {

    private final UnidadeRepository unidadeRepository;

    private final UnidadeMapper unidadeMapper;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private final UnidadeEnderecoRepository unidadeEnderecoRepository;

    private final LotacaoRepository lotacaoRepository;

    public UnidadePortImp(UnidadeRepository unidadeRepository,
                          UnidadeMapper unidadeMapper,
                          EnderecoRepository enderecoRepository,
                          EnderecoMapper enderecoMapper,
                          UnidadeEnderecoRepository unidadeEnderecoRepository, LotacaoRepository lotacaoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.unidadeMapper = unidadeMapper;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
        this.lotacaoRepository = lotacaoRepository;
    }

    @Override
    public UnidadeModel buscarPorId(Long enderecoId) {
        UnidadeModel unidadeModel = unidadeMapper
                .unidadeEntityToModel( unidadeRepository.findById(enderecoId)
                        .orElseThrow(() -> new NotFoundException("Unidade não encontrada")));
        Set<EnderecoEntity> enderecoEntityList = unidadeEnderecoRepository
                .listaENderecosUnidade(unidadeModel.getUnidadeId());
        unidadeModel.setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return unidadeModel;
    }

    @Override
    public UnidadeModel criar(UnidadeModel unidadeModel) {

        if(unidadeModel.getUnidadeSigla().isBlank() || unidadeModel.getUnidadeSigla().length()>20){
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if(unidadeModel.getUnidadeNome().isBlank() || unidadeModel.getUnidadeNome().length() >200){
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        if(unidadeModel.getEnderecoIdList() == null || unidadeModel.getEnderecoIdList().isEmpty()){
            throw new RuntimeException("É obrigatório passar pelo menos um endereco");
        }

        UnidadeModel unidadeModelBanco =  unidadeMapper.unidadeEntityToModel(
                unidadeRepository.save(
                        unidadeMapper.unidadeModelToEntity(unidadeModel)
                )
        );
        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();
        unidadeModel.getEnderecoIdList().forEach(e->{
            EnderecoModel enderecoModelBanco= enderecoMapper
                    .enderecoEntityToModel(enderecoRepository.findById(e)
                    .orElseThrow(() -> new NotFoundException("Endereco não encontrado")));

            UnidadeEnderecoId unidadeEnderecoId = new UnidadeEnderecoId();
            unidadeEnderecoId.setUnidade(unidadeModelBanco.getUnidadeId());
            unidadeEnderecoId.setEndereco(enderecoModelBanco.getEnderecoId());

            UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
            unidadeEnderecoEntity.setUnidEnderecoId(unidadeEnderecoId);
            unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
            unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(unidadeEnderecoRepository.save(unidadeEnderecoEntity).getEndereco());
        });

        unidadeModelBanco.setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return unidadeModelBanco;

    }

    @Override
    public UnidadeModel atualizar(Long unidadeId, UnidadeModel unidadeModel) {

        if (unidadeModel.getUnidadeSigla().isBlank() || unidadeModel.getUnidadeSigla().length() > 20) {
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if (unidadeModel.getUnidadeNome().isBlank() || unidadeModel.getUnidadeNome().length() > 200) {
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        UnidadeModel unidadeModelBanco = unidadeRepository.findById(unidadeId)
                .map(unidadeEntity -> unidadeMapper.unidadeEntityToModel(unidadeEntity))
                .orElseThrow(() -> new NotFoundException("Unidade não encontrada"));

        unidadeModelBanco.setUnidadeSigla(unidadeModel.getUnidadeSigla());
        unidadeModelBanco.setUnidadeNome(unidadeModel.getUnidadeNome());

        unidadeRepository.save(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));

        Set<Long> enderecoIdsAtualizados = new HashSet<>(unidadeModel.getEnderecoIdList());


        Set<UnidadeEnderecoEntity> unidadeEnderecosExistentes = unidadeEnderecoRepository
                .findByUnidadeId(unidadeModelBanco.getUnidadeId());

        unidadeEnderecosExistentes.forEach(unidadeEnderecoEntity -> {
            Long enderecoId = unidadeEnderecoEntity.getEndereco().getEnderecoId();
            if (!enderecoIdsAtualizados.contains(enderecoId)) {
                unidadeEnderecoRepository.delete(unidadeEnderecoEntity);
            }
        });

        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

        unidadeModel.getEnderecoIdList().forEach(e -> {

            EnderecoModel enderecoModelBanco = enderecoRepository.findById(e)
                    .map(enderecoEntity -> enderecoMapper.enderecoEntityToModel(enderecoEntity))
                    .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));

            Optional<UnidadeEnderecoEntity> unidadeEnderecoExistente = unidadeEnderecoRepository.findByUnidadeAndEndereco(
                    unidadeModelBanco.getUnidadeId(), enderecoModelBanco.getEnderecoId());

            if (unidadeEnderecoExistente.isPresent()) {
                UnidadeEnderecoEntity unidadeEnderecoEntity = unidadeEnderecoExistente.get();
                unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
                unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));

                unidadeEnderecoRepository.save(unidadeEnderecoEntity);
            } else {
                UnidadeEnderecoId unidadeEnderecoId = new UnidadeEnderecoId();
                unidadeEnderecoId.setUnidade(unidadeModelBanco.getUnidadeId());
                unidadeEnderecoId.setEndereco(enderecoModelBanco.getEnderecoId());

                UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
                unidadeEnderecoEntity.setUnidEnderecoId(unidadeEnderecoId);
                unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
                unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));

                unidadeEnderecoRepository.save(unidadeEnderecoEntity);
            }

            enderecoEntityList.add(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
        });

        unidadeModelBanco.setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return unidadeModelBanco;
    }

    @Override
    public PageResponse<UnidadeModel> listaUnidadesPaginado(PageQuery pageQuery) {
        Page<UnidadeEntity> page = unidadeRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );
        page.getContent().forEach((u)->{
            Set<EnderecoEntity> enderecoEntityList = unidadeEnderecoRepository
                    .listaENderecosUnidade(u.getUnidadeId());
            u.setEnderecoList(
                    enderecoEntityList);
        });
        Page<UnidadeModel> unidadeModelPage = page.map(unidadeMapper::unidadeEntityToModel);

        return new PageResponse<>(
                unidadeModelPage.getNumber(),
                unidadeModelPage.getTotalPages(),
                unidadeModelPage.getTotalElements(),
                unidadeModelPage.getSize(),
                unidadeModelPage.getContent()
        );
    }

    @Override
    @Transactional
    public void excluir(Long unidadeId) {

        LotacaoEntity lotacaoEntity = lotacaoRepository.finByUnidadeUnidadeId(unidadeId);

        if(lotacaoEntity!= null){
            throw new RuntimeException("Não é possivel exluir a unidade pois a mesma possui lotaoes ligadas a ela");
        }
        UnidadeModel unidadeModelBanco = buscarPorId(unidadeId);
        Set<UnidadeEnderecoEntity> listaUnidadesEnderecos = unidadeEnderecoRepository
                .findByUnidadeId(unidadeId);

        listaUnidadesEnderecos.forEach(unidadeEnderecoRepository::delete);
        unidadeRepository.delete(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
    }

}
