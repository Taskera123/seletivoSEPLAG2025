package br.com.lucastasca.data.repositories;

import br.com.lucastasca.data.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long> {
}
