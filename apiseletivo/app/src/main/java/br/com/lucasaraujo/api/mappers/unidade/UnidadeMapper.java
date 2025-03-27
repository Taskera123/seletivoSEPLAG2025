package br.com.lucasaraujo.api.mappers.unidade;

import br.com.lucasaraujo.api.mappers.cidade.CidadeMapper;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.data.entities.EnderecoEntity;
import br.com.lucasaraujo.data.entities.UnidadeEntity;
import br.com.lucasaraujo.dto.endereco.EnderecoResponse;
import br.com.lucasaraujo.dto.unidade.UnidadeRequest;
import br.com.lucasaraujo.dto.unidade.UnidadeResponse;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.model.UnidadeModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UnidadeMapper {
    private final EnderecoMapper enderecoMapper;
    private final CidadeMapper cidadeMapper;

    public UnidadeMapper(EnderecoMapper enderecoMapper, CidadeMapper cidadeMapper) {
        this.enderecoMapper = enderecoMapper;
        this.cidadeMapper = cidadeMapper;
    }

    public UnidadeResponse unidadeModelToResponse(UnidadeModel unidadeModel){

        if (unidadeModel == null) {
            return null;
        }

        Set<EnderecoResponse> enderecoResponseSet = new HashSet<>();


        for (EnderecoModel enderecoModel : unidadeModel.getEnderecoList()) {
            enderecoResponseSet.add(enderecoMapper.enderecoModelToResponse(enderecoModel));
        }

        return new UnidadeResponse(
                unidadeModel.getUnidadeId(),
                unidadeModel.getUnidadeNome(),
                unidadeModel.getUnidadeSigla(),
                enderecoResponseSet
        );
    }

    public UnidadeModel unidadeRequestToModel(UnidadeRequest unidadeRequest){
        if (unidadeRequest == null) {
            return null;
        }

        return new UnidadeModel(
                unidadeRequest.unidadeNome(),
                unidadeRequest.unidadeSigla(),
                unidadeRequest.enderecoIdList()
        );
    }

    public UnidadeModel unidadeEntityToModel(UnidadeEntity unidadeEntity) {
        if (unidadeEntity == null) {
            return null;
        }
        Set<EnderecoModel> enderecoModelSet = new HashSet<>();

        if(unidadeEntity.getEnderecoList() != null){
            enderecoModelSet = unidadeEntity.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoEntityToModel)
                    .collect(Collectors.toSet());

       }

        return new UnidadeModel(
                unidadeEntity.getUnidadeId(),
                unidadeEntity.getUnidadeNome(),
                unidadeEntity.getUnidadeSigla(),
                enderecoModelSet
        );
    }

    public UnidadeEntity unidadeModelToEntity(UnidadeModel unidadeModel) {
        if (unidadeModel == null) {
            return null;
        }

        Set<EnderecoEntity> enderecoEntitySet = new HashSet<>();

        if(unidadeModel.getEnderecoList() != null) {
            enderecoEntitySet = unidadeModel.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoModelToEntity)
                    .collect(Collectors.toSet());
        }
        return new UnidadeEntity(
                    unidadeModel.getUnidadeId(),
                    unidadeModel.getUnidadeNome(),
                    unidadeModel.getUnidadeSigla(),
                    enderecoEntitySet
        );

    }

    private EnderecoModel enderecoEntityToModel(EnderecoEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }

        return new EnderecoModel(
                enderecoEntity.getEnderecoId(),
                enderecoEntity.getEnderecoTipoLogradouro(),
                enderecoEntity.getEnderecoLogradouro(),
                enderecoEntity.getEnderecoNumero(),
                enderecoEntity.getEnderecoBairro(),
                cidadeMapper.cidadeEntityToModel(enderecoEntity.getCidade())
        );
    }

    public List<UnidadeModel> unidadeEntityListToUnidadeModelList(List<UnidadeEntity>unidadeEntityList){
        if (unidadeEntityList == null) {
            return null;
        }

        return unidadeEntityList.stream()
                .map(this::unidadeEntityToModel)
                .collect(Collectors.toList());
    }

    public List<UnidadeResponse> unidadeModelListToUnidadeResponseList(List<UnidadeModel>unidadeModelList){
        if (unidadeModelList == null) {
            return null;
        }

        return unidadeModelList.stream()
                .map(this::unidadeModelToResponse)
                .collect(Collectors.toList());
    }
}
