/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author milut
 */

@Stateless
public class UsersRepo {
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager entityManager;
    
    public List<Users> getUsers(){
        Query q = entityManager.createQuery("select u from Users u");
        return q.getResultList();
    }

    public void insertUser(Users user){
        entityManager.persist(user);
    }

    public Users validateUser(String email, String password) {
        System.out.println(email + ", " + password);
        Query q = entityManager.createQuery("SELECT u from Users u where u.username = :username " +
                "and u.password = :pass");
        q.setParameter("username", email);
        q.setParameter("pass", password);
        List<Users> users = q.getResultList();
        System.out.println(users.size());
        if(users.size() > 0){
            return users.get(0);
        }
        else {
            return null;
        }
    }
}
