package sr.unasat.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int type_id;

    @Column(name = "type_education", nullable = false)
    private String type_education;


    //oneToMany = 1type bij meerdere education kan staan
    @OneToMany(mappedBy = "type")
    private Set<Education> educations;

    public Type(){}

    public Type(String type_education){
        this.type_education = type_education;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_education() {
        return type_education;
    }

    public void setType_education(String type_education) {
        this.type_education = type_education;
    }

    public Set<Education> getEducation() {
        return educations;
    }

    public void setEducation(Set<Education> education) {
        this.educations = education;
    }

}
