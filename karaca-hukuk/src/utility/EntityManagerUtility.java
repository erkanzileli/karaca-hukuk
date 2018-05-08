package utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtility {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");

    private EntityManagerUtility() {
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
