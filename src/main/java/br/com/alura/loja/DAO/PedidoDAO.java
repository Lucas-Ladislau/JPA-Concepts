package br.com.alura.loja.DAO;

import br.com.alura.loja.Vo.RelatorioVendasVo;
import br.com.alura.loja.model.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
public class PedidoDAO {

    private final EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    /*Funcao para buscar dados para um relatorio, trazendo a quantidade e a data
    * da ultima venda, por se tratarem de classes diferentes fazemos com que o retorno
    * dessa função seja um Object, porém tem outro modo de fazer*/
    public List<Object[]> relatorioDeVendasObject(){
        String jpql = "SELECT produto.nome, SUM(item.quantidade), MAX(pedido.data) FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, Object[].class).getResultList();
    }

    /*Outra forma de realizar a consulta acima é criarmos um ValueObject para para os valores
    * sendo assim eliminamos a necessidade do Object[], fazemos isso com a classe do ValueObject
    * dando um new como instancia da classe, isso é possibilitado pelo Hibernate, consultas com new.
    * É necessário escrever o Full Qualified Name da classe*/
    public List<RelatorioVendasVo> relatorioDeVendasVo(){
        String jpql = "SELECT new br.com.alura.loja.Vo.RelatorioVendasVo(produto.nome, SUM(item.quantidade), MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
    }


}
