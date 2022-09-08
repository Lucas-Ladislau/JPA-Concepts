package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Created by Lucas Anderson Ladislau Aguiar on 05/09/2022.
 */
public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence
			.createEntityManagerFactory("loja");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
