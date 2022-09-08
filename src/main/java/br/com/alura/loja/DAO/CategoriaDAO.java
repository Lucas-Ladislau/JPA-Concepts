package br.com.alura.loja.DAO;

import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
public class CategoriaDAO {

    private final EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }
    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }
}
