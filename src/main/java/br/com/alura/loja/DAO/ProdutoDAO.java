package br.com.alura.loja.DAO;

import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    //Fazendo consulta com parametros opcionais utilizando a API do criteria
    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco,
                                                        LocalDate dataCadastro){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();
        if(nome != null && !nome.trim().isEmpty()){
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if(preco != null){
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }
        if(dataCadastro != null){
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);

        return em.createQuery(query).getResultList();
    }

    //Outra forma de fazer sem usar o criteria
    public List<Produto> buscarPorParametros(String nome,
                                             BigDecimal preco, LocalDate dataCadastro) {
        String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
        if (nome != null && !nome.trim().isEmpty()) {
            jpql += " AND p.nome = :nome ";
        }
        if (preco != null) {
            jpql += " AND p.preco = :preco ";
        }
        if (dataCadastro != null) {
            jpql += " AND p.dataCadastro = :dataCadastro ";
        }
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {
            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }

        return query.getResultList();
    }
}
