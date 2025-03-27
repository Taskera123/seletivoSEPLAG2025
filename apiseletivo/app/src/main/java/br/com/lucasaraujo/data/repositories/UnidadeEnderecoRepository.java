package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.EnderecoEntity;
import br.com.lucasaraujo.data.entities.UnidadeEnderecoEntity;
import br.com.lucasaraujo.data.entities.UnidadeEnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, UnidadeEnderecoId> {

    @Query("select ue.endereco from UnidadeEnderecoEntity ue where ue.unidEnderecoId.unidade = :unidadeId")
    Set<EnderecoEntity> listaENderecosUnidade(Long unidadeId);

    @Query("select ue from UnidadeEnderecoEntity ue " +
            "where ue.unidEnderecoId.unidade = :unidadeId " +
            "and ue.unidEnderecoId.endereco = :enderecoId")
    Optional<UnidadeEnderecoEntity> findByUnidadeAndEndereco(Long unidadeId, Long enderecoId);

    @Query("select ue from UnidadeEnderecoEntity ue " +
            "where ue.unidEnderecoId.unidade = :unidadeId")
    Set<UnidadeEnderecoEntity> findByUnidadeId(Long unidadeId);
}
