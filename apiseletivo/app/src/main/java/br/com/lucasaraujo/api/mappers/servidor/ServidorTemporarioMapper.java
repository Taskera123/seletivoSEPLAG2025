package br.com.lucasaraujo.api.mappers.servidor;

import br.com.lucasaraujo.api.mappers.pessoa.PessoaMapper;
import br.com.lucasaraujo.data.entities.ServidorTemporarioEntity;
import br.com.lucasaraujo.dto.servidor.ServidorTemporarioRequest;
import br.com.lucasaraujo.dto.servidor.ServidorTemporarioResponse;
import br.com.lucasaraujo.model.ServidorTemporarioModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServidorTemporarioMapper {

    private final PessoaMapper pessoaMapper;

    public ServidorTemporarioMapper(PessoaMapper pessoaMapper) {
        this.pessoaMapper = pessoaMapper;
    }

    public ServidorTemporarioResponse servidorTemporarioModelToResponse(ServidorTemporarioModel servidorTemporarioModel){


        if (servidorTemporarioModel == null) {
            return null;
        }


        return new ServidorTemporarioResponse(
                servidorTemporarioModel.getServidorTemporarioDataAdmissao(),
                servidorTemporarioModel.getServidorTemporarioDataDemissao(),
                pessoaMapper.pessoaModelToResponse(servidorTemporarioModel.getPessoa())
        );
    }

    public ServidorTemporarioModel servidorTemporarioRequestToModel(ServidorTemporarioRequest servidorTemporarioRequest){
        if (servidorTemporarioRequest == null) {
            return null;
        }

        return new ServidorTemporarioModel(
                servidorTemporarioRequest.servidorTemporarioDataAdmissao(),
                servidorTemporarioRequest.servidorTemporarioDataDemissao(),
                pessoaMapper.pessoaRequestToModel(servidorTemporarioRequest.pessoaRequest())
        );
    }

    public ServidorTemporarioEntity servidorTemporarioModelToEntity(ServidorTemporarioModel servidorTemporarioModel){
        if (servidorTemporarioModel == null) {
            return null;
        }

        return new ServidorTemporarioEntity(
                servidorTemporarioModel.getId(),
                servidorTemporarioModel.getServidorTemporarioDataAdmissao(),
                servidorTemporarioModel.getServidorTemporarioDataDemissao(),
                pessoaMapper.pessoaModelToEntity(servidorTemporarioModel.getPessoa())
        );
    }

    public ServidorTemporarioModel servidorTemporarioEntityToModel(ServidorTemporarioEntity servidorTemporarioEntity){
        if (servidorTemporarioEntity == null) {
            return null;
        }

        return new ServidorTemporarioModel(
                servidorTemporarioEntity.getId(),
                servidorTemporarioEntity.getServidorTemporarioDataAdmissao(),
                servidorTemporarioEntity.getServidorTemporarioDataDemissao(),
                pessoaMapper.pessoaEntityToModel(servidorTemporarioEntity.getPessoa())
        );
    }

    public List<ServidorTemporarioModel> servidorEntityListToServidorModelList(List<ServidorTemporarioEntity>servidorTemporarioEntityList){
        if (servidorTemporarioEntityList == null) {
            return null;
        }

        return servidorTemporarioEntityList.stream()
                .map(this::servidorTemporarioEntityToModel)
                .collect(Collectors.toList());
    }

    public List<ServidorTemporarioResponse> servidorTemporarioModelListToServidorResponseList(List<ServidorTemporarioModel>servidorModelList){
        if (servidorModelList == null) {
            return null;
        }

        return servidorModelList.stream()
                .map(this::servidorTemporarioModelToResponse)
                .collect(Collectors.toList());
    }

}
