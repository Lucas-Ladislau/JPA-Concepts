package br.com.alura.loja.testes;

import br.com.alura.loja.DAO.CategoriaDAO;
import br.com.alura.loja.DAO.ClienteDAO;
import br.com.alura.loja.DAO.PedidoDAO;
import br.com.alura.loja.DAO.ProdutoDAO;
import br.com.alura.loja.Vo.RelatorioVendasVo;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 09/09/2022.
 */
public class CadastroPedido {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);

        Produto produto = produtoDao.seachByID(1l);
        Produto produto2 = produtoDao.seachByID(2l);
        Produto produto3 = produtoDao.seachByID(3l);
        Cliente cliente = clienteDao.seachByID(1l);

        em.getTransaction().begin();
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido2, produto3));

        PedidoDAO pedidoDao = new PedidoDAO(em);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("Total: R$"+totalVendido);

//        List<Object[]> relatorio = pedidoDao.relatorioDeVendasObject();
//        for (Object[] obj : relatorio) {
//            System.out.println(obj[0]);
//            System.out.println(obj[1]);
//            System.out.println(obj[2]);
//        }

        List<RelatorioVendasVo> relatorioVo = pedidoDao.relatorioDeVendasVo();
        relatorioVo.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
        Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

        Cliente cliente = new Cliente("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
