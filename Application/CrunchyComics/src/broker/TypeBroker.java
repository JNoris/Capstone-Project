/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import domain.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import manager.DatabaseManager;

/**
 *
 * @author Vinicius Smith
 */
public class TypeBroker {
    
    private DatabaseManager dbManager;
    private EntityManager em;
    
    public TypeBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }
    
    public List<Type> getAllTypes(){
        Query q = em.createNamedQuery("Type.findAll");
        return (List<Type>)q.getResultList();
    }
    
    public void insert(Type type){
        em.getTransaction().begin();
        em.persist(type);
        em.getTransaction().commit();
    }
}
