package sr.unasat.dao;

import sr.unasat.entities.Address;
import sr.unasat.entities.Application;
import sr.unasat.entities.Education;
import sr.unasat.entities.Status;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RapportingDAO {

    private EntityManager entityManager;

    public RapportingDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //alle leningen met goedgekeurd
    public List<Application> selectAllApplicationByApproved(Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.status = :status";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("status", status);
        List<Application> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    //Jaaroverzicht met goed/afgekeurd.
    public List<Application> selectJaaroverzicht(int month, int year) {
        entityManager.getTransaction().begin();
        String jpql = "SELECT a FROM Application a " +
                "WHERE FUNCTION('MONTH', a.date) = :month " +
                "AND FUNCTION('YEAR', a.date) = :year ";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
        List<Application> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    // Meest declined
    public List<Object[]> findCountDeclined(Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select count(a.status) from Application a where a.status = :status";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("status", status);
        List<Object[]> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    //meest approved
    public List<Object[]> findCountApproved(Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select count(a.status) from Application a where a.status = :status";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("status", status);
        List<Object[]> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    //afgekeurde per school
    public List<Application> selectDeclinedBySchool(Education education, Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.education= :education and a.status= :status ";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("education", education);
        query.setParameter("status", status);
        List<Application> results = query.getResultList();
        entityManager.getTransaction().commit();
        return results;
    }

    //afgekeurde per school
    public List<Application> findAllSchoolApplications() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a order by education desc, status";
        //order by school status
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        List<Application> results = query.getResultList();
        entityManager.getTransaction().commit();
        return results;
    }


    public void printMonthlyOverview(List<Application> applicationList1) {

        for (Application application : applicationList1) {
            System.out.println("");
            System.out.println("-------------BEGIN----------------");
            System.out.println("Date of application: " + application.getDate());
            System.out.println("Application ID: " + application.getApplication_id());
            System.out.println("-----------------------------");
            System.out.println("Student naam: " + application.getStudent().getFirstName() + " " + application.getStudent().getLastName());
            System.out.println("Student date of birth: " + application.getStudent().getDate_of_birth());
            System.out.println("Student telephone number: " + application.getStudent().getTelephone_number());
            System.out.println("Student gender: " + application.getStudent().getGender());
            System.out.println("-----------------------------");
            for (Address address : application.getStudent().getAddress()) {
                System.out.println("Student address ");
                System.out.println("Student district:" + address.getDistrict());
                System.out.println("Student streetname:" + address.getStreetname());
                System.out.println("-----------------------------");
            }
            System.out.println("Education title:" + application.getEducation().getTitle());
            System.out.println("Education name:" + application.getEducation().getEducation_name());
            System.out.println("Education amount:" + application.getEducation().getAmount());
            System.out.println("Education Type:" + application.getEducation().getType().getType_education());
            System.out.println("-----------------------------");
            System.out.println("Tender description:" + application.getTender().getTender_description());
            System.out.println("-----------------------------");
            System.out.println("Status:" + application.getStatus().getStatus());
            System.out.println("Note: " + application.getNote());
            System.out.println("-------------END----------------");
            System.out.println("");
        }
    }


}
