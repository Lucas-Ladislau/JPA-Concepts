package br.com.alura.loja.model;

import javax.persistence.*;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
