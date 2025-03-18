package br.com.lucastasca.data.repositories;

import br.com.lucastasca.data.entities.UnidadeEnderecoEntity;
import br.com.lucastasca.data.entities.UnidadeEnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, UnidadeEnderecoId> {
}
