package com.pedro.web.domain.produto;

import com.pedro.web.core.controller.RestAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin(origins = "*")
public class ProdutoController extends RestAbstractController{

    @Autowired
    ProdutoService produtoService;

	@GetMapping
    public ResponseEntity<?> buscarTodos(){
        return jsonResponse(produtoService.buscarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable long id) {
        if (produtoService.buscarPorId(id) != null) {
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_BUSCAR);
            return jsonResponse(produtoService.buscarPorId(id));
        } else {
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_BUSCAR);
            return jsonResponse();
        }
    }

	@PostMapping
    public ResponseEntity<?> salvar(@RequestBody Produto produto){
        produtoService.salvar(produto);
        jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_SALVAR);
        return jsonResponse();
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Produto produto) {
        if (produtoService.buscarPorId(produto.getId()) != null && produto.getId() != 0) {
            produtoService.salvar(produto);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_SALVAR);
            return jsonResponse();
        } else {
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_SALVAR);
            return jsonResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable long id) {
        if (produtoService.buscarPorId(id) != null) {
            System.out.println("DELETAR");
            produtoService.deletar(id);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_EXCLUIR);
            return jsonResponse();
        } else {
            System.out.println("ERRO AO DELETAR");
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_SALVAR);
            return jsonResponse();
        }
    }

}
