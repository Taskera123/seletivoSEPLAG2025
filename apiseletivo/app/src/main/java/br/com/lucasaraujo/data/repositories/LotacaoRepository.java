package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.LotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<LotacaoEntity,Long> {

    @Query("SELECT l FROM LotacaoEntity l WHERE l.unidade.unidId = :unidId")
    LotacaoEntity finByUnidadeUnidId(Long unidId);

    @Query("SELECT l FROM LotacaoEntity l WHERE l.pessoa.pesId = :pesId")
    LotacaoEntity finByPessoaPesId(Long pesId);
}
