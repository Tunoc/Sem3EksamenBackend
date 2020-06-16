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
@NamedQuery(name = "DayPlan.deleteAllRows", query = "DELETE from DayPlan")
public class DayPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long recipeID;
    private int numberOfServings;
//    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
//    @JoinColumn(name="MENU_PLAN_ID")
    private int mpID;

    //Constructors
    public DayPlan() {
    }

    public DayPlan(long recipeID, int numberOfServings, int mpID) {
        this.recipeID = recipeID;
        this.numberOfServings = numberOfServings;
        this.mpID = mpID;
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public int getMpID() {
        return mpID;
    }

//    public MenuPlan getMp() {
//        return mpd;
//    }
//
//    public void setMp(MenuPlan mp) {
//        this.mpd = mp;
//    }
    public void setMpID(int mpID) {
        this.mpID = mpID;
    }

    public long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(long recipeID) {
        this.recipeID = recipeID;
    }

}
