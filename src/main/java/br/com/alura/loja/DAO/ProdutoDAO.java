package br.com.alura.loja.DAO;

import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

    public List<Produto> searchAll(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> searchByNameProduto(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome",nome)
                .getResultList();
    }

    //Uma coisa interessante da JPA com HIbernate é que pela jpql uma consulta pode ser "oop"
    //logo, para fazer um join basta irmos "adentrando" no atributo
    public List<Produto> searchByNameCategoria(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome",nome)
                .getResultList();
    }

    //Busca do preco de um produto pelo nome
    public BigDecimal searchByPrecoDoProdutoPorNome(String nome){
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome",nome)
                .getSingleResult();
    }
}
