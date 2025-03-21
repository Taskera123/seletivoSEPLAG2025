package br.com.lucastasca.data.repositories;

import br.com.lucastasca.data.entities.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<UnidadeEntity,Long> {
}
