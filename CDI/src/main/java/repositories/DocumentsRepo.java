/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Documents;
import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author milut
 */
@Stateless
public class DocumentsRepo {
    
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager entityManager;
    
    public void insertDocument(Documents document){
        entityManager.persist(document);
    }
}
