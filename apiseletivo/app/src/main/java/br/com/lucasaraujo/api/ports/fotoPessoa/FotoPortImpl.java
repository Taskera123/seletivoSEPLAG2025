package br.com.lucasaraujo.api.ports.fotoPessoa;

import br.com.lucasaraujo.api.config.NotFoundException;
import br.com.lucasaraujo.api.mappers.fotoPessoa.FotoMapper;
import br.com.lucasaraujo.data.entities.FotoEntity;
import br.com.lucasaraujo.data.entities.PessoaEntity;
import br.com.lucasaraujo.data.repositories.FotoRepository;
import br.com.lucasaraujo.data.repositories.PessoaRepository;
import br.com.lucasaraujo.model.FotoModel;
import br.com.lucasaraujo.ports.fotoModel.FotoPort;
import br.com.lucasaraujo.service.StorageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FotoPortImpl implements FotoPort {

    private final FotoRepository fotoRepository;

    private final FotoMapper fotoMapper;

    private final PessoaRepository pessoaRepository;

    private final StorageService storageService;

    public FotoPortImpl(FotoRepository fotoRepository, FotoMapper fotoMapper, PessoaRepository pessoaRepository, StorageService storageService) {
        this.fotoRepository = fotoRepository;
        this.fotoMapper = fotoMapper;
        this.pessoaRepository = pessoaRepository;
        this.storageService = storageService;
    }

    @Transactional
    public List<FotoModel> uploadFotos(List<FotoModel> fotoModelList) {
        List<FotoModel>fotoModelListaNova = new ArrayList<>();

        if(!fotoModelList.isEmpty()){

            PessoaEntity pessoaEntityBD = pessoaRepository
                    .findById(fotoModelList.get(0)
                            .getPessoaId()).orElseThrow(()->new NotFoundException("Pessoa não encontrada"));

            fotoModelList.forEach((f)->{
                FotoEntity fotoEntity =  new FotoEntity(LocalDate.now(),f.getPessoaId()+"/"+f.getFoto().name(),f.getFoto().checksum());
                fotoEntity.setPessoa(pessoaEntityBD);
                fotoRepository.saveAndFlush(fotoEntity);
                storageService.store(fotoEntity.getFpBucket(),f.getFoto());
                String linkFoto = storageService.generateTemporaryLink(fotoEntity.getFpBucket());
                FotoModel fotoModelNovo = new FotoModel(f.getPessoaId(),linkFoto);
                fotoModelListaNova.add(fotoModelNovo);

            });
        }

        return fotoModelListaNova;
    }

}
