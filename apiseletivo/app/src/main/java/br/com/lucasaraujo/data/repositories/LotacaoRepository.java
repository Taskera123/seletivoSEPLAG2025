package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.LotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<LotacaoEntity,Long> {

    @Query("SELECT l FROM LotacaoEntity l WHERE l.unidade.unidadeId = :unidadeId")
    LotacaoEntity finByUnidadeUnidadeId(Long unidadeId);

    @Query("SELECT l FROM LotacaoEntity l WHERE l.pessoa.pessoaId = :pessoaId")
    LotacaoEntity finByPessoaPessoaId(Long pessoaId);
}
