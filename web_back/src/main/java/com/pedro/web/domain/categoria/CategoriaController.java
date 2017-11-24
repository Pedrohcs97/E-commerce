package com.pedro.web.domain.categoria;

import com.pedro.web.core.controller.RestAbstractController;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends RestAbstractController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> buscarCategorias() {
        jsonResponseService.addSuccess(CategoriaMessageCode.CATEGORIA_SUCESSO_BUSCAR);
        return jsonResponse(categoriaService.buscarPorTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria.getId() != 0 && categoria != null) {
            jsonResponseService.addSuccess(CategoriaMessageCode.CATEGORIA_SUCESSO_BUSCAR);
            return jsonResponse(categoriaService.buscarPorId(id));
        } else {
            jsonResponseService.addError(CategoriaMessageCode.CATEGORIA_ERRO_BUSCAR);
            return jsonResponse();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvarCategoria(@RequestBody Categoria categoria) {
        if (categoria.getId() == 0) {
            System.out.println("SALVAR CATEGORIA");
            categoriaService.salvar(categoria);
            jsonResponseService.addSuccess(CategoriaMessageCode.CATEGORIA_SUCESSO_SALVAR);
            return jsonResponse();
        } else {
            jsonResponseService.addError(CategoriaMessageCode.CATEGORIA_ERRO_SALVAR);
            return jsonResponse();
        }
    }

    @PutMapping(value= "/{id}")
    public ResponseEntity<?> alterarCategoria(@RequestBody Categoria categoria, @PathVariable long id) {
        if (categoria.getId() != 0) {
            categoria.setId(id);
            System.out.println("ALTERAR CATEGORIA");
            categoriaService.salvar(categoria);

            jsonResponseService.addSuccess(CategoriaMessageCode.CATEGORIA_SUCESSO_ALTERAR);
            return jsonResponse();
        } else {
            jsonResponseService.addError(CategoriaMessageCode.CATEGORIA_ERRO_ALTERAR);
            return jsonResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria != null && categoria.getId() != 0) {
            categoriaService.deletar(id);
            jsonResponseService.addSuccess(CategoriaMessageCode.CATEGORIA_SUCESSO_EXCLUIR);
            return jsonResponse();
        } else {
            jsonResponseService.addError(CategoriaMessageCode.CATEGORIA_ERRO_EXCLUIR);
            return jsonResponse();
        }
    }
}
