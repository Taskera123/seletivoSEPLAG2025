package br.com.lucastasca.data.repositories;

import br.com.lucastasca.data.entities.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity,Long> {
}
