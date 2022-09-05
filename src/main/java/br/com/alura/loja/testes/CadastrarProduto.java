package br.com.alura.loja.testes;

import br.com.alura.loja.DAO.CategoriaDAO;
import br.com.alura.loja.DAO.ProdutoDAO;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
public class CadastrarProduto {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");
        Produto produto = new Produto("Xiomi","Muito legal",new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        //É preciso inicicializar a transação. No caso de aplicações robustas
        //utilizando spring por exemplo, já temos tudo isso com a injeção de dependencia
        //onde podemos usar diretamente a classe DAO
        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(produto);
        em.getTransaction().commit();
        em.close();
    }
}
