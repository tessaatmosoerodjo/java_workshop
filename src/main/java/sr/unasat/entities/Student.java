package sr.unasat.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //value will be automatically generated for that field.
    @Column(name = "student_id")
    private int student_id;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Temporal(TemporalType.DATE)
    //store time and date(util) / drops the time value and only preserves the date.
    @Column(name = "date_of_birth", nullable = false)
    private Date date_of_birth;

    @Column(name = "telephone_number", nullable = false)
    private int telephone_number;

    @Column(name = "gender", nullable = false)
    private String gender;

    //constructor
    public Student(String lastName, String firstname, Date date_of_birth, int telephone_number, String gender) {
        this.lastName = lastName;
        this.firstname = firstname;
        this.date_of_birth = date_of_birth;
        this.telephone_number = telephone_number;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    @OneToMany(mappedBy = "student")
    private Set<Application> applications = new HashSet<>();
    //HashSet = store a collection of unique elements. implementeert Set interface

    @ManyToMany(cascade = {CascadeType.ALL})
    //any changehappens to student should happen to address
    @JoinTable(name = "student_address",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> address = new HashSet<>() ;


    public Student(){}

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStudent_id() {return student_id; }

    public void setStudent_id(int student_id) {this.student_id = student_id; }

    public String getLastName() {return lastName; }

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFirstName() {return firstname;}

    public void setFirstName(String firstName) {this.firstname = firstName;}

    public Date getDate_of_birth() {return date_of_birth;}

    public void setDate_of_birth(Date date_of_birth) {this.date_of_birth = date_of_birth;}

    public int getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(int telephone_number) {this.telephone_number = telephone_number; }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
