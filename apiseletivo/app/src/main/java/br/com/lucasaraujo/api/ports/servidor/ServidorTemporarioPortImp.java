package br.com.lucasaraujo.api.ports.servidor;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.api.mappers.pessoa.PessoaMapper;
import br.com.lucasaraujo.api.mappers.servidor.ServidorTemporarioMapper;
import br.com.lucasaraujo.data.entities.*;
import br.com.lucasaraujo.data.repositories.*;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.model.PessoaModel;
import br.com.lucasaraujo.model.ServidorTemporarioModel;
import br.com.lucasaraujo.ports.servidor.ServidorTemporarioPort;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ServidorTemporarioPortImp implements ServidorTemporarioPort {

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    private final PessoaRepository pessoaRepository;

    private final ServidorTemporarioMapper servidorTemporarioMapper;

    private final PessoaMapper pessoaMapper;

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    private final EnderecoRepository enderecoRepository;

    private final EnderecoMapper enderecoMapper;

    private final LotacaoRepository lotacaoRepository;

    private final FotoRepository fotoRepository;

    public ServidorTemporarioPortImp(ServidorTemporarioRepository servidorTemporarioRepository,
                                     ServidorTemporarioMapper servidorTemporarioMapper,
                                     PessoaRepository pessoaRepository,
                                     PessoaMapper pessoaMapper,
                                     PessoaEnderecoRepository pessoaEnderecoRepository, EnderecoRepository enderecoRepository,
                                     EnderecoMapper enderecoMapper, LotacaoRepository lotacaoRepository, FotoRepository fotoRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.servidorTemporarioMapper = servidorTemporarioMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.lotacaoRepository = lotacaoRepository;
        this.fotoRepository = fotoRepository;
    }

    @Override
    public ServidorTemporarioModel buscarPorId(Long pessoaId) {

       ServidorTemporarioModel servidorTemporarioModelBd =  servidorTemporarioMapper
                .servidorTemporarioEntityToModel( servidorTemporarioRepository.findById(pessoaId)
                        .orElseThrow(() -> new RuntimeException("Servidor Temporario não encontrado")));

        Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                .listaEnderecosPessoa(servidorTemporarioModelBd.getPessoa().getPessoaId());
        servidorTemporarioModelBd.getPessoa().setEnderecoList(enderecoMapper.enderecoEntityListToEnderecoModelList(
                enderecoEntityList));
        return servidorTemporarioModelBd;
    }

    @Transactional
    @Override
    public ServidorTemporarioModel criar(ServidorTemporarioModel servidorTemporarioModel) {

        regrasNegocio(servidorTemporarioModel);

        ServidorTemporarioModel servidorTemporarioModeBD=  servidorTemporarioMapper.servidorTemporarioEntityToModel(
                servidorTemporarioRepository.saveAndFlush(
                        servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModel)
                )
        );

       Set<EnderecoEntity> enderecoEntityList = new HashSet<>();

       servidorTemporarioModeBD.getPessoa().setEnderecoIdList(servidorTemporarioModel.getPessoa().getEnderecoIdList());

       servidorTemporarioModeBD.getPessoa().getEnderecoIdList().forEach(e -> {
            EnderecoModel enderecoModelBanco = enderecoMapper
                    .enderecoEntityToModel(enderecoRepository.findById(e)
                            .orElseThrow(() -> new RuntimeException("Endereco não encontrado")));

            PessoaEnderecoId pessoaEnderecoId = new PessoaEnderecoId();
            pessoaEnderecoId.setPessoa(servidorTemporarioModeBD.getPessoa().getPessoaId());
            pessoaEnderecoId.setEndereco(enderecoModelBanco.getEnderecoId());

            PessoaEnderecoEntity pessoaEnderecoEntity = new PessoaEnderecoEntity();
            pessoaEnderecoEntity.setPesEnderecoId(pessoaEnderecoId);
            pessoaEnderecoEntity.setPessoa(pessoaMapper.pessoaModelToEntity(servidorTemporarioModeBD.getPessoa()));
            pessoaEnderecoEntity.setEndereco(enderecoMapper.enderecoModelToEntity(enderecoModelBanco));
            enderecoEntityList.add(pessoaEnderecoRepository.save(pessoaEnderecoEntity).getEndereco());
        });

        servidorTemporarioModeBD.getPessoa().setEnderecoList(enderecoMapper
                .enderecoEntityListToEnderecoModelList(enderecoEntityList));

        return servidorTemporarioModeBD;
    }

    @Transactional
    @Override
    public ServidorTemporarioModel atualizar(Long pessoaId, ServidorTemporarioModel servidorTemporarioModel) {

        regrasNegocio(servidorTemporarioModel);

        // Busca o servidor no banco
        ServidorTemporarioModel servidorTemporarioModeBD = servidorTemporarioMapper.servidorTemporarioEntityToModel(
                servidorTemporarioRepository.findById(pessoaId)
                        .orElseThrow(() -> new RuntimeException("Servidor Temporário não encontrado"))
        );

        servidorTemporarioModeBD.setServidorTemporarioDataAdmissao(servidorTemporarioModel.getServidorTemporarioDataAdmissao());
        servidorTemporarioModeBD.setServidorTemporarioDataDemissao(servidorTemporarioModel.getServidorTemporarioDataDemissao());

        PessoaModel pessoaModel = servidorTemporarioModel.getPessoa();
        PessoaEntity pessoaEntityBD = pessoaMapper.pessoaModelToEntity(servidorTemporarioModeBD.getPessoa());

        pessoaEntityBD.setPesNome(pessoaModel.getPesNome());
        pessoaEntityBD.setPesDataNascimento(pessoaModel.getPesDataNascimento());
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
                            .orElseThrow(() -> new NotFoundException("Endereco não encontrado"))
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

        servidorTemporarioModeBD.setPessoa(pessoaMapper.pessoaEntityToModel(pessoaEntityBD));

        servidorTemporarioRepository.save(
                servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModeBD));

        return servidorTemporarioModeBD;
    }


    @Override
    public PageResponse<ServidorTemporarioModel> listaServidoresTemporariosPaginado(PageQuery pageQuery) {
        Page<ServidorTemporarioEntity> page = servidorTemporarioRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );
        page.getContent().forEach((p)->{
            Set<EnderecoEntity> enderecoEntityList = pessoaEnderecoRepository
                    .listaEnderecosPessoa(p.getPessoa().getPessoaId());
            p.getPessoa().setEnderecoList(
                    enderecoEntityList);
        });

        Page<ServidorTemporarioModel> servidorTemporarioModelPage = page.map(servidorTemporarioMapper::servidorTemporarioEntityToModel);

        return new PageResponse<>(
                servidorTemporarioModelPage.getNumber(),
                servidorTemporarioModelPage.getTotalPages(),
                servidorTemporarioModelPage.getTotalElements(),
                servidorTemporarioModelPage.getSize(),
                servidorTemporarioModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long pessoaId) {
        ServidorTemporarioModel servidorTemporarioModelBanco = buscarPorId(pessoaId);
        servidorTemporarioRepository.delete(servidorTemporarioMapper.servidorTemporarioModelToEntity(servidorTemporarioModelBanco));
        excluirPessoa(servidorTemporarioModelBanco.getPessoa());
    }


    private void regrasNegocio(ServidorTemporarioModel servidorTemporarioModel) {
        if (servidorTemporarioModel.getServidorTemporarioDataAdmissao() == null){
            throw new RuntimeException("Data de admissao é obrigatoria");
        }


        if(servidorTemporarioModel.getPessoa().getPesNome().isBlank()){
            throw new RuntimeException("Nome é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPesNome().length() > 200){
            throw new RuntimeException("Nome deve ter no maximo 200 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPessoaPai().isBlank()){
            throw new RuntimeException("Nome do Pai é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPessoaPai().length() > 200){
            throw new RuntimeException("Nome do Pai deve ter no maximo 200 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPessoaMae().isBlank()){
            throw new RuntimeException("Nome da Mae é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPessoaMae().length() > 200){
            throw new RuntimeException("Nome da Mae deve ter no maximo 200 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPessoaSexo().isBlank()){
            throw new RuntimeException("Sexo é obrigatorio");
        }

        if(servidorTemporarioModel.getPessoa().getPessoaSexo().length() > 200){
            throw new RuntimeException("Sexo deve ter no maximo 09 caracteres");
        }

        if(servidorTemporarioModel.getPessoa().getPesDataNascimento() == null){
            throw new RuntimeException("Data de Nascimento é obrigatorio");
        }

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
}
