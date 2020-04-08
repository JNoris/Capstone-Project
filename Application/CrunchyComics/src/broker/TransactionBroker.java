package broker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import manager.DatabaseManager;
import domain.*;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 * TransactionBroker class is responsible for retrieving and inserting
 * Transactions data from/to the database.
 *
 * @author Simon Skrudland
 */
public class TransactionBroker {

    private EntityManager em = null;
    private DatabaseManager dbManager = null;

    public TransactionBroker() {
        this.em = DatabaseManager.getEntityManager();
    }

    public TransactionBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }

    /**
     * Inserts a Transaction object and TransationItem list to the database.
     *
     * @param transaction - Transaction object to be persisted.
     */
    public void insert(Transaction transaction) {
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        System.out.println("Transaction added.");
    }

    /**
     * Gets the highest transaction ID from the database.
     *
     * @return The highest transaction ID.
     */
    public int getHighestID() {
        Query q = em.createNamedQuery("Transaction.getHighestIndex");
        return (int) q.getResultList().get(0);
    }

    /**
     * Gets all transaction from the database.
     *
     * @return a list with all transactions.
     */
    public List<Transaction> getAllTransactions() {
        Query q = em.createNamedQuery("Transaction.findAll");
        return (List<Transaction>) q.getResultList();
    }

    public List<Transaction> getTransactionsBetweenDates(String from, String to) {
        Query q = em.createNamedQuery("Transaction.findByTransactionBetween");
        q.setParameter("fromDate", from);
        q.setParameter("toDate", to + " 23:00:00");
        return (List<Transaction>) q.getResultList();

    }

}
