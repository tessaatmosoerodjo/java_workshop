package sr.unasat.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "education")
public class Education{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int education_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "education_name", nullable = false)
    private String education_name;

    @Column(name = "amount", nullable = false)
    private int amount;

    //manyToOne = meerdere opleidingen kunnen 1 type hebben
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany
    @JoinColumn(name = "education" )
    private Set<Application> application ;


    public Education(){}

    public Education(String title, int amount) {
        this.title = title;
        this.amount = amount;

    }


    public int getEducation_id() {
        return education_id;
    }

    public void setEducation_id(int education_id) {
        this.education_id = education_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEducation_name() {
        return education_name;
    }

    public void setEducation_name(String education_name) {
        this.education_name = education_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Application> getApplication() {
        return application;
    }

    public void setApplication(Set<Application> applications) {
        this.application = applications;
    }


}
