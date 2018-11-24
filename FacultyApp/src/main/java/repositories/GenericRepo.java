package repositories;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author brusu
 */
public class GenericRepo<T> {
    
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
    
    protected T getEntity(long entityId) {
        return em.find(entityType, entityId);
    }
    
    public void insertEntity(final T entity){
        executeTransaction(new ITransaction(){
            @Override
            public void makeTransaction() {
                em.persist(entity);
            }
        });
    }
    
    public void deleteEntity(final long entityId){
        executeTransaction(new ITransaction(){
            @Override
            public void makeTransaction() {                
                em.remove(getEntity(entityId));
            }  
        });
    }
    
    public void updateEntity(final T entity){
        executeTransaction(new ITransaction(){
            @Override
            public void makeTransaction() {
                em.merge(entity);
            }
        });
    }
}
