package broker;

import domain.Orders;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import manager.DatabaseManager;

/**
 *
 * @author Vinicius Smith
 */
public class OrderBroker {
    private EntityManager em = null;

    public OrderBroker() {
        this.em = DatabaseManager.getEntityManager();
    }

    /**
     * Gets an order based on the ID.
     *
     * @param id target order ID.
     * @return the order that has the given id.
     */
    public Orders getOrderByID(int id) {
        Query q = em.createNamedQuery("Orders.findByOrderNo");
        q.setParameter("orderNo", id);
        List results = q.getResultList();

        return (Orders) results.get(0);
    }

      /**
     * Returns all the orders from the database.
     *
     * @return a list of all the orders.
     */
    public List<Orders> getAllOrders() {
        Query q = em.createNamedQuery("Orders.findAll");
        List results = q.getResultList();

        return results;
    }

    public int getLastID() {
        Query q = em.createNamedQuery("Orders.findLastID");
        List results = q.getResultList();
        return (int)results.get(0);
    }

    public void insert(Orders order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }
}
