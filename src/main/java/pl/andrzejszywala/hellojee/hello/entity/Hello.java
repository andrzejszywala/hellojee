package pl.andrzejszywala.hellojee.hello.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Hello.findAll, query = "SELECT e FROM Hello e")
public class Hello {
	public static final String findAll = "Registration.all";

    @Id
    @GeneratedValue
    private long id;

    private String value;
    
    public Hello() {
	}
    
	public Hello(String value) {
		super();
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
