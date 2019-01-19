package sr.unasat.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "address")
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int address_id;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "streetname", nullable = false)
    private String streetname;


    @ManyToMany(mappedBy = "address")
    private Set<Student> students = new HashSet<>()  ;


    public Address(){}

    public Address(String district, String streetname){
        this.district = district;
        this.streetname = streetname;
    }




    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudents(Student student) {
        if (!student.getAddress().contains(this)) {
            student.getAddress().add(this);
        }
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    public void removeStudent(Student student) {
        if (student.getAddress().contains(this)) {
            student.getAddress().remove(this);
        }
        if (this.students.contains(student)) {
            this.students.remove(student);
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "address id=" + address_id +
                ", district='" + district + '\'' +
                ", streetname='" + streetname + '\'' +
                '}';
    }

}
