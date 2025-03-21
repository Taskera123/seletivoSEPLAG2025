package br.com.lucastasca.data.repositories;

import br.com.lucastasca.data.entities.ServidorEfetivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivoEntity,Long> {
}
