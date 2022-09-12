package br.com.alura.loja.model;

import javax.persistence.*;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 08/09/2022.
 */
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente() {
    }

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Método delegate, irá delegar a função a outra classe
    public String getNome() {
        return this.dadosPessoais.getNome();
    }


    public String getCpf() {
        return this.dadosPessoais.getCpf();
    }


}
