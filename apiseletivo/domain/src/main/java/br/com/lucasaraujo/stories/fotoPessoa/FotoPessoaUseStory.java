package br.com.lucasaraujo.stories.fotoPessoa;

import br.com.lucasaraujo.model.FotoModel;
import br.com.lucasaraujo.ports.fotoModel.FotoPort;

import java.util.List;

public class FotoPessoaUseStory {

    private final FotoPort fotoPort;

    public FotoPessoaUseStory(FotoPort fotoPort) {
        this.fotoPort = fotoPort;
    }

    public List<FotoModel> uploadFotos(List<FotoModel> fotoModelList){
        return fotoPort.uploadFotos(fotoModelList);
    }
}
