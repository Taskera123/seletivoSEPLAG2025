package br.com.lucastasca.api.controllers;

import br.com.lucastasca.PageQuery;
import br.com.lucastasca.PageResponse;
import br.com.lucastasca.api.mappers.endereco.EnderecoMapper;
import br.com.lucastasca.dto.endereco.EnderecoRequest;
import br.com.lucastasca.dto.endereco.EnderecoResponse;
import br.com.lucastasca.model.EnderecoModel;
import br.com.lucastasca.stories.endereco.EnderecoUseStory;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoUseStory enderecoUseStory;

    public EnderecoController(EnderecoMapper enderecoMapper,
                              EnderecoUseStory enderecoUseStory) {
        this.enderecoMapper = enderecoMapper;
        this.enderecoUseStory = enderecoUseStory;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Criar um novo endereco"),
    })
    @PostMapping()
    public EnderecoResponse criarEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        System.out.println("entrou no controller");
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .criar(enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Buscar um endereco"),
    })
    @GetMapping("/{endId}")
    public EnderecoResponse buscarEnderecpPorId(@PathVariable Long endId) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .buscarPorId(endId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Listar todos enderecos - paginado"),
    })
    @GetMapping("/paginado/all")
    public PageResponse<EnderecoResponse> listaEnderecosPaginado(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int sizePage) {
        PageQuery pageQuery = new PageQuery(page, sizePage);
        PageResponse<EnderecoModel> enderecoPage = enderecoUseStory.listaEnderecosPaginado(pageQuery);

        return enderecoPage.map(enderecoMapper::enderecoModelToResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Atualizar um endereco"),
    })
    @PutMapping("/{endId}")
    public EnderecoResponse atualizarCidade(@PathVariable Long endId,
                                          @RequestBody EnderecoRequest enderecoRequest) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .atualizar(endId,enderecoMapper.enderecoRequestToModel(enderecoRequest)));
    }

   /* @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Excluir um endereco"),
    })
    @DeleteMapping("/{endId}")
    public ResponseEntity<String> excluir(@PathVariable Long endId) {
        return enderecoMapper.enderecoModelToResponse(enderecoUseStory
                .buscarPorId(endId));
    }*/
}