package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.ServidorTemporarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporarioEntity,Long> {
}
