package sr.unasat.entities;


import sr.unasat.App.App;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "status")
public class Status{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int status_id;

    @Column(name = "status", nullable = false)
    private String status;

   @OneToMany(mappedBy = "status")
   private Set<Application> applications ;
//HashSets are used to store a collection of unique elements.Hashset implementeert set interface

    public Status(){}

    public Status(String status){
        this.status = status;
    }



    public int getStatus_id() {return status_id;}

    public void setStatus_id(int status_id) {this.status_id = status_id;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application){
        if (application.getStatus() != this){
            application.setStatus(this);
        }
        if (this.applications.contains(application)){
            this.applications.add(application);
        }
    }

    public void removeApplication(Application application){
        if (application.getStatus() == this){
            application.setStatus(null);
        }
        if (this.applications.contains(application)){
            this.applications.remove(application);
        }
    }

    @Override
    public String toString(){
        return "Status{" +
                "status_id =" + status_id + '\'' +
                ", status = " + status  +
                '}';
    }

}
