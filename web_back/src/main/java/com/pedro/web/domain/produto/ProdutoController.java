package com.pedro.web.domain.produto;

import com.pedro.web.core.controller.RestAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin(origins = "*")
public class ProdutoController extends RestAbstractController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> buscarProdutos() {
        jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_BUSCAR);
        return jsonResponse(produtoService.buscarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable long id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null && produto.getId() != 0) {
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_BUSCAR);
            return jsonResponse(produtoService.buscarPorId(id));
        } else {
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_BUSCAR);
            return jsonResponse();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvarProduto(@RequestBody Produto produto) {
        System.out.println("salvar");
        if (produto.getId() == 0) {
            System.out.println("IFFF");

            produtoService.salvar(produto);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_SALVAR);
            return jsonResponse();
        } else {
            System.out.println("elseeee");
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_SALVAR);
            return jsonResponse();
        }
    }

    @PutMapping(value= "/{id}")
    public ResponseEntity<?> alterarProduto(@RequestBody Produto produto, @PathVariable long id) {
        if (produto.getId() != 0) {
            System.out.println(produto.getNome());
            produto.setId(id);
            produtoService.salvar(produto);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_ALTERAR);
            return jsonResponse();
        } else {
            System.out.println("ELSE ALTERAR");
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_ALTERAR);
            return jsonResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable long id) {
        if (produtoService.buscarPorId(id) != null) {
            produtoService.deletar(id);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_EXCLUIR);
            return jsonResponse();
        } else {
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_SALVAR);
            return jsonResponse();
        }
    }
}
