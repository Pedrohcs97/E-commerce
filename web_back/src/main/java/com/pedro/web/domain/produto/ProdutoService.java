package com.pedro.web.domain.produto;

import com.pedro.web.core.exceptions.DuplicatedException;
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

    public void salvar(Produto produto){

        if(produto.getId() != 0){

            Produto p = produtoRepository.findOne(produto.getId());
            if(p != null){
                produtoRepository.save(produto);
            } else {
                // lançar exception
            }
        } else {
            List<Produto> produtos = produtoRepository.findByNome(produto.getNome());

            if(produtos.size() > 0){
                
                System.out.println("---------------- EXCEPTION ");

                throw new DuplicatedException("Item já existe");
            } else {

                System.out.println("---------------- SALVAR ");
                produtoRepository.save(produto);
            }
        }
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
