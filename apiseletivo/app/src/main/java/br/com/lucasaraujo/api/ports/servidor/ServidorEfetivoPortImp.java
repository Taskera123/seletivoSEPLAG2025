package br.com.lucasaraujo.api.ports.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.api.mappers.pessoa.PessoaMapper;
import br.com.lucasaraujo.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.lucasaraujo.data.entities.*;
import br.com.lucasaraujo.data.entities.vo.ServidoresUnidadeVo;
import br.com.lucasaraujo.data.repositories.*;
import br.com.lucasaraujo.model.*;
import br.com.lucasaraujo.ports.servidor.ServidorEfetivoPort;
import br.com.lucasaraujo.service.StorageService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ServidorEfetivoPortImp implements ServidorEfetivoPort {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final PessoaRepository pessoaRepository;

    private final ServidorEfetivoMapper servidorEfetivoMapper;

    private final PessoaMapper pessoaMapper;

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private final LotacaoRepository lotacaoRepository;

    private final FotoRepository fotoRepository;

    private final StorageService storageService;

    public ServidorEfetivoPortImp(ServidorEfetivoRepository servidorEfetivoRepository,
                                  ServidorEfetivoMapper servidorEfetivoMapper,
                                  PessoaRepository pessoaRepository,
                                  PessoaMapper pessoaMapper,
                                  PessoaEnderecoRepository pessoaEnderecoRepository, EnderecoRepository enderecoRepository,
                                  EnderecoMapper enderecoMapper, LotacaoRepository lotacaoRepository, FotoRepository fotoRepository, StorageService storageService) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.lotacaoRepository = lotacaoRepository;
        this.fotoRepository = fotoRepository;
        this.storageService = storageService;
    }

    @Override
    public ServidorEfetivoModel buscarPorId(Long pessoaId) {

       ServidorEfetivoModel servidorEfetivoModelBd =  servidorEfetivoMapper
                .servidorEfetivoEntityToModel( servidorEfetivoRepository.findById(pessoaId)
                        .orElseThrow(() -> new RuntimeException("Servidor Efetivo não encontrado")));

        Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                .listaEnderecosPessoa(servidorEfetivoModelBd.getPessoa().getPessoaId());
        servidorEfetivoModelBd.getPessoa().setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return servidorEfetivoModelBd;
    }

    @Transactional
    @Override
    public ServidorEfetivoModel criar(ServidorEfetivoModel servidorEfetivoModel) {
        regrasNegocio(servidorEfetivoModel);

        ServidorEfetivoModel servidorEfetivoModel1=  servidorEfetivoMapper.servidorEfetivoEntityToModel(
                servidorEfetivoRepository.saveAndFlush(
                        servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModel)
                )
        );

       Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

       servidorEfetivoModel1.getPessoa().setEnderecoIdList(servidorEfetivoModel.getPessoa().getEnderecoIdList());

       servidorEfetivoModel1.getPessoa().getEnderecoIdList().forEach(e -> {
            EnderecoModel enderecoModelBanco = enderecoMapper
                    .enderecoEntityToModel(enderecoRepository.findById(e)
                            .orElseThrow(() -> new NotFoundException("Endereco não encontrado")));

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(servidorEfetivoModel1.getPessoa().getPessoaId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEnderecoId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEnderecoId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaMapper.pessoaModelToEntity(servidorEfetivoModel1.getPessoa()));
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        servidorEfetivoModel1.getPessoa().setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return servidorEfetivoModel1;
    }

    private void regrasNegocio(ServidorEfetivoModel servidorEfetivoModel) {
        if (servidorEfetivoModel.getServidorMatricula().isBlank()){
            throw new RuntimeException("É obrigatório a matricula");
        }

        if(servidorEfetivoModel.getServidorMatricula().length() >20){
            throw new RuntimeException("Matricula deve ter no maximo 20 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaNome().isBlank()){
            throw new RuntimeException("Nome é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaNome().length() > 200){
            throw new RuntimeException("Nome deve ter no maximo 200 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaPai().isBlank()){
            throw new RuntimeException("Nome do Pai é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaPai().length() > 200){
            throw new RuntimeException("Nome do Pai deve ter no maximo 200 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaMae().isBlank()){
            throw new RuntimeException("Nome da Mae é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaMae().length() > 200){
            throw new RuntimeException("Nome da Mae deve ter no maximo 200 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaSexo().isBlank()){
            throw new RuntimeException("Sexo é obrigatorio");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaSexo().length() > 200){
            throw new RuntimeException("Sexo deve ter no maximo 09 caracteres");
        }

        if(servidorEfetivoModel.getPessoa().getPessoaDataNascimento() == null){
            throw new RuntimeException("Data de Nascimento é obrigatorio");
        }

    }


    @Override
    public ServidorEfetivoModel atualizar(Long pessoaId, ServidorEfetivoModel servidorEfetivoModel) {
        regrasNegocio(servidorEfetivoModel);
        ServidorEfetivoModel servidorEfetivoModelBD = servidorEfetivoMapper.servidorEfetivoEntityToModel(
                servidorEfetivoRepository.findById(pessoaId)
                        .orElseThrow(() -> new RuntimeException("Servidor Efetivo não encontrado"))
        );

        servidorEfetivoModelBD.setServidorMatricula(servidorEfetivoModel.getServidorMatricula());

        PessoaModel pessoaModel = servidorEfetivoModel.getPessoa();
        PessoaEntity pessoaEntityBD = pessoaMapper
                .pessoaModelToEntity(servidorEfetivoModelBD.getPessoa());

        pessoaEntityBD.setPessoaNome(pessoaModel.getPessoaNome());
        pessoaEntityBD.setPessoaDataNascimento(pessoaModel.getPessoaDataNascimento());
        pessoaEntityBD.setPessoaSexo(pessoaModel.getPessoaSexo());
        pessoaEntityBD.setPessoaMae(pessoaModel.getPessoaMae());
        pessoaEntityBD.setPessoaPai(pessoaModel.getPessoaPai());


        Set<Long> enderecoIdsNovos = new HashSet<>(pessoaModel.getEnderecoIdList());
        Set<EnderecoModel> enderecoModelBancoList = enderecoMapper.enderecoEntityListToEnderecoModelList(
                pessoaEnderecoRepository.listaEnderecosPessoa(pessoaId));

        enderecoModelBancoList.forEach(endereco -> {
            if (!enderecoIdsNovos.contains(endereco.getEnderecoId())) {
                PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId(pessoaId, endereco.getEnderecoId());
                pessoaEnderecoRepository.deleteById(pessoaEnderecoId);
            }
        });

        Set<EnderecoEntity> enderecoEntityList = new HashSet<>();
        enderecoIdsNovos.forEach(enderecoId -> {
            EnderecoModel enderecoModelBanco = enderecoMapper.enderecoEntityToModel(
                    enderecoRepository.findById(enderecoId)
                            .orElseThrow(() -> new RuntimeException("Endereco não encontrado"))
            );

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(pessoaEntityBD.getPessoaId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEnderecoId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEnderecoId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaEntityBD);
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        pessoaEntityBD.setEnderecoList(enderecoEntityList);

        pessoaRepository.save(pessoaEntityBD);

        servidorEfetivoModelBD.setPessoa(pessoaMapper.pessoaEntityToModel(pessoaEntityBD));

        servidorEfetivoRepository.save(
                servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModelBD));

        return servidorEfetivoModelBD;
    }

    @Override
    public PageResponse<ServidorEfetivoModel> listaServidoresEfetivosPaginado(PageQuery pageQuery) {
        Page<ServidorEfetivoEntity> page = servidorEfetivoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );
        page.getContent().forEach((p)->{
            Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                    .listaEnderecosPessoa(p.getPessoa().getPessoaId());
            p.getPessoa().setEnderecoList(
                    enderecoEntityList);
        });

        Page<ServidorEfetivoModel> servidorEfetivoModelPage = page.map(servidorEfetivoMapper::servidorEfetivoEntityToModel);

        return new PageResponse<>(
                servidorEfetivoModelPage.getNumber(),
                servidorEfetivoModelPage.getTotalPages(),
                servidorEfetivoModelPage.getTotalElements(),
                servidorEfetivoModelPage.getSize(),
                servidorEfetivoModelPage.getContent()
        );
    }

    @Transactional
    @Override
    public void excluir(Long pessoaId) {
        ServidorEfetivoModel servidorEfetivoModelBanco = buscarPorId(pessoaId);
        servidorEfetivoRepository.delete(servidorEfetivoMapper.servidorEfetivoModelToEntity(servidorEfetivoModelBanco));
        excluirPessoa(servidorEfetivoModelBanco.getPessoa());
    }

    private void excluirPessoa(PessoaModel pessoaModel) {

        LotacaoEntity lotacaoEntity = lotacaoRepository.finByPessoaPessoaId(pessoaModel.getPessoaId());

        if(lotacaoEntity!= null){
            throw new RuntimeException("Não é possivel exluir a pessoa pois a mesma possui lotacoes ligadas a ela");
        }
        Set<PessoaEnderecoEntity> listaPessoasEnderecos = pessoaEnderecoRepository
                .findByPessoaId(pessoaModel.getPessoaId());

        listaPessoasEnderecos.forEach(pessoaEnderecoRepository::delete);


        Set<FotoEntity> listaFotosPessoa = fotoRepository
                .findByPessoaId(pessoaModel.getPessoaId());

        listaFotosPessoa.forEach(fotoRepository::delete);

        pessoaRepository.delete(pessoaMapper
                .pessoaModelToEntity(pessoaModel));
    }

    @Override
    public PageResponse<ServidorEfetivoModel> buscarServidoresLotadosUnidade(Long unidadeId, PageQuery pageQuery) {
        Page<ServidoresUnidadeVo> pageServidoresUnidadeVo = servidorEfetivoRepository.buscarServidoreLotadosUnidade(
                unidadeId, PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        pageServidoresUnidadeVo.forEach(servidor -> {
            Set<String> listaStringBucket = fotoRepository.listaBuckets(servidor.getPessoaId());

            Set<String> linksTemporarios = listaStringBucket.stream()
                    .map(storageService::generateTemporaryLink)
                    .collect(Collectors.toSet());

            servidor.setlistLinkFotos(linksTemporarios);

        });


        Page<ServidorEfetivoModel> pageServidorEfetivoModel = pageServidoresUnidadeVo
                .map(servidorEfetivoMapper::servidorEfetivoVoToModel);

        return new PageResponse<>(
                pageServidorEfetivoModel.getNumber(),
                pageServidorEfetivoModel.getTotalPages(),
                pageServidorEfetivoModel.getTotalElements(),
                pageServidorEfetivoModel.getSize(),
                pageServidorEfetivoModel.getContent()
        );
    }



}
