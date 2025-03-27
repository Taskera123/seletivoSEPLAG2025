package br.com.lucasaraujo.api.controllers;

import br.com.lucasaraujo.PageQuery;

import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.mappers.lotacao.LotacaoMapper;
import br.com.lucasaraujo.dto.lotacao.LotacaoRequest;
import br.com.lucasaraujo.dto.lotacao.LotacaoResponse;
import br.com.lucasaraujo.model.LotacaoModel;
import br.com.lucasaraujo.stories.lotacao.LotacaoUseStory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Lotações")
@RequestMapping("/lotacao")
public class LotacaoController {

    private final LotacaoMapper lotacaoMapper;
    private final LotacaoUseStory lotacaoUseStory;

    public LotacaoController(LotacaoMapper lotacaoMapper,
                             LotacaoUseStory lotacaoUseStory) {
        this.lotacaoMapper = lotacaoMapper;
        this.lotacaoUseStory = lotacaoUseStory;
    }

    @Operation(summary = "Criar uma nova lotação")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação criada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public LotacaoResponse criarLotacao(@RequestBody LotacaoRequest lotacaoRequest) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .criar(lotacaoMapper.lotacaoRequestToModel(lotacaoRequest)));
    }

    @Operation(summary = "Atualizar uma lotação pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação atualizada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{lotacaoId}")
    public LotacaoResponse atualizarLotacao(@PathVariable Long lotacaoId,
                                          @RequestBody LotacaoRequest lotacaoRequest) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .atualizar(lotacaoId,lotacaoMapper.lotacaoRequestToModel(lotacaoRequest)));
    }

    @Operation(summary = "Excluir uma lotação pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação excluida com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{lotacaoId}")
    public ResponseEntity<String> excluir(@PathVariable Long lotacaoId) {
        lotacaoUseStory.excluir(lotacaoId);
        return ResponseEntity.ok("Lotacao excluida com sucesso");
    }

    @Operation(summary = "Buscar uma lotação pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação buscada pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    public LotacaoResponse buscarLotacaoPorId(@PathVariable Long lotacaoId) {
        return lotacaoMapper.lotacaoModelToResponse(lotacaoUseStory
                .buscarPorId(lotacaoId));
    }

    @Operation(summary = "Listar lotações de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotações listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<LotacaoResponse> listaUnidadesPaginado(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<LotacaoModel> unidadePage = lotacaoUseStory.listaLotacoesPaginado(pageQuery);

        return unidadePage.map(lotacaoMapper::lotacaoModelToResponse);
    }
}