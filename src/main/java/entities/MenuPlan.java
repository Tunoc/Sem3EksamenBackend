package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author rando
 */
@Entity
@NamedQuery(name = "MenuPlan.deleteAllRows", query = "DELETE from MenuPlan")
public class MenuPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne(cascade=CascadeType.PERSIST)
    //private List<Ingredients> shoppingList;
    private int ingredient;

    //@OneToMany(mappedBy = "mpd", cascade=CascadeType.PERSIST)
    //private List<DayPlan> dayPlans;
    private int dayPlan;

    @OneToMany(mappedBy = "mpu")
    private List<User> users;

    //Constructors
    public MenuPlan() {
    }

//    public MenuPlan(List<Ingredients> shoppingList, List<DayPlan> dayPlans, List<User> users) {
//        this.shoppingList = shoppingList;
//        this.dayPlans = dayPlans;
//        this.users = users;
//    }

    public MenuPlan(int ingredient, int dayPlan, List<User> users) {
        this.ingredient = ingredient;
        this.dayPlan = dayPlan;
        this.users = users;
    }
    
    

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIngredient() {
        return ingredient;
    }

    public void setIngredient(int ingredient) {
        this.ingredient = ingredient;
    }

    public int getDayPlan() {
        return dayPlan;
    }

    public void setDayPlan(int dayPlan) {
        this.dayPlan = dayPlan;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
