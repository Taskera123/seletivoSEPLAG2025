package br.com.lucasaraujo.data.repositories;


import br.com.lucasaraujo.data.entities.EnderecoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long> {

    List<EnderecoEntity> findByCidadeCidadeId(Long cidadeId);

    @Query("select e from EnderecoEntity e " +
            "inner join UnidadeEnderecoEntity ue on ue.endereco.enderecoId = e.enderecoId " +
            "inner join LotacaoEntity l on l.unidade.unidadeId = ue.unidade.unidadeId " +
            "inner join PessoaEntity p on pessoaId = l.pessoa.pessoaId "+
            "where lower(p.pessoaNome) like lower(CONCAT('%', :nome, '%'))"

    )
    Page<EnderecoEntity> listaEnderecosFuncPorParteNome(String nome,Pageable pageable);
}
