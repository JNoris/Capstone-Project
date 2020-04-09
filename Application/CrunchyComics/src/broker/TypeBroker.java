package broker;

import domain.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import manager.DatabaseManager;

/**
 * The class that grabs from the "Types" table in and out of the database.
 *
 * @author Vinicius Smith
 */
public class TypeBroker {

    private DatabaseManager dbManager;
    private EntityManager em;

    /**
     * The non-default constructor to grab data from the database.
     *
     * @param dbManager database manager reference.
     */
    public TypeBroker(DatabaseManager dbManager) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CrunchyComicsPU");
        this.dbManager = dbManager;
        this.em = emfactory.createEntityManager();
    }

    /**
     * Grabs all types of items in the database.
     *
     * @return all of the items in the database, regardless of type.
     */
    public List<Type> getAllTypes() {
        Query q = em.createNamedQuery("Type.findAll");
        return (List<Type>) q.getResultList();
    }

    /**
     * Inserts into the database the item type.
     *
     * @param type type reference.
     */
    public void insert(Type type) {
        em.getTransaction().begin();
        em.persist(type);
        em.getTransaction().commit();
    }
}
