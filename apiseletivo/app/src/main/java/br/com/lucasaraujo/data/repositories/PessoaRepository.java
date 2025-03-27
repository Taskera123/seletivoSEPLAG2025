package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity,Long> {
}
