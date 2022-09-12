package br.com.alura.loja.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 12/09/2022.
 */
@Entity
public class Livro extends Produto {

    private String autor;
    private int qtdPaginas;

    public Livro(String autor, int qtdPaginas) {
        this.autor = autor;
        this.qtdPaginas = qtdPaginas;
    }


    public Livro() {

    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }
}
