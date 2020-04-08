package broker;

import domain.Item;
import domain.Vendor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import manager.DatabaseManager;

/**
 *
 * @author Vinicius Smith
 */
public class VendorBroker {

    private EntityManager em;

    public VendorBroker() {
        this.em = DatabaseManager.getEntityManager();
    }

    public List<Vendor> getAllVendors() {
        Query q = em.createNamedQuery("Vendor.findAll");
        List results = q.getResultList();

        return results;
    }

    public void insert(Vendor vendor) {
        em.getTransaction().begin();
        em.persist(vendor);
        em.getTransaction().commit();
    }

    public int getLastID() {
        Query q = em.createNamedQuery("Vendor.findLastID");
        List results = q.getResultList();
        return (int) results.get(0);
    }
}
