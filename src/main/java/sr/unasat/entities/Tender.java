package sr.unasat.entities;

import javax.persistence.*;


@Entity
@Table(name = "Tender")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tender_id")
    private int tender_id;

    @Column(name = "tender_description", nullable = false)
    private String tender_description;


    @OneToOne(mappedBy = "tender")
    private Application application;

    public Tender(){}

    public Tender(String  tender_description){
        this.tender_description = tender_description;
    }


    public int getTender_id() {
        return tender_id;
    }

    public void setTender_id(int tender_id) {
        this.tender_id = tender_id;
    }

    public String getTender_description() {
        return tender_description;
    }

    public void setTender_description(String tender_description) {
        this.tender_description = tender_description;
    }


    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "id=" + tender_id +
                ", description='" + tender_description + '\'' +
                '}';
    }



}
