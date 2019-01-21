package sr.unasat.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "application")
public class Application  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int application_id ;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tender_id")
    private Tender tender;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;


    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "date", nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }



    @Override
    public String toString() {
        return "ApplicationDAO{" +
                "application id=" + application_id +
                ", Student='" + student + '\'' +
                ", education='" + education + '\'' +
                ", tender=" + tender + '\'' +
                ", status=" + status + '\'' +
                '}';
    }


}
