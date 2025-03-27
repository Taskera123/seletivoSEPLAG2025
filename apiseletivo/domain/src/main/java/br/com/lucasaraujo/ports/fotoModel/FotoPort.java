package br.com.lucasaraujo.ports.fotoModel;

import br.com.lucasaraujo.model.FotoModel;

import java.util.List;

public interface FotoPort {

    List<FotoModel> uploadFotos(List<FotoModel> fotoModelList);
}
