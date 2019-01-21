package sr.unasat.dao;

import sr.unasat.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDAO {


    private EntityManager entityManager;

    public StudentDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Student createStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }

    public Student selectStudentBy(int student_id) {
        entityManager.getTransaction().begin();
        String jpql = "select s from Student s where s.student_id = :student_id";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        query.setParameter("student_id", student_id);
        Student student = query.getSingleResult();
        entityManager.getTransaction().commit();
        return student;
    }

    public Student selectStudentByFirstName(String firstname) {
        entityManager.getTransaction().begin();
        String jpql = "select s from Student s where s.firstname = :firstname";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        query.setParameter("firstname", firstname);
        Student student = query.getSingleResult();
        entityManager.getTransaction().commit();
        return student;
    }

    public List<Student> selectAllStudent(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select s from Student s";
        TypedQuery<Student > query = entityManager.createQuery(jpql, Student.class);
        List<Student > studentList = query.getResultList();
        entityManager.getTransaction().commit();
        return studentList;
    }

    public void updateStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();

    }

    public void deleteStudent(Student student){
            entityManager.getTransaction().begin();
            entityManager.remove(student);
            entityManager.getTransaction().commit();
    }
}
