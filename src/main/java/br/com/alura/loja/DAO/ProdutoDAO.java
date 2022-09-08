package br.com.alura.loja.DAO;

import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
public class ProdutoDAO {

    private final EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public void atualizar(Produto produto){this.em.merge(produto);}

    public void remover(Produto produto){
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public Produto seachByID(Long id){
        return em.find(Produto.class, id);
    }
}
