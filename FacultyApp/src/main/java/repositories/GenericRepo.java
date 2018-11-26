package repositories;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author brusu
 */
public class GenericRepo<T, K> {
    
    protected EntityManager em;
    private Class<T> entityType;
    
    public GenericRepo(Class<T> type){
        em = Persistence.createEntityManagerFactory("persistenceUnit").createEntityManager();
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
