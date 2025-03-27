package br.com.lucasaraujo.api.config;

import br.com.lucasaraujo.ports.cidade.CidadePort;
import br.com.lucasaraujo.ports.endereco.EnderecoPort;
import br.com.lucasaraujo.ports.fotoModel.FotoPort;
import br.com.lucasaraujo.ports.lotacao.LotacaoPort;
import br.com.lucasaraujo.ports.servidor.ServidorEfetivoPort;
import br.com.lucasaraujo.ports.servidor.ServidorTemporarioPort;
import br.com.lucasaraujo.ports.unidade.UnidadePort;
import br.com.lucasaraujo.stories.cidade.CidadeUseStory;
import br.com.lucasaraujo.stories.endereco.EnderecoUseStory;
import br.com.lucasaraujo.stories.fotoPessoa.FotoPessoaUseStory;
import br.com.lucasaraujo.stories.lotacao.LotacaoUseStory;
import br.com.lucasaraujo.stories.servidor.ServidorEfetivoUseStory;
import br.com.lucasaraujo.stories.servidor.ServidorTemporarioUseStory;
import br.com.lucasaraujo.stories.unidade.UnidadeUseStory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CidadeUseStory cidadeUseStory(CidadePort cidadePort) {
        return new CidadeUseStory (cidadePort);
    }

    @Bean
    public EnderecoUseStory enderecoUseStory(EnderecoPort enderecoPort) {
        return new EnderecoUseStory (enderecoPort);
    }

    @Bean
    public UnidadeUseStory unidadeUseStory(UnidadePort unidadePort) {
        return new UnidadeUseStory (unidadePort);
    }

    @Bean
    public LotacaoUseStory lotacaoUseStory(LotacaoPort lotacaoPort) {
        return new LotacaoUseStory (lotacaoPort);
    }

    @Bean
    public ServidorEfetivoUseStory servidorEfetivoUseStory(ServidorEfetivoPort servidorEfetivoPort) {
        return new ServidorEfetivoUseStory (servidorEfetivoPort) ;
    }

    @Bean
    public ServidorTemporarioUseStory servidorTemporarioUseStory(ServidorTemporarioPort servidorTemporarioPort) {
        return new ServidorTemporarioUseStory (servidorTemporarioPort) ;
    }

    @Bean
    public FotoPessoaUseStory fotoPessoaUseStory(FotoPort fotoPort) {
        return new FotoPessoaUseStory (fotoPort);
    }
}
