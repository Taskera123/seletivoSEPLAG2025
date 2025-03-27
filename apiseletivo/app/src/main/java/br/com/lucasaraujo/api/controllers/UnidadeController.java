package br.com.lucasaraujo.api.controllers;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.mappers.unidade.UnidadeMapper;
import br.com.lucasaraujo.dto.unidade.UnidadeRequest;
import br.com.lucasaraujo.dto.unidade.UnidadeResponse;
import br.com.lucasaraujo.model.UnidadeModel;
import br.com.lucasaraujo.stories.unidade.UnidadeUseStory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Unidades")
@RequestMapping("/unidade")
public class UnidadeController {

    private final UnidadeMapper unidadeMapper;
    private final UnidadeUseStory unidadeUseStory;

    public UnidadeController(UnidadeMapper unidadeMapper,
                             UnidadeUseStory unidadeUseStory) {
        this.unidadeMapper = unidadeMapper;
        this.unidadeUseStory = unidadeUseStory;
    }

    @Operation(summary = "Criar uma nova unidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Lotação criada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public UnidadeResponse criarUnidade(@RequestBody UnidadeRequest unidadeRequest) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .criar(unidadeMapper.unidadeRequestToModel(unidadeRequest)));
    }


    @Operation(summary = "Atualizar uma unidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidade atualizada com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{unidadeId}")
    public UnidadeResponse atualizarUnidade(@PathVariable Long unidadeId,
                                          @RequestBody UnidadeRequest unidadeRequest) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .atualizar(unidadeId,unidadeMapper.unidadeRequestToModel(unidadeRequest)));
    }

    @Operation(summary = "Excluir uma unidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidade excluida com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{unidadeId}")
    public ResponseEntity<String> excluir(@PathVariable Long unidadeId) {
        unidadeUseStory.excluir(unidadeId);
        return ResponseEntity.ok("Unidade excluida com sucesso");
    }

    @Operation(summary = "Buscar uma unidade pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidade buscada pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/{unidadeId}")
    public UnidadeResponse buscarUnidadePorId(@PathVariable Long unidadeId) {
        return unidadeMapper.unidadeModelToResponse(unidadeUseStory
                .buscarPorId(unidadeId));
    }

    @Operation(summary = "Listar unidades de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Unidades listadas de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<UnidadeResponse> listaUnidadesPaginado( @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<UnidadeModel> unidadePage = unidadeUseStory.listaUnidadesPaginado(pageQuery);

        return unidadePage.map(unidadeMapper::unidadeModelToResponse);
    }
}