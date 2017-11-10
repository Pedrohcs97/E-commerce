package com.pedro.web.domain.categoria;

import com.pedro.web.core.controller.RestAbstractController;
import com.pedro.web.domain.produto.ProdutoMessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends RestAbstractController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        return jsonResponse(categoriaService.buscarPorTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable long id) {
        if (categoriaService.buscarPorId(id) != null) {
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_BUSCAR);
            return jsonResponse(categoriaService.buscarPorId(id));
        } else {
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_BUSCAR);
            return jsonResponse();
        }
    }
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Categoria categoria){
        categoriaService.salvar(categoria);
        jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_SALVAR);
        return jsonResponse();
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Categoria categoria) {
        if (categoriaService.buscarPorId(categoria.getId()) != null && categoria.getId() != 0) {
            categoriaService.salvar(categoria);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_SALVAR);
            return jsonResponse();
        } else {
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_SALVAR);
            return jsonResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        if (categoriaService.buscarPorId(id) != null) {
            System.out.println("DELETAR");
            categoriaService.deletar(id);
            jsonResponseService.addSuccess(ProdutoMessageCode.PRODUTO_SUCESSO_EXCLUIR);
            return jsonResponse();
        } else {
            System.out.println("ERRO AO DELETAR");
            jsonResponseService.addError(ProdutoMessageCode.PRODUTO_ERRO_SALVAR);
            return jsonResponse();
        }
    }


}
