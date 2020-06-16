package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author rando
 */
@Entity
@NamedQuery(name = "Ingredients.deleteAllRows", query = "DELETE from Ingredients")
public class Ingredients implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private String name;

    //@OneToOne(cascade=CascadeType.PERSIST)
    private int mpID;

    public Ingredients() {
    }

    public Ingredients(int amount, String name, int mpID) {
        this.amount = amount;
        this.name = name;
        this.mpID = mpID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
