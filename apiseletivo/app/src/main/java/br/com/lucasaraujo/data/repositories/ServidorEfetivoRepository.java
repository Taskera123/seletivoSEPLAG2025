package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.ServidorEfetivoEntity;
import br.com.lucasaraujo.data.entities.vo.ServidoresUnidadeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivoEntity,Long> {
    @Query("select new br.com.lucasaraujo.data.entities.vo.ServidoresUnidadeVo(se.pessoa.pessoaNome," +
            " u.unidadeNome, se.pessoa.pessoaDataNascimento,se.pessoa.pessoaId) " +
            "from ServidorEfetivoEntity se " +
            "inner join LotacaoEntity l on l.pessoa.pessoaId = se.pessoa.pessoaId " +
            "inner join l.unidade u on u.unidadeId = l.unidade.unidadeId " +
            "where u.unidadeId = :unidadeId")
    Page<ServidoresUnidadeVo> buscarServidoreLotadosUnidade(Long unidadeId, Pageable pageable);
}
