package at.htl.motorcycle.business;

import at.htl.motorcycle.model.Engine;
import at.htl.motorcycle.model.Motorcycle;
import at.htl.motorcycle.model.MotorcycleType;
import at.htl.motorcycle.model.Transmission;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Startup
@Singleton
public class InitBean {

    private static InitBean initBean;

    public static synchronized InitBean getInstance() {
        if (initBean == null){
            initBean = new InitBean();
        }
        return initBean;
    }

    @PersistenceContext
    EntityManager em;

    private InitBean() {

    }

    @PostConstruct
    public void init() {
        System.err.println("******** hello");
        MotorcycleType ktm = new MotorcycleType("KTM", "1290 Superduke", LocalDate.of(2017,4,15));
        Engine ktmEngine = new Engine(2,1290);
        Transmission ktmTransmission = new Transmission(6);
        em.persist(new Motorcycle("Orange", 160, ktm,ktmEngine,ktmTransmission));
        MotorcycleType honda = new MotorcycleType("Honda","CBR 1000",LocalDate.of(2016,5,3));
        Engine hondaEngine = new Engine(4,1000);
        Transmission hondaTransmission = new Transmission(5);
        em.persist(new Motorcycle("Red",130,honda,hondaEngine,hondaTransmission));
        MotorcycleType yamaha = new MotorcycleType("Yamaha", "R1",LocalDate.of(2018,10,24));
        Engine yamahaEngine = new Engine(2,1200);
        Transmission yamahaTransmission = new Transmission(6);
        em.persist(new Motorcycle("Blue",140, yamaha,yamahaEngine,yamahaTransmission));
    }

    public void addMotorcycle(Motorcycle motorcycle) {
        if(em == null) {
            System.out.println(motorcycle);
        }
        em.persist(motorcycle);
    }
}
