package br.com.lucastasca.data.repositories;

import br.com.lucastasca.data.entities.LotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<LotacaoEntity,Long> {
}
