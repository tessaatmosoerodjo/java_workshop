package sr.unasat.DP.factory;

import sr.unasat.dao.StudentDAO;
import sr.unasat.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StudentFactory implements StudentInterface {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PERSISTENCE");
    private EntityManager entityManager;

    public StudentFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student getStudent(String firstname) {
        if (firstname != null) {
            StudentDAO studentDAO = new StudentDAO(entityManager);
            Student student = studentDAO.selectStudentByFirstName(firstname);
            if (student != null) {
                System.out.println("STUDENT ALREADY EXISTS.");
                System.out.println("APPLICATION EXIT");
                System.exit(0);
                return student;
            }
        }
        return null;
    }

    @Override
    public Student createStudent(Student student) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(student);
            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            manager.close();
        }
        return null;
    }
}

//create object without exposing creation logic
