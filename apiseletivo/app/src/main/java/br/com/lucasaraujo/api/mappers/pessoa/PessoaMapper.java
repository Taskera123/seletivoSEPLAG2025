package br.com.lucasaraujo.api.mappers.pessoa;

import br.com.lucasaraujo.api.mappers.cidade.CidadeMapper;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.data.entities.EnderecoEntity;
import br.com.lucasaraujo.data.entities.PessoaEntity;
import br.com.lucasaraujo.dto.endereco.EnderecoResponse;
import br.com.lucasaraujo.dto.pessoa.PessoaRequest;
import br.com.lucasaraujo.dto.pessoa.PessoaResponse;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.model.PessoaModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    private final EnderecoMapper enderecoMapper;
    private final CidadeMapper cidadeMapper;

    public PessoaMapper(EnderecoMapper enderecoMapper, CidadeMapper cidadeMapper) {
        this.enderecoMapper = enderecoMapper;
        this.cidadeMapper = cidadeMapper;
    }


    public PessoaResponse pessoaModelToResponse(PessoaModel pessoaModel) {
        if (pessoaModel == null) {
            return null;
        }

        Set<EnderecoResponse> enderecoResponseSet = new HashSet<>();


        for (EnderecoModel enderecoModel : pessoaModel.getEnderecoList()) {
            enderecoResponseSet.add(enderecoMapper.enderecoModelToResponse(enderecoModel));
        }

        return new PessoaResponse(
                pessoaModel.getPessoaId(),
                pessoaModel.getPesNome(),
                pessoaModel.getPesDataNascimento(),
                pessoaModel.getPessoaSexo(),
                pessoaModel.getPessoaMae(),
                pessoaModel.getPessoaPai(),
                enderecoResponseSet
        );
    }

    public PessoaModel pessoaRequestToModel(PessoaRequest pessoaRequest) {
        if (pessoaRequest == null) {
            return null;
        }

        return new PessoaModel(
                pessoaRequest.pesNome(),
                pessoaRequest.pesDataNascimento(),
                pessoaRequest.enderecoIdList(),
                pessoaRequest.pessoaSexo(),
                pessoaRequest.pessoaMae(),
                pessoaRequest.pessoaPai()
        );
    }

    public PessoaModel pessoaEntityToModel(PessoaEntity pessoaEntity) {
        if(pessoaEntity == null){
            return null;
        }

        Set<EnderecoModel> enderecoModelSet = new HashSet<>();

        if(pessoaEntity.getEnderecoList() != null){
            enderecoModelSet = pessoaEntity.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoEntityToModel)
                    .collect(Collectors.toSet());

        }

        return new PessoaModel(
                pessoaEntity.getPessoaId(),
                pessoaEntity.getPesNome(),
                pessoaEntity.getPesDataNascimento(),
                enderecoModelSet,
                pessoaEntity.getPessoaSexo(),
                pessoaEntity.getPessoaMae(),
                pessoaEntity.getPessoaPai()

        );
    }

    public PessoaEntity pessoaModelToEntity(PessoaModel pessoaModel) {
        if(pessoaModel == null){
            return null;
        }
        Set<EnderecoEntity> enderecoEntitySet = new HashSet<>();

        if(pessoaModel.getEnderecoList() != null) {
            enderecoEntitySet = pessoaModel.getEnderecoList().stream()
                    .map(enderecoMapper::enderecoModelToEntity)
                    .collect(Collectors.toSet());
        }
        return new PessoaEntity(
                pessoaModel.getPessoaId(),
                pessoaModel.getPesNome(),
                pessoaModel.getPesDataNascimento(),
                pessoaModel.getPessoaSexo(),
                pessoaModel.getPessoaMae(),
                pessoaModel.getPessoaPai(),
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

    public List<PessoaModel> pessoaEntityListToPessoaModelList(List<PessoaEntity>pessoaEntityList){
        if (pessoaEntityList == null) {
            return null;
        }

        return pessoaEntityList.stream()
                .map(this::pessoaEntityToModel)
                .collect(Collectors.toList());
    }

    public List<PessoaResponse> pessoaModelListToPessoaResponseList(List<PessoaModel>pessoaModelList){
        if (pessoaModelList == null) {
            return null;
        }

        return pessoaModelList.stream()
                .map(this::pessoaModelToResponse)
                .collect(Collectors.toList());
    }


}
