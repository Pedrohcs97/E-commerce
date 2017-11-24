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
            Categoria c = buscarPorId(categoria.getId());

            if(c != null) {
                categoriaRepository.save(categoria);
            } else {
                throw new NotFoundException("Produto não encontrado.");
            }
        } else {

            categoriaRepository.save(categoria);
        }
//        } else {
//            throw new DuplicatedException(ExceptionMessageCode.MENSAGEM_DUPLICATED_ERROR);
//        }
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
