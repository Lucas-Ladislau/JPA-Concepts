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
}
