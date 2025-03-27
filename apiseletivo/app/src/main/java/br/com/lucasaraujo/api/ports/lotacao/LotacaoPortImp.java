package br.com.lucasaraujo.api.ports.lotacao;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.lotacao.LotacaoMapper;
import br.com.lucasaraujo.api.mappers.pessoa.PessoaMapper;
import br.com.lucasaraujo.data.entities.LotacaoEntity;
import br.com.lucasaraujo.data.repositories.LotacaoRepository;
import br.com.lucasaraujo.data.repositories.PessoaRepository;
import br.com.lucasaraujo.model.LotacaoModel;
import br.com.lucasaraujo.model.PessoaModel;
import br.com.lucasaraujo.model.UnidadeModel;
import br.com.lucasaraujo.ports.lotacao.LotacaoPort;
import br.com.lucasaraujo.stories.unidade.UnidadeUseStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class LotacaoPortImp implements LotacaoPort {

    private final LotacaoRepository lotacaoRepository;

    private final LotacaoMapper lotacaoMapper;

    private final UnidadeUseStory unidadeUseStory;

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;


    public LotacaoPortImp(LotacaoRepository lotacaoRepository, LotacaoMapper lotacaoMapper, UnidadeUseStory unidadeUseStory, PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
        this.lotacaoRepository = lotacaoRepository;
        this.lotacaoMapper = lotacaoMapper;
        this.unidadeUseStory = unidadeUseStory;
        this.pessoaRepository = pessoaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    @Override
    public LotacaoModel buscarPorId(Long cidadeId) {

        return lotacaoMapper
                .lotacaoEntityToModel( lotacaoRepository.findById(cidadeId)
                        .orElseThrow(() -> new NotFoundException("Lotação não encontrada")));
    }

    @Override
    public LotacaoModel criar(LotacaoModel lotacaoModel) {

        regrasNegocio(lotacaoModel);

        PessoaModel pessoaModelBd = pessoaMapper.pessoaEntityToModel(pessoaRepository.findById(lotacaoModel.getPessoaId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada")));

        UnidadeModel unidadeModel = unidadeUseStory
                .buscarPorId(lotacaoModel.getUnidadeId());
        lotacaoModel.setPessoaModel(pessoaModelBd);

        lotacaoModel.setUnidadeModel(unidadeModel);

        return lotacaoMapper.lotacaoEntityToModel(
                lotacaoRepository.save(
                        lotacaoMapper.lotacaoModelToEntity(lotacaoModel)
                )
        );
    }

    @Override
    public LotacaoModel atualizar(Long lotacaoId, LotacaoModel lotacaoModel) {

        regrasNegocio(lotacaoModel);

        LotacaoModel lotacaoModelBanco = buscarPorId(lotacaoId);

        lotacaoModelBanco.setLotacaoDataLotacao(lotacaoModel.getLotacaoDataLotacao());
        lotacaoModelBanco.setLotacaoDataRemocao(lotacaoModel.getLotacaoDataRemocao());
        lotacaoModelBanco.setLotacaoPortaria(lotacaoModel.getLotacaoPortaria());

        PessoaModel pessoaModelBanco = pessoaMapper.pessoaEntityToModel(pessoaRepository.findById(lotacaoModel.getPessoaId())
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada")));

        UnidadeModel unidadeModelBanco = unidadeUseStory
                .buscarPorId(lotacaoModel.getUnidadeId());
        lotacaoModelBanco.setPessoaModel(pessoaModelBanco);
        lotacaoModelBanco.setUnidadeModel(unidadeModelBanco);

        return lotacaoMapper.lotacaoEntityToModel(
                lotacaoRepository.save(
                        lotacaoMapper.lotacaoModelToEntity(lotacaoModelBanco)
                )
        );
    }

    private void regrasNegocio(LotacaoModel lotacaoModel) {
        if(lotacaoModel.getLotacaoDataLotacao() == null){
            throw new RuntimeException("É obrigatório informar a data de Lotação");
        }

        if(lotacaoModel.getLotacaoPortaria().isBlank()){
            throw new RuntimeException("É obrigatório informar a Portaria");
        }

        if(lotacaoModel.getLotacaoPortaria().length() > 100){
            throw new RuntimeException("Portaria deve ter no maximo 100 caracteres");
        }

        if(lotacaoModel.getPessoaId() == null){
            throw new RuntimeException("É obrigatório informar o id da pessoa");
        }

        if(lotacaoModel.getUnidadeId() == null){
            throw new RuntimeException("É obrigatório informar o id da unidade");
        }

    }

    @Override
    public PageResponse<LotacaoModel> listaLotacoesPaginado(PageQuery pageQuery) {
        Page<LotacaoEntity> page = lotacaoRepository.findAll(
                PageRequest.of(pageQuery.getPage(), pageQuery.getSizePage())
        );

        Page<LotacaoModel> lotacaoModelPage = page.map(lotacaoMapper::lotacaoEntityToModel);

        return new PageResponse<>(
                lotacaoModelPage.getNumber(),
                lotacaoModelPage.getTotalPages(),
                lotacaoModelPage.getTotalElements(),
                lotacaoModelPage.getSize(),
                lotacaoModelPage.getContent()
        );
    }

    @Override
    public void excluir(Long cidadeId) {
        LotacaoModel lotacaoModelBanco = buscarPorId(cidadeId);
        lotacaoRepository.delete(lotacaoMapper.lotacaoModelToEntity(lotacaoModelBanco));
    }

}
