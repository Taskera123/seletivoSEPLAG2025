package br.com.lucasaraujo.data.repositories;

import br.com.lucasaraujo.data.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEnderecoEntity, PessoaEnderecoId> {

    @Query("select pe.endereco from PessoaEnderecoEntity pe where pe.pesEnderecoId.pessoa = :pessoaId")
    Set<EnderecoEntity> listaEnderecosPessoa(Long pessoaId);

    @Query("select pe from PessoaEnderecoEntity pe " +
            "where pe.pesEnderecoId.pessoa = :pessoaId " +
            "and pe.pesEnderecoId.endereco = :enderecoId")
    Optional<PessoaEnderecoEntity> findByPessoaAndEndereco(Long pessoaId, Long enderecoId);

    @Query("select pe from PessoaEnderecoEntity pe " +
            "where pe.pesEnderecoId.pessoa = :pessoaId")
    Set<PessoaEnderecoEntity> findByPessoaId(Long pessoaId);
}
