package br.com.alura.loja.DAO;

import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Pedido;
import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
public class ClienteDAO {

    private final EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente pedido){
        this.em.persist(pedido);
    }

    public Cliente seachByID(Long id){
        return em.find(Cliente.class, id);
    }
}
