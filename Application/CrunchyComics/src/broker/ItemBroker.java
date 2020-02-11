/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import manager.DatabaseManager;
import domain.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author 695553
 */
public final class ItemBroker {
    private EntityManager em = null;
    private DatabaseManager dbManager = null;

    public ItemBroker(DatabaseManager dbManager) {
         EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CrunchyComicsPU" );
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }
    
    public Item getItemByID(int id){
        Query q = em.createNamedQuery("Item.findByItemID");
        q.setParameter("itemID", id);
        List results = q.getResultList();
        
        return (Item)results.get(0);
    }
    public Item getItemByUPC(int upc){
        Query q = em.createNamedQuery("Item.findByItemID");
        q.setParameter("upc", upc);   
        List results = q.getResultList();
        
        return (Item)results.get(0);
    }
    public List<Item> getAllItems(){
        Query q = em.createNamedQuery("Item.findAll");
        List results = q.getResultList();
        
        return results;
    }
}
