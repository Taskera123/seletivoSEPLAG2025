package br.com.lucasaraujo.api.controllers;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.api.mappers.fotoPessoa.FotoMapper;
import br.com.lucasaraujo.api.mappers.servidor.ServidorEfetivoMapper;
import br.com.lucasaraujo.dto.endereco.EnderecoResponse;
import br.com.lucasaraujo.dto.fotoPessoa.FotoRequest;
import br.com.lucasaraujo.dto.fotoPessoa.FotoResponse;
import br.com.lucasaraujo.dto.servidor.ServidorEfetivoLotacaoResponse;
import br.com.lucasaraujo.dto.servidor.ServidorEfetivoRequest;
import br.com.lucasaraujo.dto.servidor.ServidorEfetivoResponse;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.model.ServidorEfetivoModel;
import br.com.lucasaraujo.service.Resource;
import br.com.lucasaraujo.stories.endereco.EnderecoUseStory;
import br.com.lucasaraujo.stories.fotoPessoa.FotoPessoaUseStory;
import br.com.lucasaraujo.stories.servidor.ServidorEfetivoUseStory;
import br.com.lucasaraujo.util.HashingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Servidores Efetivos")
@RequestMapping("/servidor-efetivo")
public class ServidorEfetivoController {

    private final ServidorEfetivoMapper servidorEfetivoMapper;

    private final ServidorEfetivoUseStory servidorEfetivoUseStory;

    private final FotoMapper fotoMapper;

    private final FotoPessoaUseStory fotoPessoaUseStory;

    private final EnderecoUseStory enderecoUseStory;

    private final EnderecoMapper enderecoMapper;

    public ServidorEfetivoController(ServidorEfetivoMapper servidorEfetivoMapper,
                                     ServidorEfetivoUseStory servidorEfetivoUseStory,
                                     FotoMapper fotoMapper, FotoPessoaUseStory fotoPessoaUseStory, EnderecoUseStory enderecoUseStory, EnderecoMapper enderecoMapper
    ) {
        this.servidorEfetivoMapper = servidorEfetivoMapper;
        this.servidorEfetivoUseStory = servidorEfetivoUseStory;
        this.fotoMapper = fotoMapper;
        this.fotoPessoaUseStory = fotoPessoaUseStory;
        this.enderecoUseStory = enderecoUseStory;
        this.enderecoMapper = enderecoMapper;
    }

    @Operation(summary = "Criar um novo servidor efetivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidor efetivo criado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public ServidorEfetivoResponse criarServidorEfetivo(
            @RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .criar(servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));

    }

    @Operation(summary = "Atualizar um servidor efetivo pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidor efetivo atualizado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{pessoaId}")
    public ServidorEfetivoResponse atualizarServidorEfetivo(@PathVariable Long pessoaId,
                                                            @RequestBody ServidorEfetivoRequest servidorEfetivoRequest)
    {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .atualizar(pessoaId,servidorEfetivoMapper.servidorEfetivoRequestToModel(servidorEfetivoRequest)));
    }

    @Operation(summary = "Fazer upload de fotos de um servidor efetivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Upload de fotos doServidor efetivo enviado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PostMapping(value = "/upload-fotos/{pessoaId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public List<FotoResponse> uploadFotos(
            @PathVariable Long pessoaId,
            @RequestParam(name = "fotos", required = false) List<MultipartFile> fotos
    ){
        List<Resource> listaResource = fotos.stream().map(this::resourceOf).toList();
        List<FotoResponse>listaFotoResponse =new ArrayList<FotoResponse>();
        List<FotoRequest>listaFotoRequest =new ArrayList<FotoRequest>();

        listaResource.forEach((f)->{
            FotoRequest fotoRequest = new FotoRequest(pessoaId,f);
            listaFotoRequest.add(fotoRequest);
        });

        listaFotoResponse =  fotoMapper.fotoModelListToFotoResponseList(fotoPessoaUseStory
                .uploadFotos(fotoMapper.fotoRequestListToFotoModelList(listaFotoRequest)));

        return listaFotoResponse;
    }

    @Operation(summary = "Excluir uma Servidor efetivo pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servido efetivo excluido com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<String> excluir(@PathVariable Long pessoaId) {
        servidorEfetivoUseStory.excluir(pessoaId);
        return ResponseEntity.ok("Servidor Efetivo excluido com sucesso");
    }

    @Operation(summary = "Buscar um servidor efetivo pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidor efetivo buscado pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })

    @GetMapping("/{pessoaId}")
    public ServidorEfetivoResponse buscarServidorEfetivoPorId(@PathVariable Long pessoaId) {
        return servidorEfetivoMapper.servidorEfetivoModelToResponse(servidorEfetivoUseStory
                .buscarPorId(pessoaId));
    }

    @Operation(summary = "Listar servidores efetivos de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidores efetivos listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<ServidorEfetivoResponse>servidorEfetivoUseStory(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<ServidorEfetivoModel> servidorEfetivoPage = servidorEfetivoUseStory
                .listaServidoresEfetivosPaginado(pageQuery);

        return servidorEfetivoPage.map(servidorEfetivoMapper::servidorEfetivoModelToResponse);
    }

    private Resource resourceOf(final MultipartFile part) {
        if (part == null) {
            return null;
        }

        try {
            return Resource.with(
                    part.getBytes(),
                    HashingUtils.checksum(part.getBytes()),
                    part.getContentType(),
                    part.getOriginalFilename()
            );
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Operation(summary = "Consultar os servidores efetivos lotados em determinada unidade parametrizando a consulta pelo atributo unid_id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidores efetivos listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/lotados-unidade/{unidadeId}")
    public PageResponse<ServidorEfetivoLotacaoResponse> servidoresLotadosUnidade(@PathVariable Long unidadeId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<ServidorEfetivoModel> paginado = servidorEfetivoUseStory
                .buscarServidoreLotadosUnidade(unidadeId,pageQuery);

        return paginado.map(servidorEfetivoMapper::servidorEfetivLotacaoModelToResponse);
    }

    @Operation(summary = "consultar o endereço funcional (da unidade onde o servidor é lotado) a partir de uma parte do nome do servidor efetivo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Servidores efetivos listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/endereco-funcional")
    public PageResponse<EnderecoResponse> enderecoFuncional(@RequestParam String nome,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<EnderecoModel> paginado = enderecoUseStory
                .buscarEnderecoFuncional(nome,pageQuery);

        return paginado.map(enderecoMapper::enderecoModelToResponse);
    }

}