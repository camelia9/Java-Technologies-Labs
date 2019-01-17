/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import entities.Group;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import utils.AuthenticationUtils;

@Stateless
public class UserEJB {
	
	@PersistenceContext(unitName="mypu")
	private EntityManager em;
	
	public User createUser(User user) {
		try {
			user.setPassword((user.getPassword()));
                        System.out.println("[Debug] " + user.getUserid() + " " + user.getPassword());
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		Group group = new Group();
		group.setUserid(user.getUserid());
		group.setGroupname(Group.USERS_GROUP);
		em.persist(user);
		em.persist(group);
		
		return user;
	}
	public User findUserById(String id) {
		TypedQuery<User> query = em.createNamedQuery("findUserById", User.class);
		query.setParameter("userid", id);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
}