package br.com.lucasaraujo.api.mappers.endereco;

import br.com.lucasaraujo.data.entities.CidadeEntity;
import br.com.lucasaraujo.data.entities.EnderecoEntity;
import br.com.lucasaraujo.dto.cidade.CidadeCompletoRequest;
import br.com.lucasaraujo.dto.cidade.CidadeResponse;
import br.com.lucasaraujo.dto.endereco.EnderecoRequest;
import br.com.lucasaraujo.dto.endereco.EnderecoResponse;
import br.com.lucasaraujo.model.CidadeModel;
import br.com.lucasaraujo.model.EnderecoModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnderecoMapper {

    public EnderecoResponse enderecoModelToResponse(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return null;
        }

        CidadeResponse cidadeResponse = (enderecoModel.getCidade() != null)
                ? new CidadeResponse(
                enderecoModel.getCidade().getCidadeId(),
                enderecoModel.getCidade().getCidadeNome(),
                enderecoModel.getCidade().getCidadeUf()
        )
                : null;

        return new EnderecoResponse(
                enderecoModel.getEnderecoId(),
                enderecoModel.getEnderecoTipoLogradouro(),
                enderecoModel.getEnderecoLogradouro(),
                enderecoModel.getEnderecoNumero(),
                enderecoModel.getEnderecoBairro(),
                cidadeResponse
        );
    }


    public EnderecoModel enderecoRequestToModel(EnderecoRequest enderecoRequest) {
        if (enderecoRequest == null) {
            return null;
        }

        var cidadeRequest = enderecoRequest.cidadeCompletoRequest();
        var cidadeModel = (cidadeRequest != null)
                ? new CidadeModel(cidadeRequest.cidadeId(), cidadeRequest.cidadeNome(), cidadeRequest.cidadeUf())
                : null;

        return new EnderecoModel(
                enderecoRequest.enderecoTipoLogradouro(),
                enderecoRequest.enderecoLogradouro(),
                enderecoRequest.enderecoNumero(),
                enderecoRequest.enderecoBairro(),
                cidadeModel
        );
    }

    public EnderecoRequest enderecoModelToRequest(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return null;
        }

        var cidadeCompletoRequest = (enderecoModel.getCidade() != null)
                ? new CidadeCompletoRequest(
                enderecoModel.getCidade().getCidadeId(),
                enderecoModel.getCidade().getCidadeNome(),
                enderecoModel.getCidade().getCidadeUf()
        )
                : null;

        return new EnderecoRequest(
                enderecoModel.getEnderecoTipoLogradouro(),
                enderecoModel.getEnderecoLogradouro(),
                enderecoModel.getEnderecoNumero(),
                enderecoModel.getEnderecoBairro(),
                cidadeCompletoRequest // Adiciona a cidade ao request
        );
    }

    public EnderecoEntity enderecoModelToEntity(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return null;
        }

        CidadeEntity cidadeEntity = (enderecoModel.getCidade() != null)
                ? new CidadeEntity(
                enderecoModel.getCidade().getCidadeId(),
                enderecoModel.getCidade().getCidadeNome(),
                enderecoModel.getCidade().getCidadeUf()
        )
                : null;

        return new EnderecoEntity(
                enderecoModel.getEnderecoId(),
                enderecoModel.getEnderecoTipoLogradouro(),
                enderecoModel.getEnderecoLogradouro(),
                enderecoModel.getEnderecoNumero(),
                enderecoModel.getEnderecoBairro(),
                cidadeEntity
        );
    }

    public EnderecoModel enderecoEntityToModel(EnderecoEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }

        // Converte CidadeEntity para CidadeModel
        CidadeModel cidadeModel = (enderecoEntity.getCidade() != null)
                ? new CidadeModel(
                enderecoEntity.getCidade().getCidadeId(),
                enderecoEntity.getCidade().getCidadeNome(),
                enderecoEntity.getCidade().getCidadeUf()
        )
                : null;

        return new EnderecoModel(
                enderecoEntity.getEnderecoId(),
                enderecoEntity.getEnderecoTipoLogradouro(),
                enderecoEntity.getEnderecoLogradouro(),
                enderecoEntity.getEnderecoNumero(),
                enderecoEntity.getEnderecoBairro(),
                cidadeModel // Incluindo a cidade convertida
        );
    }


    public Set<EnderecoModel> enderecoEntityListToEnderecoModelList(Set<EnderecoEntity> enderecoEntitySet){
        if (enderecoEntitySet == null) {
            return null;
        }

        return enderecoEntitySet.stream()
                .map(this::enderecoEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<EnderecoEntity> enderecoModelListToEnderecoEntityList(Set<EnderecoModel>enderecoModelSet){
        if (enderecoModelSet == null) {
            return null;
        }

        return enderecoModelSet.stream()
                .map(this::enderecoModelToEntity)
                .collect(Collectors.toSet());
    }

    public Set<EnderecoResponse> enderecoModelListToEnderecoResponseList(Set<EnderecoModel>enderecoModelSet){
        if (enderecoModelSet == null) {
            return null;
        }

        return enderecoModelSet.stream()
                .map(this::enderecoModelToResponse)
                .collect(Collectors.toSet());
    }
}
