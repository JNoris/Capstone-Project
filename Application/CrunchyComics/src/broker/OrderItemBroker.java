/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import domain.OrderItem;
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
public class OrderItemBroker {
       private EntityManager em = null;
        private DatabaseManager dbManager = null;

    public OrderItemBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }
    
      public List<OrderItem> getAllOrderItems(){
        Query q = em.createNamedQuery("OrderItem.findAll");
        List results = q.getResultList();
        
        return results;
    }
      
    public OrderItem getOrderItemByItemID(int itemID){
        Query q = em.createNamedQuery("OrderItem.findByItemID");
        q.setParameter("itemID", itemID);
        List results = q.getResultList();
        
        return (OrderItem)results.get(0);
    }
    
    public OrderItem getOrderItemByOrderID(int orderNo){
        Query q = em.createNamedQuery("OrderItem.findByOrderNo");
        q.setParameter("orderNo", orderNo);
        List results = q.getResultList();
        
        return (OrderItem)results.get(0);
    }
    
     public OrderItem getOrderItemByPurchasePrice(float purchasePrice){
        Query q = em.createNamedQuery("OrderItem.findByPurchasePrice");
        q.setParameter("purchasePrice", purchasePrice);
        List results = q.getResultList();
        
        return (OrderItem)results.get(0);
    }
    
}
