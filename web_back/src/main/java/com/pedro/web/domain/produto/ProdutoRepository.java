package com.pedro.web.domain.produto;

import com.pedro.web.domain.produto.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByNome(String nome);
    
   /*@Query("SELECT p FROM Produto p WHERE p.nome = :nome")
    public boolean verificarNomeExistente(@Param("nome") String nome);

    public Produto findByNome(String nome);

    /*@Query("SELECT p FROM Produto p WHERE p.categoria = :categoria")
    public List<Produto> buscarProdutoPelaCategoria(@Param("categoria") Categoria categoria);*/
}
