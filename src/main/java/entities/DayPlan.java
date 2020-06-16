package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private int numberOfServings;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MENU_PLAN_ID")
    private MenuPlan mp;

    //Constructors
    public DayPlan() {
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
