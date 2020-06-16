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
    private List<Ingredient> shoppingList;
    
    @OneToMany(mappedBy="mp")
    private List<DayPlan> dayPlans;
    
    @OneToMany(mappedBy="")
    private List<User> users;
    
    //Constructors
    public MenuPlan(){}

    
    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
