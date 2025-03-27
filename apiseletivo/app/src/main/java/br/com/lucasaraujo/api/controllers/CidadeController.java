package br.com.lucasaraujo.api.controllers;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.mappers.cidade.CidadeMapper;
import br.com.lucasaraujo.dto.cidade.CidadeRequest;
import br.com.lucasaraujo.dto.cidade.CidadeResponse;
import br.com.lucasaraujo.model.CidadeModel;
import br.com.lucasaraujo.stories.cidade.CidadeUseStory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Cidades")
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeMapper cidadeMapper;
    private final CidadeUseStory cidadeUseStory;

    public CidadeController(CidadeMapper cidadeMapper,
                            CidadeUseStory cidadeUseStory) {
        this.cidadeMapper = cidadeMapper;
        this.cidadeUseStory = cidadeUseStory;
    }

    @Operation(summary = "Criar uma nova cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Cidade criada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public CidadeResponse criarCidade(@RequestBody CidadeRequest cidadeRequest) {
        return cidadeMapper.cidadeModelToResponse(cidadeUseStory
                .criar(cidadeMapper.cidadeRequestToModel(cidadeRequest)));
    }

    @Operation(summary = "Atualizar uma cidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Cidade atualizada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{cidadeId}")
    public CidadeResponse atualizarCidade(@PathVariable Long cidadeId,
                                          @RequestBody CidadeRequest cidadeRequest) {
        return cidadeMapper.cidadeModelToResponse(cidadeUseStory
                .atualizar(cidadeId,cidadeMapper.cidadeRequestToModel(cidadeRequest)));
    }

    @Operation(summary = "Excluir uma cidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Cidade excluida com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<String> excluir(@PathVariable Long cidadeId) {
        cidadeUseStory.excluir(cidadeId);
        return ResponseEntity.ok("Cidade excluida com sucesso");
    }

    @Operation(summary = "Buscar uma cidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Cidade buscada pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/{cidadeId}")
    public CidadeResponse buscarCidadePorId(@PathVariable Long cidadeId) {
        return cidadeMapper.cidadeModelToResponse(cidadeUseStory
                .buscarPorId(cidadeId));
    }

    @Operation(summary = "Listar cidades de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Cidades listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<CidadeResponse> listaCidadesPaginado(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<CidadeModel> cidadePage = cidadeUseStory.listaCidadesPaginado(pageQuery);

        return cidadePage.map(cidadeMapper::cidadeModelToResponse);
    }
}