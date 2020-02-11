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
    public void insert(Transaction transaction){
        em.persist(transaction);
    }
}
