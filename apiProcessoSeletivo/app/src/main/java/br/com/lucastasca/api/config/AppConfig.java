package br.com.lucastasca.api.config;

import br.com.lucastasca.ports.cidade.CidadePort;
import br.com.lucastasca.ports.endereco.EnderecoPort;
import br.com.lucastasca.ports.lotacao.LotacaoPort;
import br.com.lucastasca.ports.unidade.UnidadePort;
import br.com.lucastasca.stories.cidade.CidadeUseStory;
import br.com.lucastasca.stories.endereco.EnderecoUseStory;
import br.com.lucastasca.stories.lotacao.LotacaoUseStory;
import br.com.lucastasca.stories.unidade.UnidadeUseStory;
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
}
