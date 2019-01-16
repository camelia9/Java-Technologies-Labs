/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Documents;
import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author milut
 */
@Stateless
public class DocumentsRepo {
    
    @PersistenceContext(name="persistence-unit") 
    private EntityManager entityManager;
    
    private Query selectAll;
    
    public void insertDocument(Documents document){
        entityManager.persist(document);
    }
    
    public List<Documents> getAllDocuments(){
        if (entityManager == null){
            System.out.println("[DEBUG] EntityManager is null");
        }
        else if (selectAll == null){
            selectAll = entityManager.createQuery("SELECT d FROM Documents d");
            System.out.println(selectAll.getResultList());
        }
        return selectAll.getResultList();
    }
    
    public List<Documents>findDocumentsByUsername(String username){
        Query q = entityManager.createQuery("select d from DocumentsEntity d where d.uploadedby = :username");
        q.setParameter("username", username);
        return q.getResultList();
    }
}
