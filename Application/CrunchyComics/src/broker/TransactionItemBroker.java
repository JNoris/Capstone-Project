/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import domain.Item;
import domain.Transaction;
import domain.TransactionItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import manager.DatabaseManager;

/**
 *
 * @author 749295
 */
public class TransactionItemBroker {
     private EntityManager em = null;
    private DatabaseManager dbManager = null;

    public TransactionItemBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }
    public void insert(Transaction transaction){
        em.persist(transaction);
    }
    
      public List<TransactionItem> getAllTransactionItems(){
        Query q = em.createNamedQuery("Item.findAll");
        List results = q.getResultList();
        
        return results;
    }
    
    public TransactionItem getTransactionItem(int itemID, int transactionID){
        Query q = em.createNamedQuery("TransactionItem.findTransactionItem");
        q.setParameter("itemID", itemID);
        q.setParameter("transactionID", transactionID);
        List results = q.getResultList();
        
        return (TransactionItem)results.get(0);
    }
    
    public TransactionItem getTransactionItemByItemID(int itemID){
        Query q = em.createNamedQuery("TransactionItem.findByItemID");
        q.setParameter("itemID", itemID);
        List results = q.getResultList();
        
        return (TransactionItem)results.get(0);
    }
    
     public TransactionItem getTransactionItemByTransactionID(int transactionID){
        Query q = em.createNamedQuery("TransactionItem.findByTransactionID");
        q.setParameter("transactionID", transactionID);
        List results = q.getResultList();
        
        return (TransactionItem)results.get(0);
    }
     
      public TransactionItem getTransactionItemByItemPrice(int price){
        Query q = em.createNamedQuery("TransactionItem.findByPrice");
        q.setParameter("price", price);
        List results = q.getResultList();
        
        return (TransactionItem)results.get(0);
    }
}
