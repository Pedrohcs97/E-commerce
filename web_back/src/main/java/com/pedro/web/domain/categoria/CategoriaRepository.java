package com.pedro.web.domain.categoria;

import com.pedro.web.domain.categoria.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findByNome(String nome);
    
}
