package sr.unasat.entities;


import javax.persistence.*;

import java.util.HashSet;
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
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany
    @JoinColumn(name = "education" )
    private Set<Application> application ;


    public Education(){}

    public Education(String title, String education_name, int amount){
        this.title = title;
        this.education_name = education_name;
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

    public void addApplication(Application application) {
        if (application.getEducation() != this) {
            application.setEducation(this);
        }
        if (!this.application.contains(application)) {
            this.application.add(application);
        }
    }

    public void removeApplication(Application application) {
        if (application.getEducation() == this) {
            application.setEducation(null);
        }
        if (this.application.contains(application)) {
            this.application.remove(application);
        }
    }

    @Override
    public String toString() {
        return "Education{" +
                "education id=" + education_id +
                ", title='" + title  + '\'' +
                ", education name='" + education_name + '\'' +
                ", amount = '" + amount + '\'' +
                ", type = '" + type + '\'' +
                '}';
    }


}
