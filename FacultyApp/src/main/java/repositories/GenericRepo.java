package repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brusu
 */
public class GenericRepo<T, K> {
    
    @PersistenceContext(name="persistenceUnit") 
    protected EntityManager em;
    private Class<T> entityType;
    
    public GenericRepo(Class<T> type){
        entityType = type;
    }
    
    protected void executeTransaction(ITransaction transaction){
        em.getTransaction().begin();
        transaction.makeTransaction();
        em.getTransaction().commit();
    }
    
    protected T getEntity(K entityId) {
        return em.find(entityType, entityId);
    }
    
    protected void insertEntity(final T entity){
        executeTransaction(new ITransaction(){
            @Override
            public void makeTransaction() {
                em.persist(entity);
            }
        });
    }
    
    protected void deleteEntity(final K entityId){
        executeTransaction(new ITransaction(){
            @Override
            public void makeTransaction() {         
                T entityToDelete = getEntity(entityId);
                if (entityToDelete != null)
                    em.remove(entityToDelete);
            }  
        });
    }
    
    protected void updateEntity(final T entity){
        executeTransaction(new ITransaction(){
            @Override
            public void makeTransaction() {
                em.merge(entity);
            }
        });
    }
    
    public void close(){
        em.close();
    }
}
