package broker;

import domain.Vendor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import manager.DatabaseManager;

/**
 * The class that determines the vendor name in the database and allows to grab and insert into the database.
 * @author Vinicius Smith
 */
public class VendorBroker {

    private EntityManager em;
    
    /**
     * Creates the communication between the database and the front-end application for the vendor.
     */
    public VendorBroker() {
        this.em = DatabaseManager.getEntityManager();
    }

    /**
     * Grabs all the vendor names from database.
     * @return the list of vendors.
     */
    public List<Vendor> getAllVendors() {
        Query q = em.createNamedQuery("Vendor.findAll");
        List results = q.getResultList();

        return results;
    }
    
    /**
     * Takes the vendor name from the front-end and inserts it into the database.
     * @param vendor 
     */
    public void insert(Vendor vendor) {
        em.getTransaction().begin();
        em.persist(vendor);
        em.getTransaction().commit();
    }
    
    /**
     * Gets the last vendor name in the database and returns the ID.
     * @return the last vendor name ID;
     */
    public int getLastID() {
        Query q = em.createNamedQuery("Vendor.findLastID");
        List results = q.getResultList();
        return (int) results.get(0);
    }
}
