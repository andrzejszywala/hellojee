package pl.andrzejszywala.hellojee.hello.boundary;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hibernate.engine.spi.SessionImplementor;

import pl.andrzejszywala.hellojee.hello.entity.Hello;

@Stateless
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("hello")
public class HelloResource {

	@PersistenceContext
	EntityManager em;

	@Inject
	Logger log;
	
	@GET
	public JsonObject hello(@Context HttpServletRequest request) throws SQLException, UnknownHostException {
		log.info("HELLO JEE");
		DatabaseMetaData metadata = ((SessionImplementor) em.getDelegate()).connection().getMetaData();
		return Json.createObjectBuilder()
				   .add("database", metadata.getDatabaseProductName() + " - " + metadata.getDatabaseProductVersion())
		           .add("host", InetAddress.getLocalHost().getHostName() + " (" + InetAddress.getLocalHost().getHostAddress() + ")")
		           .add("port", request.getServerPort())
		           .add("hello", em.createNamedQuery(Hello.findAll, Hello.class)
		        		           .getResultList()
		        		           .stream()
		        		           .map(Hello::getValue)
		        		           .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add)).build();
	}

}
