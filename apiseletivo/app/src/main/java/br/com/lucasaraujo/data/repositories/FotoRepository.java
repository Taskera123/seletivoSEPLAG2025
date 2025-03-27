package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.FotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FotoRepository extends JpaRepository<FotoEntity,Long> {

    @Query("select f from FotoEntity f " +
            "where f.pessoa.pessoaId = :pessoaId")
    Set<FotoEntity> findByPessoaId(Long pessoaId);

    @Query("select f.fpBucket from FotoEntity f " +
            "where f.pessoa.pessoaId = :pessoaId")
    Set<String> listaBuckets(Long pessoaId);
}
