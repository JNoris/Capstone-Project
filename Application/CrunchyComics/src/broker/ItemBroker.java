package broker;

import manager.DatabaseManager;
import domain.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * ItemBroker class is responsible for retrieving and insert Item data from/to
 * the database.
 *
 * @author Simon Skrudland
 */
public final class ItemBroker {

    private EntityManager em = null;

    /**
     * This is the default constructor to get items in the database.
     */
    public ItemBroker() {
        this.em = DatabaseManager.getEntityManager();
    }

    /**
     * This is the non-default constructor to get items in the database;
     * required a database manager object and entity manager object.
     *
     * @param dbManager reference to database manager.
     * @param em reference to entity manager.
     */
    public ItemBroker(DatabaseManager dbManager, EntityManager em) {
        this.em = em;
    }

    /**
     * Gets an item based on the ID.
     *
     * @param id target item ID.
     * @return the item that has the given id.
     */
    public Item getItemByID(int id) {
        Query q = em.createNamedQuery("Item.findByItemID");
        q.setParameter("itemID", id);
        List results = q.getResultList();

        return (Item) results.get(0);
    }

    /**
     * Get an item base on the UPC.
     *
     * @param upc target item UPC.
     * @return the item that has the given UPC.
     */
    public Item getItemByUPC(int upc) {
        Query q = em.createNamedQuery("Item.findByItemID");
        q.setParameter("upc", upc);
        List results = q.getResultList();

        return (Item) results.get(0);
    }

    /**
     * Returns all the items from the database.
     *
     * @return a list of all the items.
     */
    public List<Item> getAllItems() {
        Query q = em.createNamedQuery("Item.findAll");
        List results = q.getResultList();

        return results;
    }

    /**
     * Searches for items that match the given string and returns a list with
     * them.
     *
     * @param toMatch string to match with item names.
     * @return a list with all the items that match.
     */
    public List<Item> getMatchingItems(String toMatch) {
        Query q = em.createNamedQuery("Item.findByMatchingName");
        q.setParameter("name", toMatch);
        List results = q.getResultList();

        return results;
    }

    /**
     * Looks at the final item in the database and grabs that item.
     *
     * @return the last item ID
     */
    public int getLastID() {
        Query q = em.createNamedQuery("Item.findLastID");
        List results = q.getResultList();
        if (results.get(0) == null) {
            return 0;
        }
        return (int) results.get(0);
    }

    /**
     * Allows for an item to be updated/inserted into the database.
     *
     * @param item item to be inserted or updated.
     */
    public void insert(Item item) {
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }
}
