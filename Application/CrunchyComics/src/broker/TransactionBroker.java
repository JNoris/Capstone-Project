/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import manager.DatabaseManager;
import domain.*;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author 695553
 */
public class TransactionBroker {

    private EntityManager em = null;
    private DatabaseManager dbManager = null;

    public TransactionBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }
    /**
     * Inserts a Transaction object and TransationItem list to the database.
     * @param transaction - Transaction object to be persisted.
     */
    public void insert(Transaction transaction){
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        System.out.println("Transaction added.");
    }
    /**
     * Gets the highest transaction ID from the database.
     * @return The highest transaction ID.
     */
    public int getHighestID(){
        Query q = em.createNamedQuery("Transaction.getHighestIndex");
        return (int) q.getResultList().get(0);
    }
    
    public List<Transaction> getAllTransactions(){
        Query q = em.createNamedQuery("Transaction.findAll");
        return (List<Transaction>)q.getResultList();
    }
    
}
