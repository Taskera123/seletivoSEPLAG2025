package br.com.lucasaraujo.data.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "pessoa_seq")
    @SequenceGenerator( name = "pessoa_seq", sequenceName = "pessoa_pes_id_seq", allocationSize = 1)
    @Column(name = "pes_id")
    private Long pessoaId;

    @Column(name = "pes_nome", length = 200, nullable = false)
    private String pesNome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate pesDataNascimento;

    @Column(name = "pes_sexo", length = 9, nullable = false)
    private String pessoaSexo;

    @Column(name = "pes_mae", length = 200, nullable = false)
    private String pessoaMae;

    @Column(name = "pes_pai", length = 200, nullable = false)
    private String pessoaPai;

    @Transient
    Set<EnderecoEntity> enderecoList;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public LocalDate getPesDataNascimento() {
        return pesDataNascimento;
    }

    public void setPesDataNascimento(LocalDate pesDataNascimento) {
        this.pesDataNascimento = pesDataNascimento;
    }

    public String getPessoaSexo() {
        return pessoaSexo;
    }

    public void setPessoaSexo(String pessoaSexo) {
        this.pessoaSexo = pessoaSexo;
    }

    public String getPessoaMae() {
        return pessoaMae;
    }

    public void setPessoaMae(String pessoaMae) {
        this.pessoaMae = pessoaMae;
    }

    public String getPessoaPai() {
        return pessoaPai;
    }

    public void setPessoaPai(String pessoaPai) {
        this.pessoaPai = pessoaPai;
    }

    public Set<EnderecoEntity> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(Set<EnderecoEntity> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public PessoaEntity() {
    }

    public PessoaEntity(Long pessoaId, String pesNome, LocalDate pesDataNascimento, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pessoaId = pessoaId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }

    public PessoaEntity(Long pessoaId, String pesNome, LocalDate pesDataNascimento, String pessoaSexo, String pessoaMae, String pessoaPai, Set<EnderecoEntity> enderecoList) {
        this.pessoaId = pessoaId;
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
        this.enderecoList = enderecoList;
    }

    public PessoaEntity(String pesNome, LocalDate pesDataNascimento, String pessoaSexo, String pessoaMae, String pessoaPai) {
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
    }
    public PessoaEntity(String pesNome, LocalDate pesDataNascimento, String pessoaSexo, String pessoaMae, String pessoaPai, Set<EnderecoEntity> enderecoList) {
        this.pesNome = pesNome;
        this.pesDataNascimento = pesDataNascimento;
        this.pessoaSexo = pessoaSexo;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
        this.enderecoList = enderecoList;
    }
}
