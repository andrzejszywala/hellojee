package pl.andrzejszywala.hellojee;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.andrzejszywala.hellojee.hello.entity.Hello;

@Singleton
@Startup
public class InitDB {

	@PersistenceContext 
	EntityManager em;
	
	@PostConstruct
	private void init() {
		em.persist(new Hello("Hello"));
		em.persist(new Hello("Witaj"));
		em.persist(new Hello("Hallo"));
		em.persist(new Hello("Hej"));
		em.persist(new Hello("Ahoj"));
		em.persist(new Hello("Bonjour"));
		em.persist(new Hello("Hola"));
	}
}
