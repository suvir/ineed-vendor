package example.jpa;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/memberlist")
/**
 * CRUD service of vendor list table. It uses REST style.
 *
 */
public class MemberListResource {

	private UserTransaction utx;
	private EntityManager em;

	public MemberListResource() {
		utx = getUserTransaction();
		em = getEm();
	}

	@POST
	public Response create(@FormParam("first_name") String first_name, @FormParam("last_name") String last_name, @FormParam("city") String city, 
			   			   @FormParam("phone") String phone, @FormParam("email") String email) {
		Member member = new Member();
		member.setFirstName(first_name);
		member.setLastName(last_name);
		member.setCity(city);
		member.setPhone(phone);
		member.setEmail(email);
		try {
			utx.begin();
			em.persist(member);
			utx.commit();
			return Response.ok(member.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();			
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@DELETE
	public Response delete(@QueryParam("id") long id) {
		try {
			utx.begin();
			Member member = em.find(Member.class, id);
			if (member != null) {
				em.remove(member);
				utx.commit();
				return Response.ok().build();
			} else {
				return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@PUT
	public Response update(@FormParam("id") long id, @FormParam("first_name") String first_name, 
						   @FormParam("last_name") String last_name, @FormParam("city") String city, 
						   @FormParam("phone") String phone, @FormParam("email") String email) {
		try {
			utx.begin();
			Member member = em.find(Member.class, id);
			if (member != null) {
				member.setFirstName(first_name);
				member.setLastName(last_name);
				member.setCity(city);
				member.setPhone(phone);
				member.setEmail(email);
				em.merge(member);
				utx.commit();
				return Response.ok().build();
			} else {
				return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") long id) {
		if (id == 0) {
			List<Member> list = em.createQuery("SELECT t FROM Member t", Member.class).getResultList();
			if (list.size() == 0) {
				createSampleData();
				list = em.createQuery("SELECT t FROM Member t", Member.class).getResultList();
			}
			//TODO use JSON util like Gson to render objects and use REST Response Writer
			String json = "{\"id\":\"all\", \"body\":" + list.toString() + "}";
			return Response.ok(json).build();
		}
		Member member = null;
		try {
			utx.begin();
			member = em.find(Member.class, id);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (member != null)
			return Response.ok(member.toString()).build();
		else
			return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
	}
	
	private void createSampleData() {
		create("First Name 1", "Last Name 1", "City 1", "Phone 1", "Email 1");
		create("First Name 2", "Last Name 2", "City 2", "Phone 2", "Email 2");
		create("First Name 3", "Last Name 3", "City 3", "Phone 3", "Email 3");
	}
	
	private UserTransaction getUserTransaction() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			return (UserTransaction) ic.lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// There are two ways of obtaining the connection information for some services in Java 
	
	// Method 1: Auto-configuration and JNDI
	// The Liberty buildpack automatically generates server.xml configuration 
	// stanzas for the SQL Database service which contain the credentials needed to 
	// connect to the service. The buildpack generates a JNDI name following  
	// the convention of "jdbc/<service_name>" where the <service_name> is the 
	// name of the bound service. 
	// Below we'll do a JNDI lookup for the EntityManager whose persistence 
	// context is defined in web.xml. It references a persistence unit defined 
	// in persistence.xml. In these XML files you'll see the "jdbc/<service name>"
	// JNDI name used.

	private EntityManager getEm() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			return (EntityManager) ic.lookup("java:comp/env/openjpa-todo/entitymanager");  //here?
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method 2: Parsing VCAP_SERVICES environment variable
    // The VCAP_SERVICES environment variable contains all the credentials of 
	// services bound to this application. You can parse it to obtain the information 
	// needed to connect to the SQL Database service. SQL Database is a service
	// that the Liberty buildpack auto-configures as described above, so parsing
	// VCAP_SERVICES is not a best practice.
	
	// see HelloResource.getInformation() for an example

}
