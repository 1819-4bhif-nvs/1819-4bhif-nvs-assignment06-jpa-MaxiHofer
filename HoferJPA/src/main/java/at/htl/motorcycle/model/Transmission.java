package at.htl.motorcycle.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Transmission.findAll", query = "select t from Transmission t"),
})
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int gearNumber;

    public Transmission() {
    }

    public Transmission(int gearNumber) {
        this.gearNumber = gearNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGearNumber() {
        return gearNumber;
    }

    public void setGearNumber(int gearNumber) {
        this.gearNumber = gearNumber;
    }
}
