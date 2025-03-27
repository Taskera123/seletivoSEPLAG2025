package br.com.lucasaraujo.api.controllers;

import br.com.lucasaraujo.PageQuery;
import br.com.lucasaraujo.PageResponse;
import br.com.lucasaraujo.api.mappers.endereco.EnderecoMapper;
import br.com.lucasaraujo.dto.endereco.EnderecoRequest;
import br.com.lucasaraujo.dto.endereco.EnderecoResponse;
import br.com.lucasaraujo.model.EnderecoModel;
import br.com.lucasaraujo.stories.endereco.EnderecoUseStory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Endereços")
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoUseStory enderecoUseStory;

    public EnderecoController(EnderecoMapper enderecoMapper,
                              EnderecoUseStory enderecoUseStory) {
        this.enderecoMapper = enderecoMapper;
        this.enderecoUseStory = enderecoUseStory;
    }

    @Operation(summary = "Criar uma novo endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco criado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada")
    })
    @PostMapping()
    public EnderecoResponse criarEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .criar(enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }


    @Operation(summary = "Atualizar um endereco pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco atualizado com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @PutMapping("/{enderecoId}")
    public EnderecoResponse atualizarEndereco(@PathVariable Long enderecoId,
                                          @RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .atualizar(enderecoId,enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }

    @Operation(summary = "Excluir um endereco pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco excluido com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<String> excluir(@PathVariable Long enderecoId) {
        enderecoUseStory.excluir(enderecoId);
        return ResponseEntity.ok("Enedereco excluido com sucesso");
    }

    @Operation(summary = "Buscar um endereco pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Endereco buscado pelo Id com sucesso"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/{enderecoId}")
    public EnderecoResponse buscarEnderecpPorId(@PathVariable Long enderecoId) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .buscarPorId(enderecoId));
    }

    @Operation(summary = "Listar enderecos de forma paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Enderecos listados de forma paginado"),
            @ApiResponse(responseCode  = "400", description  = "Requisição inválida"),
            @ApiResponse(responseCode  = "403", description  = "Requisição não autorizada"),
            @ApiResponse(responseCode  = "404", description  = "Serviço não encontrado")
    })
    @GetMapping("/paginado/all")
    public PageResponse<EnderecoResponse> listaEnderecosPaginado(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<EnderecoModel> enderecoPage = enderecoUseStory.listaEnderecosPaginado(pageQuery);

        return enderecoPage.map(enderecoMapper::enderecoModelToResponse);
    }

}