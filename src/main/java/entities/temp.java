package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author rando
 */
public class temp {
//private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
//            "pu",
//            "jdbc:mysql://localhost:3307/sem3eksamen",
//            "dev",
//            "ax2",
//            EMF_Creator.Strategy.DROP_AND_CREATE);

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        EntityManager em = emf.createEntityManager();

        User admin = new User("admin", "admin123");
        User user1 = new User("user1", "user123");
        User user2 = new User("user2", "user123");
        User user3 = new User("user3", "user123");

        List<Ingredients> i = new ArrayList();
        List<DayPlan> dpList = new ArrayList();
        List<User> uList = new ArrayList();
        MenuPlan mp1 = new MenuPlan(1, 1, uList);
        Ingredients ing = new Ingredients(5, "someEngridient", 1);
        DayPlan dp1 = new DayPlan(2, 1, 1);
        DayPlan dp2 = new DayPlan(3, 1, 1);
        dpList.add(dp1);
        dpList.add(dp2);
        uList.add(user1);
        i.add(ing);
        try {
            em.getTransaction().begin();
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user1.addRole(userRole);
            user2.addRole(userRole);
            user3.addRole(userRole);
            admin.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user1);
            em.persist(user2);
            em.persist(user3);
            em.persist(admin);
            
            em.persist(dp1);
            em.persist(dp2);
            em.persist(ing);
            em.persist(mp1);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
