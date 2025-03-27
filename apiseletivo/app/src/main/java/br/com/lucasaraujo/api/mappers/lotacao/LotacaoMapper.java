package br.com.lucasaraujo.api.mappers.lotacao;

import br.com.lucasaraujo.api.mappers.pessoa.PessoaMapper;
import br.com.lucasaraujo.api.mappers.unidade.UnidadeMapper;
import br.com.lucasaraujo.data.entities.LotacaoEntity;
import br.com.lucasaraujo.dto.lotacao.LotacaoRequest;
import br.com.lucasaraujo.dto.lotacao.LotacaoResponse;
import br.com.lucasaraujo.model.LotacaoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LotacaoMapper {

    private final PessoaMapper pessoaMapper;

    private final UnidadeMapper unidadeMapper;

    public LotacaoMapper(PessoaMapper pessoaMapper, UnidadeMapper unidadeMapper) {
        this.pessoaMapper = pessoaMapper;
        this.unidadeMapper = unidadeMapper;
    }

    public LotacaoResponse lotacaoModelToResponse(LotacaoModel lotacaoModel){

        if (lotacaoModel == null) {
            return null;
        }

        return new LotacaoResponse(
                lotacaoModel.getLotacaoId(),
                lotacaoModel.getLotacaoDataLotacao(),
                lotacaoModel.getLotacaoDataRemocao(),
                lotacaoModel.getLotacaoPortaria(),
                pessoaMapper.pessoaModelToResponse(lotacaoModel.getPessoaModel()),
                unidadeMapper.unidadeModelToResponse(lotacaoModel.getUnidadeModel())
        );

    }

    public LotacaoModel lotacaoRequestToModel(LotacaoRequest lotacaoRequest){
       if (lotacaoRequest == null) {
            return null;
        }

        return new LotacaoModel(
                lotacaoRequest.lotacaoDataLotacao(),
                lotacaoRequest.lotacaoDataRemocao(),
                lotacaoRequest.lotacaoPortaria(),
                lotacaoRequest.pessoaId(),
                lotacaoRequest.unidadeId()
        );
    }

    public LotacaoEntity lotacaoModelToEntity(LotacaoModel lotacaoModel){
        if (lotacaoModel == null) {
            return null;
        }

        return new LotacaoEntity(
                lotacaoModel.getLotacaoId(),
                lotacaoModel.getLotacaoDataLotacao(),
                lotacaoModel.getLotacaoDataRemocao(),
                lotacaoModel.getLotacaoPortaria(),
                pessoaMapper.pessoaModelToEntity(lotacaoModel.getPessoaModel()),
                unidadeMapper.unidadeModelToEntity(lotacaoModel.getUnidadeModel())
        );
    }

    public LotacaoModel lotacaoEntityToModel(LotacaoEntity lotacaoEntity){
        if (lotacaoEntity == null) {
            return null;
        }

        return new LotacaoModel(
                lotacaoEntity.getLotacaoId(),
                lotacaoEntity.getLotacaoDataLotacao(),
                lotacaoEntity.getLotacaoDataRemocao(),
                lotacaoEntity.getLotacaoPortaria(),
                pessoaMapper.pessoaEntityToModel(lotacaoEntity.getPessoa()),
                unidadeMapper.unidadeEntityToModel(lotacaoEntity.getUnidade())
        );

    }

    public List<LotacaoModel> lotacaoEntityListToLotacaoModelList(List<LotacaoEntity>LotacaoEntityList){
        if (LotacaoEntityList == null) {
            return null;
        }

        return LotacaoEntityList.stream()
                .map(this::lotacaoEntityToModel)
                .collect(Collectors.toList());
    }

    public List<LotacaoResponse> lotacaoModelListToLotacaoResponseList(List<LotacaoModel>LotacaoModelList){
        if (LotacaoModelList == null) {
            return null;
        }

        return LotacaoModelList.stream()
                .map(this::lotacaoModelToResponse)
                .collect(Collectors.toList());
    }
}
