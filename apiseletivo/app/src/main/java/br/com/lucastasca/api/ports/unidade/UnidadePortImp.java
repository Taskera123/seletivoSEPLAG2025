package br.com.lucastasca.api.ports.unidade;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.api.mappers.endereco.EnderecoMapper;
import br.com.lucastasca.api.mappers.unidade.UnidadeMapper;
import br.com.lucastasca.data.entities.EnderecoEntity;
import br.com.lucastasca.data.entities.UnidadeEnderecoEntity;
import br.com.lucastasca.data.entities.UnidadeEnderecoId;
import br.com.lucastasca.data.entities.UnidadeEntity;
import br.com.lucastasca.data.repositories.EnderecoRepository;
import br.com.lucastasca.data.repositories.UnidadeEnderecoRepository;
import br.com.lucastasca.data.repositories.UnidadeRepository;
import br.com.lucastasca.model.EnderecoModel;
import br.com.lucastasca.model.UnidadeModel;
import br.com.lucastasca.ports.unidade.UnidadePort;
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

    public UnidadePortImp(UnidadeRepository unidadeRepository,
                          UnidadeMapper unidadeMapper,
                          EnderecoRepository enderecoRepository,
                          EnderecoMapper enderecoMapper,
                          UnidadeEnderecoRepository unidadeEnderecoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.unidadeMapper = unidadeMapper;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
    }

    @Override
    public UnidadeModel buscarPorId(Long cidId) {
        UnidadeModel unidadeModel = unidadeMapper
                .unidadeEntityToModel( unidadeRepository.findById(cidId)
                        .orElseThrow(() -> new RuntimeException("Unidade não encontrada")));
        Set<EnderecoEntity> enderecoEntityList = unidadeEnderecoRepository
                .listaENderecosUnidade(unidadeModel.getUnidId());
        unidadeModel.setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return unidadeModel;
    }

    @Override
    public UnidadeModel criar(UnidadeModel unidadeModel) {

        if(unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length()>20){
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if(unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() >200){
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
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
                    .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));

            UnidadeEnderecoId unidadeEnderecoId = new UnidadeEnderecoId();
            unidadeEnderecoId.setUnidade(unidadeModelBanco.getUnidId());
            unidadeEnderecoId.setEndereco(enderecoModelBanco.getEndId());

            UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
            unidadeEnderecoEntity.setUnidEndId(unidadeEnderecoId);
            unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
            unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(unidadeEnderecoRepository.save(unidadeEnderecoEntity).getEndereco());
        });

        unidadeModelBanco.setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return unidadeModelBanco;

    }

    @Override
    public UnidadeModel atualizar(Long unidId, UnidadeModel unidadeModel) {

        if (unidadeModel.getUnidSigla().isBlank() || unidadeModel.getUnidSigla().length() > 20) {
            throw new RuntimeException("Sigla da unidade não pode ser vazio e deve ter no máximo 20 caracteres");
        }

        if (unidadeModel.getUnidNome().isBlank() || unidadeModel.getUnidNome().length() > 200) {
            throw new RuntimeException("Nome da unidade não pode ser vazio e deve ter no máximo 200 caracteres");
        }

        UnidadeModel unidadeModelBanco = unidadeRepository.findById(unidId)
                .map(unidadeEntity -> unidadeMapper.unidadeEntityToModel(unidadeEntity))
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        unidadeModelBanco.setUnidSigla(unidadeModel.getUnidSigla());
        unidadeModelBanco.setUnidNome(unidadeModel.getUnidNome());

        unidadeRepository.save(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));

        Set<Long> enderecoIdsAtualizados = new HashSet<>(unidadeModel.getEnderecoIdList());


        Set<UnidadeEnderecoEntity> unidadeEnderecosExistentes = unidadeEnderecoRepository
                .findByUnidadeId(unidadeModelBanco.getUnidId());

        unidadeEnderecosExistentes.forEach(unidadeEnderecoEntity -> {
            Long enderecoId = unidadeEnderecoEntity.getEndereco().getEndId();
            if (!enderecoIdsAtualizados.contains(enderecoId)) {
                unidadeEnderecoRepository.delete(unidadeEnderecoEntity);
            }
        });

        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

        unidadeModel.getEnderecoIdList().forEach(e -> {

            EnderecoModel enderecoModelBanco = enderecoRepository.findById(e)
                    .map(enderecoEntity -> enderecoMapper.enderecoEntityToModel(enderecoEntity))
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

            Optional<UnidadeEnderecoEntity> unidadeEnderecoExistente = unidadeEnderecoRepository.findByUnidadeAndEndereco(
                    unidadeModelBanco.getUnidId(), enderecoModelBanco.getEndId());

            if (unidadeEnderecoExistente.isPresent()) {
                UnidadeEnderecoEntity unidadeEnderecoEntity = unidadeEnderecoExistente.get();
                unidadeEnderecoEntity.setUnidade(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
                unidadeEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));

                unidadeEnderecoRepository.save(unidadeEnderecoEntity);
            } else {
                UnidadeEnderecoId unidadeEnderecoId = new UnidadeEnderecoId();
                unidadeEnderecoId.setUnidade(unidadeModelBanco.getUnidId());
                unidadeEnderecoId.setEndereco(enderecoModelBanco.getEndId());

                UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
                unidadeEnderecoEntity.setUnidEndId(unidadeEnderecoId);
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
                    .listaENderecosUnidade(u.getUnidId());
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
    public void excluir(Long cidId) {
        UnidadeModel unidadeModelBanco = buscarPorId(cidId);
        unidadeRepository.delete(unidadeMapper.unidadeModelToEntity(unidadeModelBanco));
    }

}
