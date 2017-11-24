package com.pedro.web.domain.produto;

import com.pedro.web.core.exceptions.DuplicatedException;
import com.pedro.web.core.exceptions.ExceptionMessageCode;
import com.pedro.web.core.exceptions.NotFoundException;
import com.pedro.web.domain.produto.Produto;
import com.pedro.web.domain.categoria.CategoriaRepository;
import com.pedro.web.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void salvar(Produto produto) {
            if (produto.getId() != 0) {
                Produto p = buscarPorId(produto.getId());

                if(p != null) {
                    produtoRepository.save(produto);
                } else {
                    throw new NotFoundException("Produto nao encontrado.");
                }
            } else {
                System.out.println(produto.getId() + "SALVAR SERVICE");
                produtoRepository.save(produto);
            }
//        } else {
//            throw new DuplicatedException(ExceptionMessageCode.MENSAGEM_DUPLICATED_ERROR);
//        }
    }


    public void deletar(long id) {
        Produto produto = produtoRepository.findOne(id);
        if (produto != null) {
            produtoRepository.delete(produto);
        } else {
            throw new NotFoundException("O item não foi encontrado!");
        }
    }

    public Produto buscarPorId(long id) {
        return produtoRepository.findOne(id);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

}
