package com.pedro.web.domain.categoria;

import com.pedro.web.core.exceptions.DuplicatedException;
import com.pedro.web.core.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public void salvar(Categoria categoria) {

        if (categoria.getId() != 0) {

            Categoria p = categoriaRepository.findOne(categoria.getId());
            if (p != null) {
                categoriaRepository.save(categoria);
            } else {
                // lançar exception
            }

        } else {

            List<Categoria> categorias = categoriaRepository.findByNome(categoria.getNome());

            if (categorias.size() > 0) {

                System.out.println("---------------- EXCEPTION ");

                throw new DuplicatedException("Item já existe");
            } else {

                System.out.println("---------------- SALVAR ");
                categoriaRepository.save(categoria);
            }

        }


    }

    public void deletar(Long id) {
        Categoria categoria = categoriaRepository.findOne(id);
        if (categoria != null) {
            categoriaRepository.delete(id);
        } else {
            throw new NotFoundException("O item não foi encontrado!");
        }
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findOne(id);
    }

    public List<Categoria> buscarPorTodos() {
        return categoriaRepository.findAll();
    }

}
