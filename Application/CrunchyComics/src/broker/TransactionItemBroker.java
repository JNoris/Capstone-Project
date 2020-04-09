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
 * TransactionItem class is responsible for retrieving and inserting
 * TransactionItem data from/to the database.
 *
 * @author Simon Skrudland
 */
public class TransactionItemBroker {

    private EntityManager em = null;
    private DatabaseManager dbManager = null;

    /**
     * The non-default constructor used to grab TransactionItem/s from the database.
     * @param dbManager 
     */
    public TransactionItemBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }

    /**
     * Gets all TransactionItems from the database.
     *
     * @return a list containing all the TransactionItems.
     */
    public List<TransactionItem> getAllTransactionItems() {
        Query q = em.createNamedQuery("Item.findAll");
        List results = q.getResultList();

        return results;
    }

    /**
     * Gets a TransactionItem based on the itemID and transactionID.
     *
     * @param itemID specific itemID for the TransactionItem.
     * @param transactionID specific transactionID for the TransactionItem.
     * @return a TransactionItem that matches the itemID and transactionID.
     */
    public TransactionItem getTransactionItem(int itemID, int transactionID) {
        Query q = em.createNamedQuery("TransactionItem.findTransactionItem");
        q.setParameter("itemID", itemID);
        q.setParameter("transactionID", transactionID);
        List results = q.getResultList();

        return (TransactionItem) results.get(0);
    }

    /**
     * Get TransactionItem by itemID.
     *
     * @param itemID specific itemID for the TransactionItem.
     * @return a TransactionItem that matches the itemID.
     */
    public TransactionItem getTransactionItemByItemID(int itemID) {
        Query q = em.createNamedQuery("TransactionItem.findByItemID");
        q.setParameter("itemID", itemID);
        List results = q.getResultList();

        return (TransactionItem) results.get(0);
    }

    /**
     * Get TransactionItem by transactionID.
     *
     * @param transactionID specific transactionID for the TransactionItem.
     * @return a TransactionItem that matches the transactionID.
     */
    public TransactionItem getTransactionItemByTransactionID(int transactionID) {
        Query q = em.createNamedQuery("TransactionItem.findByTransactionID");
        q.setParameter("transactionID", transactionID);
        List results = q.getResultList();

        return (TransactionItem) results.get(0);
    }

    /**
     * Get TransactionItem by price.
     *
     * @param price specific price for the TranactionItem
     * @return a TransactionItem that matches the price.
     */
    public TransactionItem getTransactionItemByItemPrice(int price) {
        Query q = em.createNamedQuery("TransactionItem.findByPrice");
        q.setParameter("price", price);
        List results = q.getResultList();

        return (TransactionItem) results.get(0);
    }

    /**
     * Gets the most sold TransactionItem.
     *
     * @return the most sold TransactionItem.
     */
    public TransactionItem getMostSold() {
        Query q = em.createNamedQuery("TransactionItem.findMostSoldItem");
        List results = q.getResultList();
        if (results.size() > 0) {
            return (TransactionItem) results.get(0);
        }
        return null;
    }
}
